package com.demo.zhulong.utils;

import com.demo.zhulong.common.enums.AudioEnum;
import com.demo.zhulong.common.enums.DocEnum;
import com.demo.zhulong.common.enums.ImageEnum;
import com.demo.zhulong.common.enums.VideoEnum;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: FileUtils.java
 * @Date: 2019/10/11 18:15
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class FileUtils {
    public static final Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * @Description: 判断文件类型
     * @Date:        2019/10/11 17:43
     * @Params:
     * @ReturnType:
     **/
    public static boolean isExistFileType(String type) {
        List<String> fileTypes = new ArrayList<String>();
        // 文件
        for (DocEnum docEnum:DocEnum.values()){
            fileTypes.add(docEnum.toString());
        }
        // 音频
        for (AudioEnum audioEnum:AudioEnum.values()){
            fileTypes.add(audioEnum.toString());
        }
        // 图片
        for (ImageEnum imageEnum:ImageEnum.values()){
            fileTypes.add(imageEnum.toString());
        }
        // 视频
        for (VideoEnum videoEnum:VideoEnum.values()){
            fileTypes.add(videoEnum.toString());
        }

        if (fileTypes.contains(type)){
            return true;
        }else {
            logger.error(String.format("未包含的媒体类型: %s", type));
            return false;
        }
    }


    /**
     * @Description: 判断上传文件是否为空
     * @Date:        2019/10/11 17:46
     * @Params:
     * @ReturnType:
     **/
    // TODO 代码优化
    public static boolean fileNameIsNull(MultipartFile fileName){

        System.out.println("判断文件名是否为空。");

        if (fileName == null || fileName.toString().trim().equals("")) {
            System.out.println("文件名为空！");
            return true;
        }

        System.out.println("文件名不为空...");
        return false;
    }

}
