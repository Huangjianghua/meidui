package com.first.IDao;

import com.first.domain.RolesMenus;

public interface RolesMenusMapper {
    int insert(RolesMenus record);

    int insertSelective(RolesMenus record);
}