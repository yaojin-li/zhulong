package com.demo.zhulong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: --------------------------------------
 * @ClassName: TablesController.java
 * @Date: 2019/9/26 19:49
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
public class TablesController {
    @RequestMapping(value = "/images")
    public String index() {
        return "images.html";
    }
}
