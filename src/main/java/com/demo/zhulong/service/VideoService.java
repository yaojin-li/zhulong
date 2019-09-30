package com.demo.zhulong.service;

import com.demo.zhulong.base.beans.Videos;
import com.demo.zhulong.base.dao.VideosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: VideoService
 * --------------------------------------
 * @ClassName: VideoService.java
 * @Date: 2019/9/30 23:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class VideoService {

    @Autowired
    @Lazy
    private VideosMapper videosMapper;

    public List<Videos> selectAll() throws Exception {
        List<Videos> result = new ArrayList<>();
        result.add(videosMapper.selectByPrimaryKey(1));
        return result;
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
//	public int updateImage(ImageCustom imageCustom) throws Exception;


}




















