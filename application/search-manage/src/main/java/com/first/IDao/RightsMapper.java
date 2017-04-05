package com.first.IDao;

import com.first.domain.Rights;

public interface RightsMapper {
    int deleteByPrimaryKey(String rightId);

    int insert(Rights record);

    int insertSelective(Rights record);

    Rights selectByPrimaryKey(String rightId);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKey(Rights record);
}