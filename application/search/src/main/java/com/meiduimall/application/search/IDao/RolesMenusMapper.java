package com.meiduimall.application.search.IDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.domain.RolesMenus;


@Mapper
public interface RolesMenusMapper {
    int insert(RolesMenus record);

    int insertSelective(RolesMenus record);
}