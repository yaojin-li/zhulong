package com.demo.zhulong;

import com.demo.zhulong.config.dataSource.DruidDBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description: --------------------------------------
 * @ClassName: DatasourceTest.java
 * @Date: 2019/9/30 10:56
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceTest {
    @Autowired
    DataSourceProperties dataSourceProperties;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() throws Exception{
        // 获取配置的数据源
        DruidDBConfig druidDBConfig = new DruidDBConfig();
        // 查看配置数据源信息
        System.out.println(druidDBConfig);
        System.out.println(druidDBConfig.getClass().getName());
        System.out.println(dataSourceProperties);
        //执行SQL,输出查到的数据
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(druidDBConfig.druidDataSource());
//        List<?> resultList = jdbcTemplate.queryForList("select * from images");
//        System.out.println("===>>>>>>>>>>>" + JSON.toJSONString(resultList));
    }
}