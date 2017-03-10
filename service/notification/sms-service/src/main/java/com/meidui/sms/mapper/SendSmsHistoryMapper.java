package com.meidui.sms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.meidui.sms.entity.SendSmsHistory;
@Mapper
public interface SendSmsHistoryMapper {

    int insert(SendSmsHistory record);

    int insertSelective(SendSmsHistory record);

}