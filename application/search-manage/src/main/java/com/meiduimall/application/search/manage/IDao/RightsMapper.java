package com.meiduimall.application.search.manage.IDao;

import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.domain.Rights;


public interface RightsMapper {
    int deleteByPrimaryKey(String rightId);

    int insert(Rights record);

    int insertSelective(Rights record);

    Rights selectByPrimaryKey(String rightId);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKey(Rights record);
}