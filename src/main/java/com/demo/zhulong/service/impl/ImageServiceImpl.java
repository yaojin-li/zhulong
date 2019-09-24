package com.demo.zhulong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.zhulong.dao.ImageMapper;
import com.demo.zhulong.entity.ImageCustom;
import com.demo.zhulong.entity.ImageQueryVo;
import com.demo.zhulong.entity.Image;
import com.demo.zhulong.dao.ImageMapperCustom;
import com.demo.zhulong.service.ImageService;

/**
 * @Description: 图像管理service
 * @Author: John
 * @Tags: 
 * @Date: 2017年8月4日 下午4:34:29
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageMapperCustom imageMapperCustom;

	
	/* 
	 * @Description: 图像列表
	 * @Author: Administrator
	 * @Tags: @param imagesQueryVo
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月30日 下午2:19:04
	 * @see com.service.ImageService#findImageList(com.entity.ImageQueryVo)
	 */
	public List<ImageCustom> findImageList(ImageQueryVo imagesQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return imageMapperCustom.findImageList(imagesQueryVo);
	}



	/* 
	 * @Description: 插入图像
	 * @Author: Administrator
	 * @Tags: @param imageCustom
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月30日 下午2:18:55
	 * @see com.service.ImageService#insertImage(com.entity.ImageCustom)
	 */
	public int insertImage(ImageCustom imageCustom) throws Exception {
		// TODO Auto-generated method stub
		return imageMapperCustom.insertImage(imageCustom);
	}


	/* 
	 * @Description: 删除图像
	 * @Author: Administrator
	 * @Tags: @param id
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月30日 下午2:18:46
	 * @see com.service.ImageService#deleteImage(java.lang.Integer)
	 */
	public int deleteImage(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return imageMapperCustom.deleteImage(id);
	}


	/* 
	 * @Description: 更新图像信息
	 * @Author: Administrator
	 * @Tags: @param imageCustom
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月30日 下午2:18:23
	 * @see com.service.ImageService#updateImage(com.entity.ImageCustom)
	 */
	public int updateImage(ImageCustom imageCustom) throws Exception {
		// TODO Auto-generated method stub
		return imageMapperCustom.updateImage(imageCustom);
	}



	

}




















