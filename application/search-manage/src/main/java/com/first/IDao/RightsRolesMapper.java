package com.first.IDao;

import com.first.domain.RightsRoles;

public interface RightsRolesMapper {
    int insert(RightsRoles record);

    int insertSelective(RightsRoles record);
}