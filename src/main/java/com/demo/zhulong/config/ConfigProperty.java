package com.demo.zhulong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 配置文件
 * --------------------------------------
 * @ClassName: ConfigProperty.java
 * @Date: 2019/9/17 20:37
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class ConfigProperty {
    @Value("${com.demo.project.name}")
    private String projectName;

    @Value("${com.demo.author.name}")
    private String authorName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
