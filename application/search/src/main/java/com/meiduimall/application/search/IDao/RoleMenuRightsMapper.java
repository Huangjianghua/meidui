package com.meiduimall.application.search.IDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.domain.RoleMenuRightsKey;

@Mapper
public interface RoleMenuRightsMapper {
    int deleteByPrimaryKey(RoleMenuRightsKey key);

    int insert(RoleMenuRightsKey record);

    int insertSelective(RoleMenuRightsKey record);
}