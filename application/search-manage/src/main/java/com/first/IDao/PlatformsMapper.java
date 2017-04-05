package com.first.IDao;

import com.first.domain.Platforms;

public interface PlatformsMapper {
    int deleteByPrimaryKey(String pfId);

    int insert(Platforms record);

    int insertSelective(Platforms record);

    Platforms selectByPrimaryKey(String pfId);

    int updateByPrimaryKeySelective(Platforms record);

    int updateByPrimaryKey(Platforms record);
}