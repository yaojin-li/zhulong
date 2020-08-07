package com.demo.zhulong;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
@MapperScan(basePackages = {"com.demo.zhulong.base.dao.*"})
//@EnableScheduling  // 开启定时器
@EnableAdminServer  //spring boot admin 服务端
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


    /**
     * @Description: 解决上传文件大于 10M 出现连接重置问题。GlobalException 无法捕获。
     * @Date:        2019/11/6 14:50
     * @Params:
     * @ReturnType:
     **/
	@Bean
    public TomcatServletWebServerFactory tomcatEmbedded(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)){
                // -1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }

}
