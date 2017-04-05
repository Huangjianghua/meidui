package com.meiduimall.application.search.IDao;

import com.meiduimall.application.search.domain.Platforms;

public interface PlatformsMapper {
    int deleteByPrimaryKey(String pfId);

    int insert(Platforms record);

    int insertSelective(Platforms record);

    Platforms selectByPrimaryKey(String pfId);

    int updateByPrimaryKeySelective(Platforms record);

    int updateByPrimaryKey(Platforms record);
}