package com.demo.zhulong;

import com.demo.zhulong.config.RabbitMQ.DataReceiver;
import com.demo.zhulong.config.RabbitMQ.DataReceiverTwo;
import com.demo.zhulong.config.RabbitMQ.DataSender;
import com.demo.zhulong.config.RabbitMQ.DataSenderTwo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * 一对多与多对多发送，接收端会均匀接收到消息
 * --------------------------------------
 * @ClassName: RabbitMQTest.java
 * @Date: 2019/11/2 16:39
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

    public static final Logger logger = LoggerFactory.getLogger(RabbitMQTest.class);

    @Autowired
    private DataSender dataSender;

    @Autowired
    private DataSenderTwo dataSenderTwo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: 测试一对一 RabbitMQ
     * @Date:        2019/11/4 19:17
     * @Params:
     * @ReturnType:
     **/
    @Test
    public void data() throws Exception{
        dataSender.send();
    }


    /**
     * @Description: 测试一对多 RabbitMQ
     * @Date:        2019/11/4 19:24
     * @Params:
     * @ReturnType:
     **/
    @Test
    public void oneToMany() throws Exception{
        for (int i = 0; i < 100; i++) {
            dataSender.send(i);
        }
    }


    /**
     * @Description: 测试多对多 RabbitMQ
     * @Date:        2019/11/4 19:49
     * @Params:
     * @ReturnType:
     **/
    @Test
    public void manyToMany() throws Exception{
        for (int i = 0; i < 100; i++) {
            dataSender.send(i);
            dataSenderTwo.send(i);
        }
    }


    @Test
    public void send() {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
    }

}
