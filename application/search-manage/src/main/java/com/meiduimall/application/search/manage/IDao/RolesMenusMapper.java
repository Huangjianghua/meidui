package com.meiduimall.application.search.manage.IDao;
import com.meiduimall.application.search.manage.domain.RolesMenus;


public interface RolesMenusMapper {
    int insert(RolesMenus record);

    int insertSelective(RolesMenus record);
}