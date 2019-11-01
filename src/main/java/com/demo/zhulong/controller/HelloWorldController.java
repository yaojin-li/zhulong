package com.demo.zhulong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello")
    public String index() {
        return "Hello World spring boot";
    }


    /**
     * @Description: 测试共享 session
     * Spring Session 提供了集群 Session（Clustered Sessions）功能，
     * 默认采用外置的 Redis 来存储 Session 数据，以此来解决 Session 共享的问题。
     * 多台设备中共享 Session：在另一个项目中再次配置一次，启动后自动就进行了 Session 共享。
     * @Date: 2019/11/1 20:13
     * @Params:
     * @ReturnType:
     **/
    @RequestMapping("/uid")
    public String testSessionUid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        logger.info(String.format("输出的 uid：%s", uid));
        logger.info(String.format("此时 session 的 ID 为：", session.getId()));
        return session.getId();
    }

}
