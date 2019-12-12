package com.demo.zhulong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 配置虚拟文件路径的映射
 * 本地新建缓存目录：
 * E:\zhulong\src\main\resources\static\upload\images
 * --------------------------------------
 * @ClassName: FileConfig.java
 * @Date: 2019/12/12 19:18
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///E:/zhulong/src/main/resources/static/upload/");
    }
}
