package com.demo.zhulong.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @Description: Druid 数据源配置文件
 * --------------------------------------
 * @ClassName: DruidDBConfig.java
 * @Date: 2019/9/30 16:56
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/

@Configuration
@MapperScan(basePackages = {"com.demo.zhulong.base.dao.*"},
        sqlSessionFactoryRef = "mysqlOneSqlSessionFactory")
public class DruidDBConfig {

    // 指定 mapping 文件地址
    private static final String MAPPER_LOCAL = "classpath*:com/demo/zhulong/base/mapping/*.xml";

    /**
     * 读取配置文件中的数据库配置
     * spring.datasource.primary.url
     * spring.datasource.primary.username=root
     * spring.datasource.primary.password=123456789
     * ...
     * 等只取公共部分 spring.datasource.primary
     * */
    @ConfigurationProperties("spring.datasource.primary")
    // 自动装配时当出现多个 Bean 时，首选含有注解 @Primary 的 Bean，否则将抛出异常
    @Primary
    // Bean 的名称与 masterSqlSessionFactory 的注解 @Qualifier 中相同
    @Bean(name = "mysqlOneDataSource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "mysqlOneTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        /**
         * DataSourceTransactionManager 提供了很多事务控制方法，交由 spring 管理；
         * spring 底层进行事务控制时就可以调用这个对象里面相应的方法。
         * 但是其依赖于一个数据源对象 dataSource
         * */
        return new DataSourceTransactionManager(druidDataSource());
    }

    @Bean(name = "mysqlOneSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("mysqlOneDataSource") DataSource dataSource) throws Exception {
        // 在 Spring 环境中新建 sessionFactoryBean，然后可以通过依赖注入将 SqlSessionFactory 传递给基于 MyBatis 的 DAO。
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
        // 通过设置 DataSource 与 mapping 中的 XML 文件，构建 sessionFactory 实例
        return sessionFactoryBean.getObject();
    }

}
