package com.meidui.shortmsg.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.meidui.shortmsg.entity.SendSmsHistory;
@Mapper
public interface SendSmsHistoryMapper {

    int insert(SendSmsHistory record);

    int insertSelective(SendSmsHistory record);

}