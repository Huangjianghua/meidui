package com.first.IDao;

import com.first.domain.Roles;

public interface RolesMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}