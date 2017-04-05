package com.first.IDao;

import com.first.domain.RoleMenuRightsKey;

public interface RoleMenuRightsMapper {
    int deleteByPrimaryKey(RoleMenuRightsKey key);

    int insert(RoleMenuRightsKey record);

    int insertSelective(RoleMenuRightsKey record);
}