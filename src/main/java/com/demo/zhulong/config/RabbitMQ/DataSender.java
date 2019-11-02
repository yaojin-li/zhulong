package com.demo.zhulong.config.RabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: --------------------------------------
 * @ClassName: DataSender.java
 * @Date: 2019/11/2 16:25
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class DataSender {

    private static final Logger logger = LoggerFactory.getLogger(DataSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "data" + new Date();
        logger.info("Sender:" + context);
        this.rabbitTemplate.convertAndSend(context);
    }
}
