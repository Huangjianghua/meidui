package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysitemItem;
import com.meiduimall.service.catalog.entity.SysitemItemExample;
import com.meiduimall.service.catalog.entity.SysitemItemWithBLOBs;

public interface SysitemItemMapper {
    int countByExample(SysitemItemExample example);

    int deleteByExample(SysitemItemExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(SysitemItemWithBLOBs record);

    int insertSelective(SysitemItemWithBLOBs record);

    List<SysitemItemWithBLOBs> selectByExampleWithBLOBs(SysitemItemExample example);

    List<SysitemItem> selectByExample(SysitemItemExample example);

    SysitemItemWithBLOBs selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") SysitemItemWithBLOBs record, @Param("example") SysitemItemExample example);

    int updateByExampleWithBLOBs(@Param("record") SysitemItemWithBLOBs record, @Param("example") SysitemItemExample example);

    int updateByExample(@Param("record") SysitemItem record, @Param("example") SysitemItemExample example);

    int updateByPrimaryKeySelective(SysitemItemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysitemItemWithBLOBs record);

    int updateByPrimaryKey(SysitemItem record);
}