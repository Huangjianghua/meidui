package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysitemItemStatus;
import com.meiduimall.service.catalog.entity.SysitemItemStatusExample;

public interface SysitemItemStatusMapper {
    int countByExample(SysitemItemStatusExample example);

    int deleteByExample(SysitemItemStatusExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(SysitemItemStatus record);

    int insertSelective(SysitemItemStatus record);

    List<SysitemItemStatus> selectByExample(SysitemItemStatusExample example);

    SysitemItemStatus selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") SysitemItemStatus record, @Param("example") SysitemItemStatusExample example);

    int updateByExample(@Param("record") SysitemItemStatus record, @Param("example") SysitemItemStatusExample example);

    int updateByPrimaryKeySelective(SysitemItemStatus record);

    int updateByPrimaryKey(SysitemItemStatus record);
}