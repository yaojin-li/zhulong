package com.demo.zhulong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
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

    @RequestMapping(value = "/queryImage")
    public String query(Model model) throws Exception {
        List<Images> imageList = imageService.selectAll();
        model.addAttribute("images", imageList);
        return "images";
    }


    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String delResult = null;
        String params = request.getParameter("params");
        JSONObject paramsJson = JSONObject.parseObject(params);
        String uuid = (String) paramsJson.get("uuid");
        try {
            // 1. 服务器删除
            int delCounts = imageService.deleteImageByUuid(uuid);
            if (delCounts <= 0) {
                logger.error(String.format("数据库删除 images 失败！images uuid: %s", uuid));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            } else {
                logger.info(String.format("数据库删除 images 个数：%s", delCounts));
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
        String params = request.getParameter("params");
        JSONObject paramsJson = JSONObject.parseObject(params);

        String delResult = null;
        Map<String, Object> map = new HashMap<>();
        try {
            int modCounts = imageService.updateImage(paramsJson);
            // 修改失败
            if (modCounts <= 0) {
                logger.error(String.format("服务器修改 images 失败！images: %s", paramsJson.toJSONString()));
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
            String absolutePath = FileUtils.fileCache(file, (String) fileInfo.get("uploadTitle"));

            // 3. 文件上传到 HDFS
            boolean uploadHdfsFlag = FileUtils.uploadToHdfs(absolutePath, (String) fileInfo.get("uploadTitle"));
            if (uploadHdfsFlag) {
                logger.info("上传 HDFS 成功");
            }else {
                logger.error(String.format("上传 HDFS 失败，文件：[%s]", (String) fileInfo.get("uploadTitle")));
            }

//            // 4. 数据信息插入数据库
//            int insertCount = imageService.insertImage(fileInfo);
//            if (insertCount <= 0) {
//                logger.error(String.format("上传图像，数据信息插入数据库失败！文件信息：[%s]", JSONObject.toJSON(fileInfo)));
//            } else {
//                logger.info("上传图像，数据信息插入数据库成功！");
//            }

            // 5. 清除本地缓存文件
//            FileUtils.deleteDirectory(absolutePath);

            model.addAttribute("uploadResult", ResultCode.SUCCESS.getCode());
        } catch (Exception e) {
            logger.error("上传图像异常！", e);
            model.addAttribute("uploadResult", ResultCode.EXCEPTION.getCode());
        }
        return "uploadImage";
    }


    @RequestMapping(value = "/downloadImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        request.setCharacterEncoding("utf-8");
        //
        String params = request.getParameter("params");
        JSONObject paramsJson = JSONObject.parseObject(params);

        // HDFS 中存储的文件名
        String fileName = (String) paramsJson.get("uploadTitle");
        logger.info(String.format("download image is:[%s]", fileName));

        try {
            // 创建配置器
            Configuration config = new Configuration();

            // 创建HDFS filesystem实例对象
            FileSystem hdfs = FileSystem.get(new URI(HdfsConfig.getSlaveOneIpPort()), config, HdfsConfig.getHdfsUser());

            // 拼接存储路径与文件名的完整路径
            fileName = HdfsConfig.getHdfsPath() + fileName;

            // 获得文件名 TODO LXJ 文件名获取方式待优化
            String realFileName = fileName.substring(fileName.indexOf("_") + 1);

            // 以文件名获得文件名的路径
            Path path = new Path(fileName);

            // 如果文件不存在
            if (!hdfs.exists(path)) {
                logger.error(String.format("资源路径[%s]不存在！", path.toString()));
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
        }finally {

        }

        logger.info(String.format("下载文件[%s]", fileName));
        map.put("result", "success");
        return map;
    }

}




