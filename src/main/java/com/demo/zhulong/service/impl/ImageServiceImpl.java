//package com.demo.zhulong.service.impl;
//
//import java.util.List;
//
//import com.demo.zhulong.base.beans.ImageVo;
//import com.demo.zhulong.dao.ImageMapper;
//import org.springframework.stereotype.Service;
//
//import com.demo.zhulong.service.ImageService;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * @Description: 图像管理service
// * @Author: John
// * @Tags:
// * @Date: 2017年8月4日 下午4:34:29
// */
//@Service
//@RequestMapping("/imageService")
//public class ImageServiceImpl implements ImageService{
//
//	private ImageMapper imageMapper;
//
//	/*
//	 * @Description: 图像列表
//	 * @Author: Administrator
//	 * @Tags: @param imagesQueryVo
//	 * @Tags: @return
//	 * @Tags: @throws Exception
//	 * @Date: 2017年8月30日 下午2:19:04
//	 * @see com.service.ImageService#findImageList(com.beans.ImageQueryVo)
//	 */
//	@Override
//	public List<ImageVo> selectAll() throws Exception {
//		// TODO Auto-generated method stub
//        return imageMapper.selectAll();
////		return imageMapper.findImageList(imagesQueryVo);
//	}
//
//
////
////	/*
////	 * @Description: 插入图像
////	 * @Author: Administrator
////	 * @Tags: @param imageCustom
////	 * @Tags: @return
////	 * @Tags: @throws Exception
////	 * @Date: 2017年8月30日 下午2:18:55
////	 * @see com.service.ImageService#insertImage(com.beans.ImageCustom)
////	 */
////    @Override
////	public int insertImage(ImageCustom imageCustom) throws Exception {
////		// TODO Auto-generated method stub
////		return imageMapperCustom.insertImage(imageCustom);
////	}
////
////
////	/*
////	 * @Description: 删除图像
////	 * @Author: Administrator
////	 * @Tags: @param id
////	 * @Tags: @return
////	 * @Tags: @throws Exception
////	 * @Date: 2017年8月30日 下午2:18:46
////	 * @see com.service.ImageService#deleteImage(java.lang.Integer)
////	 */
////    @Override
////	public int deleteImage(Integer id) throws Exception {
////		// TODO Auto-generated method stub
////		return imageMapperCustom.deleteImage(id);
////	}
////
////
////	/*
////	 * @Description: 更新图像信息
////	 * @Author: Administrator
////	 * @Tags: @param imageCustom
////	 * @Tags: @return
////	 * @Tags: @throws Exception
////	 * @Date: 2017年8月30日 下午2:18:23
////	 * @see com.service.ImageService#updateImage(com.beans.ImageCustom)
////	 */
////    @Override
////	public int updateImage(ImageCustom imageCustom) throws Exception {
////		// TODO Auto-generated method stub
////		return imageMapperCustom.updateImage(imageCustom);
////	}
////
//
//
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
