package com.demo.zhulong.service;

import java.util.List;

import com.demo.zhulong.dao.ImageMapper;
import com.demo.zhulong.entity.ImageCustom;
import com.demo.zhulong.entity.ImageQueryVo;
import com.demo.zhulong.dao.ImageMapperCustom;


/**
 * @Description: 图像列表管理service
 * @Author: John
 * @Tags: 
 * @Date: 2017年8月4日 下午4:34:29
 */
public interface ImageService {

	/**
	 * @Description: 商品查询列表
	 * @Author: Administrator
	 * @Tags: @param imagesQueryVo
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月29日 上午9:47:30
	 * @return: List<ImageCustom>
	 */
	public List<ImageCustom> findImageList(ImageQueryVo imagesQueryVo) throws Exception;

	/**
	 * @Description: 插入图像
	 * @Author: Administrator
	 * @Tags: @param imageCustom
	 * @Date: 2017年8月8日 下午7:33:56
	 * @return: void
	 */
	public int insertImage(ImageCustom imageCustom) throws Exception;
	

	/**
	 * @Description: 删除图像
	 * @Author: Administrator
	 * @Tags: @param id
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月29日 上午9:47:11
	 * @return: int
	 */
	public int deleteImage(Integer id) throws Exception;

	
	/**
	 * @Description: 更新图像信息
	 * @Author: Administrator
	 * @Tags: @param id
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月29日 上午9:49:03
	 * @return: int
	 */
	public int updateImage(ImageCustom imageCustom) throws Exception; 
	
	
	
}




















