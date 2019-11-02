package com.demo.zhulong.config.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: --------------------------------------
 * @ClassName: RabbitConfig.java
 * @Date: 2019/11/2 16:23
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue(){
        // 发送者和接收者的 queue name 必须一致，不然不能接收
        return new Queue("data");
    }

}
