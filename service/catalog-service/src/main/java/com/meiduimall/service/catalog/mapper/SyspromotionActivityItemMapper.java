package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SyspromotionActivityItem;

public interface SyspromotionActivityItemMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SyspromotionActivityItem record);

	int insertSelective(SyspromotionActivityItem record);

	SyspromotionActivityItem selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SyspromotionActivityItem record);

	int updateByPrimaryKeyWithBLOBs(SyspromotionActivityItem record);

	int updateByPrimaryKey(SyspromotionActivityItem record);
}