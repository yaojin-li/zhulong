package com.demo.zhulong.controller;

import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.apache.log4j.Logger;
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
public class ImagesController {
    private static final Logger logger = Logger.getLogger(ImagesController.class);

    @Autowired
    public ImageService imageService;

    @RequestMapping(value = "/queryImage")
    public ModelAndView query() throws Exception{
        List<Images> imageList = imageService.selectAll();
        logger.info(String.format("查询 images 结果：%s", imageList));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("images", imageList);
        modelAndView.setViewName("images.html");
        return modelAndView;
    }
}
