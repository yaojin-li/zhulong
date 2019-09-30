package com.demo.zhulong.base.dao;

import com.demo.zhulong.base.beans.Images;
import org.apache.ibatis.annotations.Param;


public interface ImagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Images record);

    int insertSelective(Images record);

//    @Select("select * from images where id = #{id,jdbcType=INTEGER}")
    Images selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Images record);

    int updateByPrimaryKey(Images record);
}