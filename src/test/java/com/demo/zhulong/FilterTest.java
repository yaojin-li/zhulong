package com.demo.zhulong;

import com.demo.zhulong.filter.WebConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 测试过滤器
 * --------------------------------------
 * @ClassName: FilterTest.java
 * @Date: 2019/9/17 20:53
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterTest {

    @Autowired
    WebConfiguration webConfiguration;

    @Test
    public void filterTest() throws Exception{
        System.out.println(webConfiguration.testFilterRegistration());
        System.out.println(webConfiguration.remoteIpFilter());
    }
}
