package com.demo.zhulong.controller;

import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.common.CommonResponse;
import com.demo.zhulong.common.FileRelated;
import com.demo.zhulong.common.enums.ResultCode;
import com.demo.zhulong.config.HdfsConfig;
import com.demo.zhulong.service.ImageService;
import org.apache.directory.api.util.Strings;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: --------------------------------------
 * @ClassName: ImagesController.java
 * @Date: 2019/9/28 23:16
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
public class ImagesController {
    private static final Logger logger = Logger.getLogger(ImagesController.class);

    @Autowired
    public ImageService imageService;

    @RequestMapping(value = "/queryImage")
    public String query(Model model) throws Exception {
        List<Images> imageList = imageService.selectAll();
        logger.info(String.format("查询 images 结果：%s", imageList));
        model.addAttribute("images", imageList);
        return "images";
    }


    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String delResult = null;
        String uuid = request.getParameter("uuid");
        try {
            // 1. 服务器删除
            int delCounts = imageService.deleteImageByUuid(uuid);
            if (delCounts <= 0) {
                logger.error(String.format("服务器删除 images 失败！images uuid: %s", uuid));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            } else {
                logger.info(String.format("服务器删除 images 个数：%s", delCounts));
                delResult = ResultCode.SUCCESS.getCode();
            }

            // 2. TODO HDFS 删除
//            CommonResponse response = imageService.deleteImageFromHdfs(uuid);
//            delResult = response.getResult();

        } catch (Exception e) {
            logger.error(ResultCode.EXCEPTION.getDesc(), e);
        }
        map.put("result", delResult);
        return map;
    }


    @RequestMapping(value = "/modifyImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify(HttpServletRequest request) throws Exception {
        String imgModInfo = request.getParameter("imgModInfo");
        String delResult = null;
        Map<String, Object> map = new HashMap<>();
        try {
            int modCounts = imageService.updateImage(imgModInfo);
            // 修改失败
            if (modCounts <= 0) {
                logger.error(String.format("服务器修改 images 失败！images: %s", imgModInfo));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            } else {
                logger.info(String.format("服务器修改 images 个数：%s", modCounts));
                delResult = ResultCode.SUCCESS.getCode();
            }

        } catch (Exception e) {
            logger.error("修改图像信息异常！", e);
        }
        map.put("result", delResult);
        return map;
    }


    /**
     * @Description: 正常访问 图像上传页面
     * @Date: 2019/11/8 19:05
     * @Params:
     * @ReturnType:
     **/
    @RequestMapping(value = "/uploadImage")
    public String uploadImagePage() {
        return "/uploadImage";
    }


    /**
     * @Description: 上传图像
     * @Date: 2019/11/8 19:06
     * @Params:
     * @ReturnType:
     **/
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("fileName") MultipartFile file, Model model) throws Exception {
        logger.info("upload file begin...");
        String uploadRes = "false";
        if (Objects.isNull(file) || file.isEmpty() || Strings.isEmpty(file.getOriginalFilename())) {
            logger.error("上传文件为空！");
            model.addAttribute("uploadResult", uploadRes);
            return "uploadImage.html";
        }

        // 1. 获取文件信息
//        res = FileRelated.getUplodInfo(fileName, request);

        String fileName = file.getOriginalFilename();
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

        int size = (int) file.getSize();
        logger.info(String.format("文件[%s] 大小为[%s]", fileName, size));

        String rootPath = "F://test";

        try {
            // 2. 文件缓存
            File dest = new File(rootPath + File.separator + fileName);
            file.transferTo(dest);
            uploadRes = "true";

//
//            // 3. 文件上传到HDFS
//            boolean flag_uploadToHDFS = FileRelated.uploadToHDFS(realSavePath, saveFileName);
//            if (flag_uploadToHDFS) {
//                System.out.println("上传HDFS成功。");
//            }else {
//                System.out.println("上传HDFS失败。");
//            }
//
//            // 4. 清除本地缓存文件
//            boolean flag_delAllFile = FileRelated.DeleteFolder(realSavePath);
//            if (flag_delAllFile) {
//                System.out.println("清除本地缓存文件成功");
//            }else {
//                System.out.println("清除本地缓存文件失败");
//            }


        } catch (Exception e) {
            logger.error("上传图像异常！", e);
        }
        model.addAttribute("uploadResult", uploadRes);
        return "uploadImage.html";
    }


    @RequestMapping(value = "/downloadImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        logger.info("begin download image...");
        request.setCharacterEncoding("utf-8");
        //
        String imgUuid = request.getParameter("uuid");
        String title = request.getParameter("title");

        // HDFS 中存储的文件名
        String fileName = imgUuid + "_" + title;
        logger.info(String.format("download image is:[%s]", fileName));

        try {
            // 创建配置器
            Configuration config = new Configuration();

            // 创建HDFS filesystem实例对象
            FileSystem hdfs = FileSystem.get(new URI(HdfsConfig.getMasterIpPort()), config, HdfsConfig.getHdfsUser());

            // 拼接存储路径与文件名的完整路径
            fileName = HdfsConfig.getHdfsPath() + fileName;

            // 获得文件名 TODO LXJ 文件名获取方式待优化
            String realFileName = fileName.substring(fileName.indexOf("_") + 1);

            // 以文件名获得文件名的路径
            Path path = new Path(fileName);

            // 如果文件不存在
            if (!hdfs.exists(path)) {
                logger.info(String.format("资源路径[%s]不存在！", path.toString()));
                map.put("result", "false");
                map.put("message", "您要下载的资源不存在！！");
                return map;
            } else {
                // 文件输入流
                FSDataInputStream inputStream = hdfs.open(path);

                // 设置下载文件相关信息---header中只支持ASCII，所以传输的文件名必须是ASCII，
                // 当文件名为中文时，必须要将该中文转换成ASCII，转换格式，防止中文名丢失。
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(realFileName, "utf-8"));

                // 文件输出流
                BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

                // 缓存
                byte[] buffer = new byte[2048];

                int byteRead = 0;
                while ((byteRead = (inputStream.read(buffer, 0, buffer.length))) != -1) {
                    outputStream.write(buffer, 0, byteRead);
                }

                // 刷新缓冲输出流
                outputStream.flush();
                inputStream.close();
                outputStream.close();
            }
        } catch (Exception e) {
            logger.error("下载文件异常！", e);
        }

        logger.info(String.format("下载文件[%s]", fileName));
        map.put("result", "success");
        return map;
    }


}
