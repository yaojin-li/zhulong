package com.demo.zhulong.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.common.FileRelated;
import com.demo.zhulong.common.HDFSConstants;
import com.demo.zhulong.common.enums.AudioEnum;
import com.demo.zhulong.common.enums.DocEnum;
import com.demo.zhulong.common.enums.ImageEnum;
import com.demo.zhulong.common.enums.VideoEnum;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public static final Logger logger = Logger.getLogger(FileUtils.class);

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

        if (fileTypes.contains(type)) {
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
    public static boolean deleteOneFile(String filePath) throws Exception {
        boolean flag = false;
        File file = new File(filePath);
        try {
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                // TODO LXJ 文件删除优化
                file.delete();
                flag = true;
            } else {
                logger.info(String.format("删除单个文件失败，filePath[%s]", filePath));
            }
        } catch (Exception e) {
            logger.error("删除单个文件异常", e);
        }
        return flag;
    }


    /**
     * @Description: 根据路径删除指定的目录或文件
     * @Date: 2019/11/21 23:09
     * @param: sPath 要删除的目录或文件
     * @ReturnType: boolean 删除成功返回 true，失败返回 false
     **/
    public static boolean deleteFileOrFolder(String path) throws Exception {
        File file = new File(path);
        // 判断目录或文件是否存在
        if (!file.exists()) {
            logger.error(String.format("待删除文件或目录[%s]不存在", path));
            return false;
        } else {
            // 判断是否为文件
            if (file.isFile()) {
                return deleteOneFile(path);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(path);
            }
        }
    }


    /**
     * @Description: 删除目录（文件夹）以及目录下的文件
     * @Author: Administrator
     * @Tags: @param sPath 被删除目录的文件路径
     * @Tags: @return 目录删除成功返回true，否则返回false
     * @Date: 2017年8月10日 上午11:22:02
     * @return: boolean
     */
    public static boolean deleteDirectory(String sPath) throws Exception {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteOneFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @Description: 检查文件，获取上传信息
     * @Author: Administrator
     * @Tags: @param fileName
     * @Tags: @param request
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月10日 下午2:23:46
     * @return: Object[]
     */
    public static Object[] getUplodInfo(MultipartFile fileName, HttpServletRequest request) throws Exception {

        //存储数据的jsonObject
        JSONObject jsonObject = new JSONObject();

        Object[] res = new Object[2];

        //获取文件名
        String title = fileName.getOriginalFilename();

        //判断文件名是否为空
        if (isNullFile(fileName)) {
            System.out.println("文件名为空");
        }

        // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        title = title.substring(title.lastIndexOf("\\") + 1);
        System.out.println("文件名称为：" + title);

        //获取文件类型
        String type = title.substring(title.lastIndexOf(".") + 1);

        //验证文件类型
        if (!isExistFileType(type)) {
            System.out.println("文件类型错误");
            res[0] = 0;
            return res;
        }

        //文件相关信息 + 输入信息
        jsonObject.put("title", title);
        jsonObject.put("type", type);
        jsonObject.put("user", request.getParameter("user"));
        jsonObject.put("new_title", request.getParameter("new_title"));
        jsonObject.put("description", request.getParameter("description"));
        jsonObject.put("remark", request.getParameter("remark"));
        jsonObject.put("size", fileName.getSize());
        jsonObject.put("create_time", new Timestamp(System.currentTimeMillis()));
        jsonObject.put("position", HDFSConstants.HDFSPath);

        //获得文件的保存名称--saveFileName[0]为UUID，saveFileName[1]为含有UUID的文件名
        String[] saveFileName = FileRelated.makeFileName(title);
        System.out.println("上传文件的保存名称为：" + saveFileName[1]);

        jsonObject.put("video_UUID", saveFileName[0]);
        jsonObject.put("img_UUID", saveFileName[0]);

        System.out.println("-----------------------------------");

        logger.info(JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd"));

        res[0] = jsonObject;
        res[1] = saveFileName[1];

        return res;

    }


}
