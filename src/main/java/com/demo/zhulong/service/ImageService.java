package com.demo.zhulong.service;

import com.demo.zhulong.base.beans.ImageVo;
import com.demo.zhulong.base.dao.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @Description: 图像列表管理service
 * @Author: John
 * @Tags:
 * @Date: 2017年8月4日 下午4:34:29
 */
public class ImageService {
    @Autowired
    private ImageMapper imageMapper;

    public List<ImageVo> selectAll() throws Exception {
        return imageMapper.selectAll();
    }

    /**
     * @Description: 商品查询列表
     * @Author: Administrator
     * @Tags: @param imagesQueryVo
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月29日 上午9:47:30
     * @return: List<ImageCustom>
     */
//	public List<ImageVo> selectAll() throws Exception;

    /**
     * @Description: 插入图像
     * @Author: Administrator
     * @Tags: @param imageCustom
     * @Date: 2017年8月8日 下午7:33:56
     * @return: void
     */
//	public int insertImage(ImageCustom imageCustom) throws Exception;


    /**
     * @Description: 删除图像
     * @Author: Administrator
     * @Tags: @param id
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月29日 上午9:47:11
     * @return: int
     */
//	public int deleteImage(Integer id) throws Exception;


    /**
     * @Description: 更新图像信息
     * @Author: Administrator
     * @Tags: @param id
     * @Tags: @return
     * @Tags: @throws Exception
     * @Date: 2017年8月29日 上午9:49:03
     * @return: int
     */
//	public int updateImage(ImageCustom imageCustom) throws Exception;


}




















