package com.demo.zhulong.utils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: redis 分布式锁
 * --------------------------------------
 * @ClassName: RedisTest.java
 * @Date: 2019/11/1 19:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class DistributedLock {
    private RedissonClient redisson = null;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
//    @Value("${spring.redis.password}")
//    private String pwd;
    @Value("${spring.redis.database}")
    private String database;

    public RedissonClient getRedisson() {
        if (redisson == null) {
            Config config = new Config();
//            config.useSingleServer().setAddress("redis://" + host + ":" + port).setDatabase(Integer.parseInt(database)).setPassword(pwd);
            config.useSingleServer().setAddress("redis://" + host + ":" + port).setDatabase(Integer.parseInt(database));
            redisson = Redisson.create(config);
        }
        return redisson;
    }
}

