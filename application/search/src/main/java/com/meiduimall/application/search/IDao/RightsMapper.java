package com.meiduimall.application.search.IDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.domain.Rights;


@Mapper
public interface RightsMapper {
    int deleteByPrimaryKey(String rightId);

    int insert(Rights record);

    int insertSelective(Rights record);

    Rights selectByPrimaryKey(String rightId);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKey(Rights record);
}