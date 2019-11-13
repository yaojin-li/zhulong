package com.demo.zhulong.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.base.dao.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Autowired
    @Lazy
    private ImagesMapper imagesMapper;

    public List<Images> selectAll() throws Exception {
        return imagesMapper.selectAll();
    }


    /**
     * @Description: 插入图像
     */
//	public int insertImage(ImageCustom imageCustom) throws Exception;


    /**
     * @Description: 删除图像
     */
	public int deleteImageByUuid(String uuid) throws Exception{
	    Images images = new Images();
	    images.setUuid(uuid);
	    return imagesMapper.delete(images);
    }


    /**
     * @Description: 更新图像信息
     */
	public int updateImage(String imgModInfo) throws Exception{
        JSONObject imgModJson = JSONObject.parseObject(imgModInfo);
        String uuid = (String) imgModJson.get("uuid");
        String title = (String) imgModJson.get("title");
        String uploader = (String) imgModJson.get("uploader");

        Images images = new Images();
        images.setUuid(uuid);
        images.setTitle(title);
        images.setUploader(uploader);
        images.setUpdateTime(new Date());

	    return imagesMapper.updateImgByUuid(images);
    }



}




















