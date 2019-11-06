package com.demo.zhulong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description: --------------------------------------
 * @ClassName: UploadFileTest.java
 * @Date: 2019/11/6 15:02
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadFileTest {

    @Test
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:template/uploadResult.html";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("" + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "Uploaded file '" + file.getOriginalFilename() + "' successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:template/uploadResult.html";
    }

}
