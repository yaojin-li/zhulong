package com.demo.zhulong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZhuLongApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhuLongApplication.class, args);
	}

}
