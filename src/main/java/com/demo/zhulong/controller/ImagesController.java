package com.demo.zhulong.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.service.ImageService;
import com.demo.zhulong.utils.StringUtils;
import org.apache.directory.api.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ModelAndView query() throws Exception {
        List<Images> imageList = imageService.selectAll();
        logger.info(String.format("查询 images 结果：%s", imageList));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("images", imageList);
        modelAndView.setViewName("images.html");
        return modelAndView;
    }


    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, Model model) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String uuid = request.getParameter("uuid");
        try {
            // 1. 服务器删除
            int delCounts = imageService.deleteImageByUuid(uuid);
            if (delCounts <= 0) {
                logger.error(String.format("服务器删除 images 失败！images uuid: %s", uuid));
                map.put("result", "false");
                return map;
            } else {
                logger.info(String.format("服务器删除 images 个数：%s", delCounts));
            }

            // 2. TODO HDFS 删除

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("result", "success");
        map.put("result", "success");
        return map;
    }


    @RequestMapping(value = "/modifyImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify(HttpServletRequest request) throws Exception {
        String imgModInfo = request.getParameter("imgModInfo");
        Map<String, Object> map = new HashMap<>();
        try {
            int modCounts = imageService.updateImage(imgModInfo);
            // 修改失败
            if (modCounts <= 0) {
                logger.error(String.format("服务器修改 images 失败！images: %s", imgModInfo));
                map.put("result", "false");
                return map;
            } else {
                logger.info(String.format("服务器修改 images 个数：%s", modCounts));
            }

            // TODO HDFS 修改

        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("result", "success");
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
    public ModelAndView upload(@RequestParam("fileName") MultipartFile file) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String uploadRes = "";
        if (Objects.isNull(file) || file.isEmpty() || Strings.isEmpty(file.getOriginalFilename())) {
            modelAndView.setViewName("uploadImage.html");
            return modelAndView;
        }
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

        int size = (int) file.getSize();
        logger.info(String.format("文件[%s] 大小为[%s]", fileName, size));

        String rootPath = "F://test";
        try {
            // 保存文件
            File dest = new File(rootPath + "/" + fileName);
            file.transferTo(dest);
            uploadRes = "true";
        } catch (Exception e) {
            e.printStackTrace();
            uploadRes = "false";
        }
        modelAndView.addObject("uploadResult", uploadRes);
        modelAndView.setViewName("uploadImage.html");
        return modelAndView;
    }




    @RequestMapping(value = "/downloadImage")
    public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String uuid = request.getParameter("uuid");
        try {
            imageService.downloadImage(uuid);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
