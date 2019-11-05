package com.demo.zhulong;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com.demo.zhulong.base.dao.*"})
//@EnableScheduling  // 开启定时器
public class ZhuLongApplication extends SpringBootServletInitializer {

    /**
     * @Description: 注册启动类
     * @Date:        2019/11/5 22:15
     * @Params:
     * @ReturnType:
     **/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ZhuLongApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ZhuLongApplication.class, args);
	}

}
