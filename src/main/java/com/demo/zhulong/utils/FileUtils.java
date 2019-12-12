package com.demo.zhulong.utils;

import com.demo.zhulong.common.FileRelated;
import com.demo.zhulong.common.enums.AudioEnum;
import com.demo.zhulong.common.enums.DocEnum;
import com.demo.zhulong.common.enums.ImageEnum;
import com.demo.zhulong.common.enums.VideoEnum;
import com.demo.zhulong.config.HdfsConfig;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.EnumUtils;
import org.apache.directory.api.util.Strings;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.*;

/**
 * @Description: --------------------------------------
 * @ClassName: FileUtils.java
 * @Date: 2019/10/11 18:15
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class FileUtils {
    public static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final String ROOT_PATH = "E:/zhulong/src/main/resources/static/upload/";

    private static final String UPLOAD_HDFS_ADDRESS = HdfsConfig.getMasterAddress();

    /**
     * @Description: 判断文件类型
     * @Date: 2019/11/19 23:17
     * @Params: type 文件后缀 eg. png
     * @ReturnType: boolean
     **/
    public static boolean isExistFileType(String type) {
        List<String> fileTypes = new ArrayList<String>();
        // 文件
        for (DocEnum docEnum : DocEnum.values()) {
            fileTypes.add(docEnum.toString());
        }
        // 音频
        for (AudioEnum audioEnum : AudioEnum.values()) {
            fileTypes.add(audioEnum.toString());
        }
        // 图片
        for (ImageEnum imageEnum : ImageEnum.values()) {
            fileTypes.add(imageEnum.toString());
        }
        // 视频
        for (VideoEnum videoEnum : VideoEnum.values()) {
            fileTypes.add(videoEnum.toString());
        }

        if (fileTypes.contains(type.toUpperCase())) {
            return true;
        } else {
            logger.error(String.format("未包含的媒体类型: %s", type));
            return false;
        }
    }


    /**
     * @Description: 判断文件是否为空
     * @Date: 2019/11/19 23:22
     * @Params: fileName 文件
     * @ReturnType: boolean
     **/
    public static boolean isNullFile(MultipartFile fileName) {
        return Objects.isNull(fileName) || fileName.isEmpty();
    }


    /**
     * @Description: 删除单个文件
     * @Date: 2019/11/19 23:27
     * @Params: filePath 被删除文件的地址 eg. c:/test.doc
     * @ReturnType: boolean 单个文件删除成功返回true，否则返回false
     **/
    public static void deleteFile(File file) {
        try {
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                Files.delete(FileSystems.getDefault().getPath("file"));
            } else {
                logger.info(String.format("删除单个文件失败，file[%s]", file));
            }
        } catch (Exception e) {
            logger.error("删除单个文件异常", e);
        }
    }


    /**
     * @Description: 删除目录（文件夹）以及目录下的文件
     * @Date: 2019/12/7 21:17
     * @param: sPath 待删除目录
     * @ReturnType: void
     **/
    public static void deleteDirectory(String sPath) throws Exception {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        try {
            if (!dirFile.exists() || !dirFile.isDirectory()) {
                throw new NoSuchFileException("deleteDirectory 异常");
            }

            //删除文件夹下的所有文件(包括子目录)
            File[] files = dirFile.listFiles();
            for (File file : files) {
                // 删除子文件
                if (file.isFile()) {
                    deleteFile(file);
                } else {
                    // 删除子目录
                    deleteDirectory(file.getAbsolutePath());
                }
            }
            // 删除当前目录
            Files.delete(FileSystems.getDefault().getPath("sPath"));
        } catch (Exception e) {
            logger.error("deleteDirectory 异常", e);
        }
    }


    /**
     * @Description: 检查文件，获取上传信息
     * @Date: 2019/12/7 20:49
     * @param: fileName 文件名
     * @param: request http 请求
     * @Return: 返回文件上传信息
     **/
    public static Map<String, Object> getUplodInfo(MultipartFile fileName, HttpServletRequest request) throws Exception {
        Map<String, Object> resMap = new HashMap<>();
        //获取文件名
        String title = fileName.getOriginalFilename();

        //判断文件名是否为空
        if (isNullFile(fileName) || Strings.isEmpty(title)) {
            throw new NoSuchFileException("文件为空");
        }

        // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        if (title.contains("\\")) {
            title = title.substring(title.lastIndexOf("\\") + 1);
        }

        //获取文件类型
        String type = title.substring(title.lastIndexOf(".") + 1);

        //验证文件类型
        if (!isExistFileType(type)) {
            throw new NoSuchElementException(String.format("文件类型[%s]异常", type));
        }

        // 根据文件类型选择上传目录

        //文件相关信息 + 输入信息
        resMap.put("title", title);
        resMap.put("type", type);
        resMap.put("uploader", request.getParameter("uploader"));
        resMap.put("remark", request.getParameter("remark"));
        resMap.put("size", fileName.getSize());
        resMap.put("hdfsPosition", HdfsConfig.getHdfsPath());

        Map<String, String> map = FileRelated.makeFileName(title);
        resMap.put("uuid", map.get("uuid"));
        resMap.put("uploadTitle", map.get("uploadTitle"));
        resMap.put("serverPosition", chooseUploadDic(type) + map.get("uploadTitle"));
        return resMap;
    }


    /**
     * @Description: 根据文件类型选择文件上传路径
     * @Date: 2019/12/12 18:09
     * @param: type 文件类型
     * @Return: 文件上传路径
     **/
    public static String chooseUploadDic(String type) {
        String serverPosition = null;
        if (EnumUtils.isValidEnum(ImageEnum.class, type.toUpperCase())) {
            serverPosition = "upload/images/";
        } else if (EnumUtils.isValidEnum(VideoEnum.class, type.toUpperCase())) {
            serverPosition = "upload/videos/";
        }
        return serverPosition;
    }

    /**
     * @Description: 将本地缓存文件上传到 HDFS
     * @Date: 2019/12/7 19:52
     * @param: realSavePath 服务器缓存文件的路径
     * @param: saveFileName 上传文件名称
     * @ReturnType: boolean
     **/
    public static boolean uploadToHdfs(String realSavePath, String saveFileName) throws Exception {
        boolean uploadFlag = false;

        // 上传到HDFS中的指定位置
        String hdfsPosition = UPLOAD_HDFS_ADDRESS + saveFileName;

        // 新建文件输入流
        InputStream inputStream = new BufferedInputStream(new FileInputStream(realSavePath));

        // 创建HDFS配置管理类对象，访问HDFS
        Configuration configuration = new Configuration();

        // 构造filesystem对象。第三个参数设置Linux中Hadoop集群的user
        FileSystem fileSystem = FileSystem.get(URI.create(hdfsPosition), configuration, HdfsConfig.getHdfsUser());

        // 创建输出流
        OutputStream outputStream = fileSystem.create(new Path(hdfsPosition), new Progressable() {
            public void progress() {
            }
        });

        try {
            // 文件上传到HDFS--（输入流，输出流，缓冲区大小，关闭数据流）
            IOUtils.copyBytes(inputStream, outputStream, 4096, true);
            uploadFlag = true;
        } catch (Exception e) {
            logger.error("文件上传到 HDFS 异常", e);
        } finally {
            inputStream.close();
            outputStream.close();
        }
        return uploadFlag;
    }


    /**
     * @Description: 上传图像到本地缓存(未用)
     * @Author: Administrator
     * @Tags: @param fileName 上传文件名
     * @Tags: @param saveFileName 保存文件名
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月10日 下午2:23:00
     * @return: String[]
     */
    public static String[] imgFileCache(MultipartFile fileName, String saveFileName) throws Exception {

        // 上传成功标识
        String flag_imgFileCache = "false";

        String[] result = new String[2];

        try {

            //图片数据
            byte[] bytes = fileName.getBytes();

            String realSavePath = FileRelated.makePath(saveFileName, FileRelated.savePath);

            System.out.println("上传文件的保存目录为：" + realSavePath);

            System.out.println("文件缓存的绝对路径为：" + realSavePath + "\\" + saveFileName);

            result[0] = realSavePath;

            File dir = new File(realSavePath);

            //在指定文件夹下保存以含有UUID名称命名的文件
            File file = new File(dir, saveFileName);

            FileOutputStream outputStream = new FileOutputStream(file);

            outputStream.write(bytes);

            flag_imgFileCache = "true";

            outputStream.close();

            result[1] = flag_imgFileCache;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    /**
     * @Description: 本地缓存
     * @Date: 2019/12/7 11:02
     * @param: file 缓存文件
     * @param: title 缓存文件名称
     * @ReturnType: 缓存文件绝对路径
     **/
    public static String fileCache(MultipartFile file, String dic, Map<String, Object> fileInfo) throws Exception {
        File dest = new File(ROOT_PATH + dic + File.separator + fileInfo.get("uploadTitle"));
        try {
            file.transferTo(dest);
            compression((String) fileInfo.get("type"), dest);
        } catch (Exception e) {
            logger.error("文件缓存异常", e);
        }
        return dest.getAbsolutePath();
    }


    /**
     * @Description: 图像压缩
     * @Date: 2019/12/12 18:06
     * @param: imgFile 图像文件
     **/
    public static void imgCompression(File imgFile) {
        try {
            Thumbnails.of(imgFile)
                    .size(100, 50)
                    .toFile(imgFile);
        } catch (Exception e) {
            logger.error("图像压缩异常", e);
        }
    }

    public static void videoCompression(File imgFile) {
        try {
            // TODO LXJ
        } catch (Exception e) {

        }
    }


    /**
     * @Description: 根据文件类型选择压缩
     * @Date: 2019/12/12 18:05
     * @param: type 文件类型
     * @param: file 文件
     **/
    public static void compression(String type, File file) {
        if (EnumUtils.isValidEnum(ImageEnum.class, type.toUpperCase())) {
            imgCompression(file);
        } else if (EnumUtils.isValidEnum(VideoEnum.class, type.toUpperCase())) {
            videoCompression(file);
        } else {
            logger.error(String.format("压缩文件类型非图片或视频！type[%s]", type));
        }
    }


}
