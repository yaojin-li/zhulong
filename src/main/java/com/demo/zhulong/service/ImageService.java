package com.demo.zhulong.service;

import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.base.dao.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
     * @Description: 商品查询列表
     */
//	public List<ImageVo> selectAll() throws Exception;

    /**
     * @Description: 插入图像
     */
//	public int insertImage(ImageCustom imageCustom) throws Exception;


    /**
     * @Description: 删除图像
     */
//	public int deleteImage(Integer id) throws Exception;


    /**
     * @Description: 更新图像信息
     */
	public int updateImage(Images images) throws Exception{
	    return imagesMapper.updateByPrimaryKey(images);
    }


}




















