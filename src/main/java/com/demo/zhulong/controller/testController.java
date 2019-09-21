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
    @RequestMapping(value = "/test")
    public String testThymeleaf() {
        return "test.html";
    }
}
