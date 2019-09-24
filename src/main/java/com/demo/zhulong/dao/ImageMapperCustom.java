package com.demo.zhulong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.zhulong.entity.ImageCustom;
import com.demo.zhulong.entity.ImageQueryVo;
import com.demo.zhulong.entity.Image;

public interface ImageMapperCustom {
    
	
	/**
	 * @Description: 商品查询列表
	 * @Author: Administrator
	 * @Tags: @param ImageQueryVo
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月29日 上午9:50:37
	 * @return: List<ImageCustom>
	 */
	public List<ImageCustom> findImageList(ImageQueryVo ImageQueryVo)throws Exception;


	/**
	 * @Description: 插入图像
	 * @Author: Administrator
	 * @Tags: @param imageCustom
	 * @Tags: @return
	 * @Date: 2017年8月8日 下午7:35:39
	 * @return: int
	 */
	public int insertImage(ImageCustom imageCustom) throws Exception;
	
	
	/**
	 * @Description: 删除图像
	 * @Author: Administrator
	 * @Tags: @param id
	 * @Tags: @return
	 * @Tags: @throws Exception
	 * @Date: 2017年8月29日 上午9:50:22
	 * @return: int
	 */
	public int deleteImage(Integer id) throws Exception;


	/**
	 * @Description: 更新图像信息
	 * @Author: Administrator
	 * @Tags: @param id
	 * @Tags: @return
	 * @Date: 2017年8月29日 上午9:49:56
	 * @return: int
	 */
	public int updateImage(ImageCustom imageCustom) throws Exception;
	
}











