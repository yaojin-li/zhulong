package com.demo.zhulong.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Videos;
import com.demo.zhulong.common.enums.ResultCode;
import com.demo.zhulong.config.HdfsConfig;
import com.demo.zhulong.service.VideoService;
import com.demo.zhulong.utils.FileUtils;
import org.apache.directory.api.util.Strings;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
 * @ClassName: VideosController.java
 * @Date: 2019/9/28 23:16
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
public class VideosController {
    private static final Logger logger = LoggerFactory.getLogger(VideosController.class);

    @Autowired
    public VideoService videoService;


    /**
     * @Description: 查询视频
     * @Date: 2019/12/9 15:21
     * @param: model
     * @ReturnType: java.lang.String
     **/
    @RequestMapping(value = "/queryVideo")
    public String query(Model model) throws Exception {
        List<Videos> videoList = videoService.selectAll();
        model.addAttribute("videos", videoList);
        return "videos";
    }


    /**
     * @Description: 删除视频（服务器 + HDFS）
     * @Date: 2019/12/9 15:49
     * @param: request
     * @ReturnType: java.util.Map<java.lang.String , java.lang.Object>
     **/
    @RequestMapping(value = "/deleteVideos", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String params = request.getParameter("params");
        JSONObject paramsJson = JSONObject.parseObject(params);
        String uploadTitle = (String) paramsJson.get("uploadTitle");
        try {
            // 1. 服务器删除
            int delCounts = videoService.deleteVideoByUploadTitle(uploadTitle);
            if (delCounts <= 0) {
                logger.error(String.format("服务器删除 videos 失败！videos uploadTitle: %s", uploadTitle));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            } else {
                logger.info(String.format("服务器删除 videos 成功个数：%s", delCounts));
            }

//            // 2. TODO HDFS 删除
//            Boolean delFlag = videoService.deleteVideoFromHdfs(uploadTitle);
//            if (Boolean.TRUE.equals(delFlag)) {
//                logger.info(String.format("Hadoop 删除 videos 成功个数：%s", delCounts));
//            } else {
//                logger.error(String.format("Hadoop 删除 videos 失败！videos uploadTitle: %s", uploadTitle));
//                map.put("result", ResultCode.FAIL.getCode());
//                return map;
//            }
        } catch (Exception e) {
            logger.error(ResultCode.EXCEPTION.getDesc(), e);
        }
        map.put("result", ResultCode.SUCCESS.getCode());
        return map;
    }


    /**
     * @Description: 修改视频
     * @Date: 2019/12/9 15:21
     * @param: request
     * @ReturnType: java.util.Map<java.lang.String , java.lang.Object>
     **/
    @RequestMapping(value = "/modifyVideo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify(HttpServletRequest request) throws Exception {
        String params = request.getParameter("params");
        JSONObject paramsJson = JSONObject.parseObject(params);

        String delResult = null;
        Map<String, Object> map = new HashMap<>();
        try {
            int modCounts = videoService.updateVideo(paramsJson);
            // 修改失败
            if (modCounts <= 0) {
                logger.error(String.format("修改 videos 失败！videos: %s", paramsJson.toJSONString()));
                map.put("result", ResultCode.FAIL.getCode());
                return map;
            } else {
                logger.info(String.format("修改 videos 个数：%s", modCounts));
                delResult = ResultCode.SUCCESS.getCode();
            }
        } catch (Exception e) {
            logger.error("修改视频信息异常！", e);
        }
        map.put("result", delResult);
        return map;
    }


    /**
     * @Description: 正常访问 视频上传页面
     * @Date: 2019/11/8 19:05
     * @Params:
     * @ReturnType:
     **/
    @RequestMapping(value = "/uploadVideo")
    public String uploadImagePage() {
        return "/uploadVideo";
    }


    /**
     * @Description: 上传视频（服务器 + HDFS）
     * @Date: 2019/11/8 19:06
     * @Params:
     * @ReturnType:
     **/
    @RequestMapping(value = "/uploadVideos")
    public String uploadVideos(@RequestParam("fileName") MultipartFile file, HttpServletRequest request, Model model) throws Exception {
        if (Objects.isNull(file) || file.isEmpty() || Strings.isEmpty(file.getOriginalFilename())) {
            logger.error("上传文件为空！");
            model.addAttribute("uploadResult", ResultCode.FAIL.getCode());
            return "uploadVideo";
        }

        // 1. 获取文件信息
        Map<String, Object> fileInfo = FileUtils.getUplodInfo(file, request);

        try {
            // 2. 以上传文件的名称（加uuid）缓存文件
            String absolutePath = FileUtils.fileCache(file, "videos", fileInfo);

//            // 3. TODO LXJ 文件上传到 HDFS
//            boolean uploadHdfsFlag = FileUtils.uploadToHdfs(absolutePath, (String) fileInfo.get("uploadTitle"));
//            if (uploadHdfsFlag) {
//                logger.info("上传 HDFS 成功");
//            } else {
//                logger.error(String.format("上传 HDFS 失败，文件：[%s]", (String) fileInfo.get("uploadTitle")));
//            }

            // 4. 数据信息插入数据库
            int insertCount = videoService.insertVideo(fileInfo);
            if (insertCount <= 0) {
                logger.error(String.format("上传视频，数据信息插入数据库失败！文件信息：[%s]", JSONObject.toJSON(fileInfo)));
            } else {
                logger.info("上传视频，数据信息插入数据库成功！");
            }

            model.addAttribute("uploadResult", ResultCode.SUCCESS.getCode());
        } catch (Exception e) {
            logger.error("上传视频异常！", e);
            model.addAttribute("uploadResult", ResultCode.EXCEPTION.getCode());
        }
        return "uploadImage";
    }


    /**
     * @Description: 下载视频
     * @Date: 2019/12/8 11:55
     * @param: request
     * @param: response
     * @ReturnType: java.util.Map<java.lang.String , java.lang.Object>
     **/
    @RequestMapping(value = "/downloadVideo")
    public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        // HDFS 中存储的文件名
        String fileName = request.getParameter("uploadTitle");
        logger.info(String.format("download video is:[%s]", fileName));


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
            logger.error("视频下载异常！", e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}


