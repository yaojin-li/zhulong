//package com.demo.zhulong;
//
//import com.alibaba.fastjson.JSONObject;
//import com.demo.zhulong.base.beans.Images;
//import com.demo.zhulong.utils.DistributedLock;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.awt.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description: --------------------------------------
// * @ClassName: RedisTest.java
// * @Date: 2019/11/1 13:44
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RedisTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private DistributedLock distributedLock;
//
//    /**
//     * @Description: 测试基本 redis
//     * @Date: 2019/11/1 19:28
//     * @Params:
//     * @ReturnType:
//     **/
//    @Test
//    public void redisTest() throws Exception {
//        stringRedisTemplate.opsForValue().set("aaa", "111");
//        logger.info(stringRedisTemplate.opsForValue().get("aaa"));
//
//        stringRedisTemplate.opsForValue().set("bbb", "123", 3, TimeUnit.SECONDS);
//    }
//
//    /**
//     * @Description: 测试 redis 缓存对象
//     * @Date: 2019/11/1 19:28
//     * @Params:
//     * @ReturnType:
//     **/
//    @Test
//    public void objRedisTest() throws Exception {
//        Images images = new Images();
//        images.setUuid("uuid");
//        images.setTitle("title");
//
//        ValueOperations<String, Images> operations = redisTemplate.opsForValue();
//        operations.set("testImages1", images);
//        operations.set("testImages2", images, 1, TimeUnit.SECONDS);
//        Thread.sleep(1000);
//
//        boolean exists = redisTemplate.hasKey("testImages2");
//        if (exists) {
//            logger.info("testImages2 exist");
//        } else {
//            logger.info("testImages2 not exist");
//        }
//        logger.info("testImages1: " + JSONObject.toJSONString(redisTemplate.opsForValue().get("testImages1")));
//    }
//
//
//    /**
//     * @Description: 测试 redis 分布式锁
//     * @Date: 2019/11/1 19:29
//     * @Params:
//     * @ReturnType:
//     **/
//    @Test
//    public void redisDistributedLockTest() {
//        // 通过此 unionId 加锁
//        String unionId = "TEST_REDIS";
//
//        // redis 分布式锁
//        RedissonClient redisson = distributedLock.getRedisson();
//        RLock lock = redisson.getLock(unionId + "_UNIONID");
//        // 设置锁时间 1 秒，执行 try 中的内容后释放锁
//        lock.lock(1, TimeUnit.SECONDS);
//        try {
//            logger.info("begin redis distributed lock");
//
//            // 业务逻辑
//
//        } catch (Exception e) {
//            logger.error(String.format("异常，原因：", e.toString()));
//        } finally {
//            // 释放锁
//            lock.unlock();
//        }
//    }
//
//
//}
//
//
