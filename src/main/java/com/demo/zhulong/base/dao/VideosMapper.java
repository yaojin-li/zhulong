package com.demo.zhulong.base.dao;

import com.demo.zhulong.base.beans.Videos;

public interface VideosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);
}