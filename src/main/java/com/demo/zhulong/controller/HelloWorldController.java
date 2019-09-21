package com.demo.zhulong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @Description: controller 测试类
 * --------------------------------------
 * @ClassName: HelloWorldController.java
 * @Date: 2019/9/17 20:14
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index(){
        return "Hello World spring boot";
    }

}
