package com.meiduimall.service.sms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.meiduimall.service.sms.entity.SendSmsHistory;
@Mapper
public interface SendSmsHistoryMapper {

    int insert(SendSmsHistory record);

    int insertSelective(SendSmsHistory record);

}