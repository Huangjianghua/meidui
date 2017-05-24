package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SyspromotionSendcouponrule;

public interface SyspromotionSendcouponruleMapper {

	int deleteByPrimaryKey(Integer ruleId);

	int insert(SyspromotionSendcouponrule record);

	int insertSelective(SyspromotionSendcouponrule record);

	SyspromotionSendcouponrule selectByPrimaryKey(Integer ruleId);

	int updateByPrimaryKeySelective(SyspromotionSendcouponrule record);

	int updateByPrimaryKey(SyspromotionSendcouponrule record);
}