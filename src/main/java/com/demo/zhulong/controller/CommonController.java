package com.demo.zhulong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 公共页 controller
 * --------------------------------------
 * @ClassName: CommonController.java
 * @Date: 2019/9/29 17:42
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
public class CommonController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/404")
    public String test404(){
        return "common/404.html";
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
