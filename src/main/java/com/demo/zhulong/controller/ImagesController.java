package com.demo.zhulong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.common.CommonResponse;
import com.demo.zhulong.common.enums.ResultCode;
import com.demo.zhulong.config.HdfsConfig;
import com.demo.zhulong.service.ImageService;
import com.demo.zhulong.utils.FileUtils;
import org.apache.directory.api.util.Strings;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);

    @Autowired
    public ImageService imageService;


    /**
     * @Description: 查询图像
     * @Date: 2019/12/9 15:21
     * @param: model
     * @ReturnType: java.lang.String
     **/
    @RequestMapping(value = "/queryImage")
    public String query(Model model) throws Exception {
        List<Images> imageList = imageService.selectAll();
        model.addAttribute("images", imageList);
        return "images";
    }


    /**
     * @Description: 删除图像（服务器 + HDFS）
     * @Date: 2019/12/9 15:49
     * @param: request
     * @ReturnType: java.util.Map<java.lang.String , java.lang.Object>
     **/
    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String params = request.getParameter("params");
        JSONObject paramsJson = JSONObject.parseObject(params);
        String uploadTitle = (String) paramsJson.get("uploadTitle");
        try {
            // 1. 服务器删除
            int delCounts = imageService.deleteImageByUploadTitle(uploadTitle);
            if (delCounts <= 0) {
                logger.error(String.format("服务器删除 images 失败！images uploadTitle: %s", uploadTitle));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            } else {
                logger.info(String.format("服务器删除 images 成功个数：%s", delCounts));
            }

            // 2. TODO HDFS 删除
            Boolean delFlag = imageService.deleteImageFromHdfs(uploadTitle);
            if (Boolean.TRUE.equals(delFlag)) {
                logger.info(String.format("Hadoop 删除 images 成功个数：%s", delCounts));
            } else {
                logger.error(String.format("Hadoop 删除 images 失败！images uploadTitle: %s", uploadTitle));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            }
        } catch (Exception e) {
            logger.error(ResultCode.EXCEPTION.getDesc(), e);
        }
        map.put("result", ResultCode.SUCCESS.getCode());
        return map;
    }


    /**
     * @Description: 修改图像
     * @Date: 2019/12/9 15:21
     * @param: request
     * @ReturnType: java.util.Map<java.lang.String , java.lang.Object>
     **/
    @RequestMapping(value = "/modifyImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify(HttpServletRequest request) throws Exception {
        String params = request.getParameter("params");
        JSONObject paramsJson = JSONObject.parseObject(params);

        String delResult = null;
        Map<String, Object> map = new HashMap<>();
        try {
            int modCounts = imageService.updateImage(paramsJson);
            // 修改失败
            if (modCounts <= 0) {
                logger.error(String.format("修改 images 失败！images: %s", paramsJson.toJSONString()));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            } else {
                logger.info(String.format("修改 images 个数：%s", modCounts));
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
     * @Description: 上传图像（服务器 + HDFS）
     * @Date: 2019/11/8 19:06
     * @Params:
     * @ReturnType:
     **/
    @RequestMapping(value = "/uploadImages")
    public String upload(@RequestParam("fileName") MultipartFile file, HttpServletRequest request, Model model) throws Exception {
        if (Objects.isNull(file) || file.isEmpty() || Strings.isEmpty(file.getOriginalFilename())) {
            logger.error("上传文件为空！");
            model.addAttribute("uploadResult", ResultCode.FAIL.getCode());
            return "uploadImage";
        }

        // 1. 获取文件信息
        Map<String, Object> fileInfo = FileUtils.getUplodInfo(file, request);

        try {
            // 2. 以上传文件的名称（加uuid）缓存文件
            String absolutePath = FileUtils.fileCache(file, "images", fileInfo);

            // 3. 文件上传到 HDFS
//            boolean uploadHdfsFlag = FileUtils.uploadToHdfs(absolutePath, (String) fileInfo.get("uploadTitle"));
//            if (uploadHdfsFlag) {
//                logger.info("上传 HDFS 成功");
//            } else {
//                logger.error(String.format("上传 HDFS 失败，文件：[%s]", (String) fileInfo.get("uploadTitle")));
//            }

            // 4. 数据信息插入数据库
            int insertCount = imageService.insertImage(fileInfo);
            if (insertCount <= 0) {
                logger.error(String.format("上传图像，数据信息插入数据库失败！文件信息：[%s]", JSONObject.toJSON(fileInfo)));
            } else {
                logger.info("上传图像，数据信息插入数据库成功！");
            }

            // 5. 清除本地缓存文件
//            FileUtils.deleteDirectory(absolutePath);

            model.addAttribute("uploadResult", ResultCode.SUCCESS.getCode());
        } catch (Exception e) {
            logger.error("上传图像异常！", e);
            model.addAttribute("uploadResult", ResultCode.EXCEPTION.getCode());
        }
        return "uploadImage";
    }


    /**
     * @Description: 下载图像
     * @Date: 2019/12/8 11:55
     * @param: request
     * @param: response
     * @ReturnType: java.util.Map<java.lang.String , java.lang.Object>
     **/
    @RequestMapping(value = "/downloadImage")
    public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        // HDFS 中存储的文件名
        String fileName = request.getParameter("uploadTitle");
        logger.info(String.format("download image is:[%s]", fileName));


        FSDataInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
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
                logger.error(String.format("资源路径[%s]不存在！", path.toString()));
            } else {
                // 文件输入流
                inputStream = hdfs.open(path);

                // 设置下载文件相关信息---header中只支持ASCII，所以传输的文件名必须是ASCII，
                // 当文件名为中文时，必须要将该中文转换成ASCII，转换格式，防止中文名丢失。
                response.setContentType(request.getServletContext().getMimeType(realFileName));
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(realFileName, "utf-8"));

                // 文件输出流
                outputStream = new BufferedOutputStream(response.getOutputStream());
                // 缓存
                byte[] buffer = new byte[2048];

                int byteRead = 0;
                while ((byteRead = (inputStream.read(buffer))) != -1) {
                    outputStream.write(buffer, 0, byteRead);
                }

                // 刷新缓冲输出流
                outputStream.flush();

            }
        } catch (Exception e) {
            logger.error("图像下载异常！", e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }


    // TODO LXJ 预览图像 优化成 从 Hadoop 中读取输出流

}


