package com.demo.zhulong;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com.demo.zhulong.base.dao.*"})
public class ZhuLongApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhuLongApplication.class, args);
	}

}
