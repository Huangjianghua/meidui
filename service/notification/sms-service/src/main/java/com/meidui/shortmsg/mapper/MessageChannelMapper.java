package com.meidui.shortmsg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meidui.shortmsg.entity.MessageChannel;
@Mapper
public interface MessageChannelMapper {

    int insert(MessageChannel record);

    int insertSelective(MessageChannel record);
    
    List<MessageChannel> getChannelList();
    
}