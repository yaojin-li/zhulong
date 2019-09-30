package com.demo.zhulong.service;

import com.demo.zhulong.base.beans.Images;
import com.demo.zhulong.base.dao.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ImageService {

    @Autowired
    private ImagesMapper imagesMapper;

    public List<Images> selectAll() throws Exception {
        List<Images> result = new ArrayList<>();
        result.add(imagesMapper.selectByPrimaryKey(1));
        return result;
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




















