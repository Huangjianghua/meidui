package com.meiduimall.application.search.manage.IDao;

import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.domain.RolesMenus;


public interface RolesMenusMapper {
    int insert(RolesMenus record);

    int insertSelective(RolesMenus record);
}