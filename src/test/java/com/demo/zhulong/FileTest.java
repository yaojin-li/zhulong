package com.demo.zhulong;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description: 测试图片压缩
 * --------------------------------------
 * @ClassName: FileTest.java
 * @Date: 2019/12/10 19:55
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Test
    public void compressFile() throws Exception {
        Thumbnails.of("E:/test/test.png")
                .scale(0.25)
                .toFile("E:/test/1/test3.png");
    }

    @Test
    public void compressFileStream() throws Exception {
        InputStream inputStream = new FileInputStream("E:\\zhulong\\src\\main\\resources\\static\\upload\\images\\e051b7de-be4d-4987-a439-c5f05dac377c_Capture001.png");
        Thumbnails.of(inputStream)
//                .outputQuality(0.25)
//                .scale(0.25)
                .size(100, 50)
                .toFile("E:\\zhulong\\src\\main\\resources\\static\\upload\\images\\e051b7de-be4d-4987-a439-c5f05dac377c_Capture001.png");
    }
}
