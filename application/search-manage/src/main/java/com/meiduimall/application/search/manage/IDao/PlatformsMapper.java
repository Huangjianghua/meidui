package com.meiduimall.application.search.manage.IDao;

import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.domain.Platforms;


public interface PlatformsMapper {
    int deleteByPrimaryKey(String pfId);

    int insert(Platforms record);

    int insertSelective(Platforms record);

    Platforms selectByPrimaryKey(String pfId);

    int updateByPrimaryKeySelective(Platforms record);

    int updateByPrimaryKey(Platforms record);
}