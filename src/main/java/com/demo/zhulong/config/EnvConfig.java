package com.demo.zhulong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description: springboot 读取 properties 配置文件中的配置项
 * --------------------------------------
 * @ClassName: EnvConfig.java
 * @Date: 2019/9/25 18:30
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class EnvConfig {

    @Autowired
    private Environment environment;

    public String getLoggingFile(){
        return environment.getProperty("logging.file");
    }


}
