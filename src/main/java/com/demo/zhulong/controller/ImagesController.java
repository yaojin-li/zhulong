package com.demo.zhulong.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                map.put("result", "failure");
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
                map.put("result", "failure");
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
