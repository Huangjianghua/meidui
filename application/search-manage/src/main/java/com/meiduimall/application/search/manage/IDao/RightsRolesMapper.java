package com.meiduimall.application.search.manage.IDao;
import com.meiduimall.application.search.manage.domain.RightsRoles;


public interface RightsRolesMapper {
    int insert(RightsRoles record);

    int insertSelective(RightsRoles record);
}