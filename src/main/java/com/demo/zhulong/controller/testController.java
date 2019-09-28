package com.demo.zhulong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: --------------------------------------
 * @ClassName: testController.java
 * @Date: 2019/9/21 18:50
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
public class testController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/test")
    public String testThymeleaf() {
        return "test.html";
    }

    @RequestMapping(value = "/404")
    public String test404(){
        return "404.html";
    }

    @RequestMapping(value = "/buttons")
    public String testButton(){
        return "buttons.html";
    }

    @RequestMapping(value = "/cards")
    public String testCards(){
        return "cards.html";
    }
}