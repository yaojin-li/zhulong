package com.demo.zhulong;

import com.demo.zhulong.config.RabbitMQ.DataSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: --------------------------------------
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

    @Test
    public void data() throws Exception{
        dataSender.send();
    }


}
