package com.meiduimall.application.search.IDao;

import com.meiduimall.application.search.domain.RoleMenuRightsKey;

public interface RoleMenuRightsMapper {
    int deleteByPrimaryKey(RoleMenuRightsKey key);

    int insert(RoleMenuRightsKey record);

    int insertSelective(RoleMenuRightsKey record);
}