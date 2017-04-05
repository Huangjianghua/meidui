package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysitemItemCount;
import com.meiduimall.service.catalog.entity.SysitemItemCountExample;

public interface SysitemItemCountMapper {
    int countByExample(SysitemItemCountExample example);

    int deleteByExample(SysitemItemCountExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(SysitemItemCount record);

    int insertSelective(SysitemItemCount record);

    List<SysitemItemCount> selectByExample(SysitemItemCountExample example);

    SysitemItemCount selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") SysitemItemCount record, @Param("example") SysitemItemCountExample example);

    int updateByExample(@Param("record") SysitemItemCount record, @Param("example") SysitemItemCountExample example);

    int updateByPrimaryKeySelective(SysitemItemCount record);

    int updateByPrimaryKey(SysitemItemCount record);
}