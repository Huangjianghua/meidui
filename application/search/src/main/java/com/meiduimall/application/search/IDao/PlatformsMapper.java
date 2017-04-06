package com.meiduimall.application.search.IDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.domain.Platforms;


@Mapper
public interface PlatformsMapper {
    int deleteByPrimaryKey(String pfId);

    int insert(Platforms record);

    int insertSelective(Platforms record);

    Platforms selectByPrimaryKey(String pfId);

    int updateByPrimaryKeySelective(Platforms record);

    int updateByPrimaryKey(Platforms record);
}