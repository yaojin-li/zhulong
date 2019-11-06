package com.demo.zhulong;

import com.demo.zhulong.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @Description: --------------------------------------
 * @ClassName: MailTest.java
 * @Date: 2019/11/6 10:21
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("lixj_zj@163.com", "test simple mail", "test this simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("lixj_zj@163.com", "test html mail", content);
    }


    @Test
    public void sendAttachmentsMail() throws Exception {
        String filePath = "C:\\Users\\tebon\\Desktop\\11111.png";
        mailService.sendAttachmentsMail("lixj_zj@163.com", "test html mail", "附件", filePath);
    }


    @Test
    public void sendInlineResourceMail() throws Exception {
        String rscId = "neo006";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\tebon\\Desktop\\11111.png";

        mailService.sendInlineResourceMail("lixj_zj@163.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() throws Exception{
        Context context = new Context();
        context.setVariable("id", "98884259");
        // 选择模板文件，以 templates 目录为根目录
        String emailContent = templateEngine.process("template/emailTemplate.html", context);
        mailService.sendHtmlMail("lixj_zj@163.com", "模板邮件", emailContent);
    }


}
