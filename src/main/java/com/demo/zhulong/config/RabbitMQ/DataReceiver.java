package com.demo.zhulong.config.RabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: --------------------------------------
 * @ClassName: DataReceiver.java
 * @Date: 2019/11/2 16:26
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
@RabbitListener(queues = "data")
public class DataReceiver {

    private static final Logger logger = LoggerFactory.getLogger(DataReceiver.class);

    @RabbitHandler
    public void process(String data){
        logger.info("Receiver:" + data);
    }
}
