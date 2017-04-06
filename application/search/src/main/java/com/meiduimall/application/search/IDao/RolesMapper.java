package com.meiduimall.application.search.IDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.domain.Roles;


@Mapper
public interface RolesMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}