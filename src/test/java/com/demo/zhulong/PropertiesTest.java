package com.demo.zhulong;

import com.demo.zhulong.config.ConfigProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 测试配置
 * --------------------------------------
 * @ClassName: propertiesTest.java
 * @Date: 2019/9/17 20:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Autowired
    ConfigProperty configProperty;

    @Test
    public void configPropertyTest(){
        System.out.println(configProperty.getProjectName());
        System.out.println(configProperty.getAuthorName());
    }

}
