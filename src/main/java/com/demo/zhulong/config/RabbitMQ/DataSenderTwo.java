package com.demo.zhulong.config.RabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: --------------------------------------
 * @ClassName: DataSender.java
 * @Date: 2019/11/2 19:25
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class DataSenderTwo {

    private static final Logger logger = LoggerFactory.getLogger(DataSenderTwo.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int count){
        Date dt = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String context = "发送者 2 send " + count + " data. 日期；" + dateFormat.format(dt);
        logger.info(context);
        this.rabbitTemplate.convertAndSend("data", context);
    }

    public void send(){
        Date dt = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String context = "发送者 2 send data. 日期；" + dateFormat.format(dt);
        logger.info(context);
        this.rabbitTemplate.convertAndSend("data", context);
    }

}
