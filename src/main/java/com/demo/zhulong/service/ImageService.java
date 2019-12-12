package com.demo.zhulong.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.base.dao.ImagesMapper;
import com.demo.zhulong.common.CommonResponse;
import com.demo.zhulong.common.enums.ResultCode;
import com.demo.zhulong.config.HdfsConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: ImageService
 * --------------------------------------
 * @ClassName: ImageService.java
 * @Date: 2019/9/30 20:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class ImageService {
    // TODO LXJ 操作数据库失败添加事务

    public static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    @Lazy
    private ImagesMapper imagesMapper;

    public List<Images> selectAll() throws Exception {
        return imagesMapper.selectAll();
    }


    /**
     * @Description: 插入图像
     */
	public int insertImage(Map<String, Object> imageInfo) throws Exception{
	    Images image = new Images();
	    image.setTitle((String) imageInfo.get("title"));
	    image.setType((String) imageInfo.get("type"));
        image.setSize((Long) imageInfo.get("size"));
        image.setHdfsPosition((String) imageInfo.get("hdfsPosition"));
        image.setServerPosition((String) imageInfo.get("serverPosition"));
        image.setUuid((String) imageInfo.get("uuid"));
        image.setUploader((String) imageInfo.get("uploader"));
        image.setRemark((String) imageInfo.get("remark"));
        image.setUploadTitle((String) imageInfo.get("uploadTitle"));
	    return imagesMapper.insertSelective(image);
    }


    /**
     * @Description: 数据库删除图像
     */
    public int deleteImageByUploadTitle(String uploadTitle) throws Exception {
        return imagesMapper.deleteImgByUploadTitle(uploadTitle);
    }


    /**
     * @Description: 从 HDFS 中删除
     */
    public Boolean deleteImageFromHdfs(String uploadTitle) throws Exception {
        String filePath = HdfsConfig.getMasterAddress() + uploadTitle;
        logger.info(String.format("HDFS 中待删除文件[%s]", filePath));
        try {
            Configuration configuration = new Configuration();
            FileSystem fileSystem = FileSystem.get(URI.create(filePath), configuration, HdfsConfig.getHdfsUser());
            Path saveFilePath = new Path(filePath);
            if (!fileSystem.exists(saveFilePath)) {
                logger.error(String.format("HDFS 中文件路径不存在！filePath:[%s]", filePath));
                return false;
            }
            // 删除
            return fileSystem.delete(saveFilePath, true);
        } catch (Exception e) {
            logger.error(String.format("HDFS 中删除文件异常！uploadTitle:[%s]", uploadTitle), e);
        }
        return false;
    }


    /**
     * @Description: 更新图像信息（仅限服务器修改，暂时不支持 HDFS 中修改）
     */
    public int updateImage(JSONObject imgModInfo) throws Exception {
        String uuid = (String) imgModInfo.get("uuid");
        String title = (String) imgModInfo.get("title");
        String uploader = (String) imgModInfo.get("uploader");

        Images images = new Images();
        images.setUuid(uuid);
        images.setTitle(title);
        images.setUploader(uploader);
        images.setUpdateTime(new Date());
        return imagesMapper.updateImgByUuid(images);
    }


}




















