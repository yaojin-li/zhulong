package com.demo.zhulong;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.mongodb.core.query.Query;


/**
 * @Description: 测试 MongoDB
 * 1. 需要安装 MongoDB 服务后新建用户与权限设置；
 * 2. 在 application-dev.properties 文件中指定 MongoDB 配置；
 * 3. 参考链接：https://blog.csdn.net/HeyShHeyou/article/details/102874096
 * --------------------------------------
 * @ClassName: MongoDBTest.java
 * @Date: 2019/11/2 14:30
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTest {
    private static final Logger logger = LoggerFactory.getLogger(MongoDBTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void mongoDBTest() {
        Images images = new Images();
        images.setId(123456789);
        images.setTitle("mongoTitle");
        images.setUuid("2222222");
        images.setUploader("测试MongoDB");

        // 保存
        mongoTemplate.save(images);


        // 根据名称查询
        String title = "mongoTitle";
        Query query = new Query(Criteria.where("title").is(title));
        logger.info(String.format("查询结果：%s", JSONObject.toJSON(mongoTemplate.findOne(query, Images.class))));


        // 更新
        Query query2 = new Query(Criteria.where("title").is(title));
        Update update = new Update().set("uuid", "33333").set("uploader", "更新MongoDB");
        // 更新查询结果集的第一条
        mongoTemplate.updateFirst(query2, update, Images.class);
        // 更新所有查询结果
        // mongoTemplate.updateMulti(query2, update, Images.class);
        logger.info(String.format("更新结果：%s", JSONObject.toJSON(mongoTemplate.findOne(query2, Images.class))));


        // 删除
        String delTitle = "mongoTitle";
        Query query1 = new Query(Criteria.where("title").is(delTitle));
        mongoTemplate.remove(query, Images.class);
        logger.info(String.format("删除后结果：%s", JSONObject.toJSON(mongoTemplate.findOne(query1, Images.class))));

    }

}
