package com.demo.zhulong.controller;

import com.demo.zhulong.base.beans.Videos;
import com.demo.zhulong.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: VideosController.java
 * @Date: 2019/9/30 23:36
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
@RequestMapping(value = "/videoController")
public class VideosController {

    @Autowired
    public VideoService videoService;

    @RequestMapping(value = "/queryVideo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView query() throws Exception{
        List<Videos> imageList = videoService.selectAll();
        System.out.println(imageList);
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
