package com.meiduimall.application.search.IDao;

import com.meiduimall.application.search.domain.RightsRoles;

public interface RightsRolesMapper {
    int insert(RightsRoles record);

    int insertSelective(RightsRoles record);
}