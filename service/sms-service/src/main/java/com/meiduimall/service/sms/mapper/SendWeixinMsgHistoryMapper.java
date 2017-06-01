package com.meiduimall.service.sms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.meiduimall.service.sms.entity.SendWeixinMsgHistory;

@Mapper
public interface SendWeixinMsgHistoryMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SendWeixinMsgHistory record);

	int insertSelective(SendWeixinMsgHistory record);

	SendWeixinMsgHistory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SendWeixinMsgHistory record);

	int updateByPrimaryKeyWithBLOBs(SendWeixinMsgHistory record);

	int updateByPrimaryKey(SendWeixinMsgHistory record);
}