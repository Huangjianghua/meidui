package com.meiduimall.application.search.IDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.domain.RightsRoles;


@Mapper
public interface RightsRolesMapper {
    int insert(RightsRoles record);

    int insertSelective(RightsRoles record);
}