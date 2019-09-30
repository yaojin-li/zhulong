package com.demo.zhulong.controller;

import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
@RequestMapping(value = "/imagesController")
public class ImagesController {

    @Autowired
    public ImageService imageService;

    @RequestMapping(value = "/queryImage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView query() throws Exception{
        List<Images> imageList = imageService.selectAll();
        System.out.println(imageList);
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("imageList", imageList);
//        modelAndView.setViewName("example/imgFileList");
        return modelAndView;
    }
}
