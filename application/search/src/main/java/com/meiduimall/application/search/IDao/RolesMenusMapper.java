package com.meiduimall.application.search.IDao;

import com.meiduimall.application.search.domain.RolesMenus;

public interface RolesMenusMapper {
    int insert(RolesMenus record);

    int insertSelective(RolesMenus record);
}