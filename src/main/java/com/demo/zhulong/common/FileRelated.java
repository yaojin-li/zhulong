/**
 * @Author: Administrator
 * @Description: TODO
 * @Date: 2017年7月20日 下午2:19:09
 */
package com.demo.zhulong.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.demo.zhulong.common.HDFSConstants;

/**
 * @Description: 文件相关函数
 * --------------------------------------
 * @ClassName: CommonController.java
 * @Date: 2019/11/06 17:49
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class FileRelated {


    // 文件在本地的缓存地址。---将上传的文件存放于WEB-INF目录下，存储到固定目录，不允许外界直接访问，保证上传文件的安全。
    // 若无该路径，在UploadServlet中会自行创建
    // 由于存在临时目录下时，tomcat servers删除后，临时目录及文件丢失
    public static String savePath = "D:\\FileRelated_temp";

    public static final Logger LOGGER = Logger.getLogger(FileRelated.class);







    /**
     * @throws UnsupportedEncodingException
     * @Description: 生成上传文件的文件名。文件名以：uuid+"_"+文件的原始名称
     * @Author: Administrator
     * @Tags: @param filename
     * @Tags: @return
     * @Date: 2017年8月3日 上午11:26:37
     * @return: String[]
     */
    public static String[] makeFileName(String filename) throws Exception {

//		filename = URLEncoder.encode(filename, "utf-8");

        // 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        String[] strArrays = new String[2];
        String uuid = UUID.randomUUID().toString();//文件的UUID
        String uuid_filename = uuid + "_" + filename;//上传文件的唯一文件名

        strArrays[0] = uuid;
        strArrays[1] = uuid_filename;

        System.out.println("文件UUID相关：");
        System.out.println(Arrays.toString(strArrays));

        return strArrays;

    }


    /**
     * @Description: 生成文件路径。 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     * @Tags: @param filename 文件名，要根据文件名生成存储目录
     * @Tags: @param savePath 文件存储路径
     * @Tags: @return 新的存储目录
     * @Author: Administrator
     * @Date: 2017年7月20日 下午12:06:03
     */
    public static String makePath(String filename, String savePath) {

        // 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf; // 0--15
        int dir2 = (hashcode & 0xf0) >> 4; // 0-15

        // 构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3

        // File既可以代表文件也可以代表目录
        File file = new File(dir);

        // 如果目录不存在
        if (!file.exists()) {
            // 创建目录
            file.mkdirs();
        }
        return dir;
    }


    /**
     * @Description: 遍历本地文件系统
     * @Tags: @param file
     * @Tags: @param fileNameMap
     * @Author: Administrator
     * @Date: 2017年7月25日 下午5:21:14
     */
    private static void listfile(File file, HashMap<String, String> fileNameMap) {

        System.out.println(file.getName());

        // 如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            // 列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            // 遍历files[]数组
            for (File f : files) {
                // 递归
                listfile(f, fileNameMap);
            }
        } else {
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             * file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：
             * 9349249849-88343-8344_阿_凡_达.avi
             * 那么file.getName().substring(file.getName().indexOf("_")+1)
             * 处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_") + 1);
            // file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            fileNameMap.put(file.getName(), realName);
        }

    }


    /**
     * @Description: 遍历HDFS文件的测试方法
     * @Tags: @param hdfs
     * @Tags: @param path
     * @Author: Administrator
     * @Date: 2017年7月25日 下午5:03:21
     */
    public static void iteratorShowFiles(FileSystem hdfs, Path path) {
        try {
            if (hdfs == null || path == null) {
                return;
            }
            // 获取文件列表
            FileStatus[] files = hdfs.listStatus(path);

            // 展示文件信息
            for (int i = 0; i < files.length; i++) {
                try {
                    if (files[i].isDirectory()) {
                        System.out.println(">>>" + files[i].getPath() + ", dir owner:" + files[i].getOwner());

                        // 递归调用
                        iteratorShowFiles(hdfs, files[i].getPath());

                    } else if (files[i].isFile()) {
                        System.out.println("   " + files[i].getPath() + ", length:" + files[i].getLen() + ", owner:"
                                + files[i].getOwner());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: 遍历HDFS文件系统
     * @Tags: @param hdfs 文件系统
     * @Tags: @param fileNameMap 文件map
     * @Tags: @param path 文件路径
     * @Author: Administrator
     * @Date: 2017年7月26日 上午11:32:39
     */
    public static void showFiles(FileSystem hdfs, HashMap<String, String> fileNameMap, Path path) {

        try {

            System.out.println(hdfs);

            if (hdfs == null || hdfs.equals("")) {
                return;
            }

            // 获取文件列表，根据path （/） 进行目录分割
            FileStatus[] fileStatus = hdfs.listStatus(path);

            for (int i = 0; i < fileStatus.length; i++) {
                try {

                    // 是目录
                    if (fileStatus[i].isDirectory()) {

                        /** -- 只遍历用户自己的文件夹 -- **/
                        //if (fileStatus[i].getOwner().equals(HDFSConstants.nowUser)) {

                        // 输出目录信息
                        System.out.println(">>>" + fileStatus[i].getPath() + ", dir owner:" + fileStatus[i].getOwner());

                        // 递归---获取当前文件路径，再次遍历目录中的文件
                        showFiles(hdfs, fileNameMap, fileStatus[i].getPath());

                        //}

                        // 是文件
                    } else if (fileStatus[i].isFile()) {

                        /** -- 只遍历用户自己上传的文件 -- **/
                        // 验证文件上传者
                        if (fileStatus[i].getOwner().equals(HDFSConstants.nowUser)) {

                            // 输出文件信息
                            System.out.println("   " + fileStatus[i].getPath() + ", length:" + fileStatus[i].getLen()
                                    + ", owner:" + fileStatus[i].getOwner());

                            // 文件在HDFS中的真实存储路径：路径+文件名
                            Path realPath = fileStatus[i].getPath();

                            // 从path转换为string
                            String realStringPath = realPath.toString();

                            System.out.println(realStringPath);

                            // 截取文件名，除去路径等信息，得到a.jpg
                            String realName = realStringPath.substring(realStringPath.indexOf("_") + 1);

                            System.out.println("文件名：" + realName);

                            // 文件路径、文件名存入HashMap
                            fileNameMap.put(realStringPath, realName);

                        }
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    /**
     * @Description: 在HDFS中为当前用户建立用户名命名的目标文件夹，文件名以：uuid+"_"+用户名
     * @Author: Administrator
     * @Tags: @param fileSystem
     * @Tags: @return
     * @Tags: @throws IOException
     * @Date: 2017年7月27日 下午5:03:26
     * @return:String 新建文件夹位置+名称
     */
    public static String makeFolderOnHDFS(FileSystem fileSystem) throws IOException {

        fileSystem.mkdirs(new Path(HDFSConstants.HDFSAddress + "/" + UUID.randomUUID().toString() + "_" + HDFSConstants.nowUser));

        return HDFSConstants.HDFSAddress + "/" + UUID.randomUUID().toString() + "_" + HDFSConstants.nowUser;

    }


    /**
     * @Description: 将本地缓存文件上传到HDFS
     * @Author: Administrator
     * @Tags: @param realSavePath
     * @Tags: @param saveFileName
     * @Tags: @throws Exception
     * @Date: 2017年8月10日 上午9:38:24
     * @return: void
     */
    public static boolean uploadToHDFS(String realSavePath, String saveFileName) throws Exception {

        // 上传成功标识
        boolean flag = false;

        try {
            // 得到文件的保存目录
            String localSrc = realSavePath + "\\" + saveFileName;

            // 上传到HDFS中的指定位置
            String dst2 = HDFSConstants.HDFSAddress + saveFileName;

            System.out.println("文件在HDFS中的位置：" + dst2);

            // 新建文件输入流
            InputStream in2 = new BufferedInputStream(new FileInputStream(localSrc));

            // 创建HDFS配置管理类对象，通过配置文件hdfs-site.xml以及core-site.xml，访问HDFS
            Configuration conf2 = new Configuration();

            // 构造filesystem对象
            FileSystem fs = FileSystem.get(URI.create(dst2), conf2);

            // 创建输出流
            OutputStream out2 = fs.create(new Path(dst2), new Progressable() {
                public void progress() {
                    System.out.print(".");
                }
            });

            // 文件上传到HDFS--（输入流，输出流，缓冲区大小，关闭数据流）
            IOUtils.copyBytes(in2, out2, 4096, true);

            System.out.println("success");

            flag = true;

            in2.close();
            in2 = null;
            out2.close();
            out2 = null;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return flag;
    }




    /**
     * @Description: 上传图像到本地缓存
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
     * @Description: TODO
     * @Author: Administrator
     * @Tags: @param fileName
     * @Tags: @param saveFileName
     * @Tags: @return
     * @Date: 2017年8月30日 下午2:29:39
     * @return: String []
     */
    public static String[] videoFileCache(MultipartFile fileName, String saveFileName) {

        // 上传成功标识
        String flag_videoFileCache = "false";

        String[] result = new String[2];

        try {

            String realSavePath = FileRelated.makePath(saveFileName, FileRelated.savePath);

            System.out.println("上传文件的保存目录为：" + realSavePath);

            System.out.println("文件缓存的绝对路径为：" + realSavePath + "\\" + saveFileName);

            result[0] = realSavePath;

            File dir = new File(realSavePath);

            // 在指定文件夹下保存以含有UUID名称命名的文件
            File file = new File(dir, saveFileName);

            // 创建一个文件输入流
            InputStream in = fileName.getInputStream();
            // 创建一个文件输出流
            FileOutputStream out = new FileOutputStream(file);
            // 创建一个缓冲区
            byte buffer[] = new byte[1024];
            // 判断输入流中的数据是否已经读完的标识
            int len = 0;
            // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while ((len = in.read(buffer)) > 0) {
                // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write(buffer, 0, len);
            }
            flag_videoFileCache = "true";
            // 关闭输入流
            in.close();
            in = null;
            // 关闭输出流
            out.close();
            out = null;

            result[1] = flag_videoFileCache;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


}












