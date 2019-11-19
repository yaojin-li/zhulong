package com.demo.zhulong.utils;

import com.demo.zhulong.common.enums.AudioEnum;
import com.demo.zhulong.common.enums.DocEnum;
import com.demo.zhulong.common.enums.ImageEnum;
import com.demo.zhulong.common.enums.VideoEnum;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
     * @Date: 2019/11/19 23:17
     * @Params: [type] 文件后缀 eg. png
     * @ReturnType: boolean
     **/
    public static boolean isExistFileType(String type) {
        List<String> fileTypes = new ArrayList<String>();
        // 文件
        for (DocEnum docEnum : DocEnum.values()) {
            fileTypes.add(docEnum.toString());
        }
        // 音频
        for (AudioEnum audioEnum : AudioEnum.values()) {
            fileTypes.add(audioEnum.toString());
        }
        // 图片
        for (ImageEnum imageEnum : ImageEnum.values()) {
            fileTypes.add(imageEnum.toString());
        }
        // 视频
        for (VideoEnum videoEnum : VideoEnum.values()) {
            fileTypes.add(videoEnum.toString());
        }

        if (fileTypes.contains(type)) {
            return true;
        } else {
            logger.error(String.format("未包含的媒体类型: %s", type));
            return false;
        }
    }


    /**
     * @Description: 判断文件是否为空
     * @Date: 2019/11/19 23:22
     * @Params: [fileName] 文件
     * @ReturnType: boolean
     **/
    public static boolean isNullFile(MultipartFile fileName) {
        return Objects.isNull(fileName) || fileName.isEmpty();
    }


    /**
     * @Description: 删除单个文件
     * @Date:        2019/11/19 23:27
     * @Params:      [filePath] 被删除文件的地址 eg. c:/test.doc
     * @ReturnType:  boolean 单个文件删除成功返回true，否则返回false
     **/
    public static boolean deleteOneFile(String filePath) throws Exception{
        boolean flag = false;
        File file = new File(filePath);
        try{
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                // TODO LXJ 文件删除优化
                file.delete();
                flag = true;
            }else {
                logger.info(String.format("删除单个文件失败，filePath[%s]", filePath));
            }
        }catch (Exception e){
            logger.error("删除单个文件异常", e);
        }
        return flag;
    }



}
