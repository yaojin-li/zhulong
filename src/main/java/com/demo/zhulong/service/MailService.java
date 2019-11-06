package com.demo.zhulong.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Description: --------------------------------------
 * @ClassName: MailService.java
 * @Date: 2019/11/6 9:45
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String sender;

    /**
     * @Description: 邮件简单测试
     * @Date:        2019/11/6 11:52
     * @Params:
     * @ReturnType:
     **/
    public void sendSimpleMail(String receiver, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setSubject(subject);
        message.setTo(receiver);
        message.setText(content);

        try{
            mailSender.send(message);
            logger.info("简单邮件已发送");
        }catch (Exception e){
            logger.error("简单邮件发送异常", e);
        }
    }


    /**
     * @Description: 发送 html 格式邮件
     * @Date:        2019/11/6 11:52
     * @Params:
     * @ReturnType:
     **/
    public void sendHtmlMail(String receiver, String subject, String content) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();

        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(content, true);

        try {
            mailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }


    /**
     * @Description: 附带附件
     * @Date:        2019/11/6 12:00
     * @Params:
     * @ReturnType:
     **/
    public void sendAttachmentsMail(String receiver, String subject, String content, String filePath) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));

            // 添加多个附件，多个 helper.addAttachment(fileName, file)
            helper.addAttachment(fileName, file);
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }


    
    /**
     * @Description: 发送静态资源文件
     * @Date:        2019/11/6 13:31
     * @Params:      
     * @ReturnType:  
     **/
    public void sendInlineResourceMail(String receiver, String subject, String content, String rscPath, String rscId) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }

}
