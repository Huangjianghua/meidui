package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysitemItemDesc;
import com.meiduimall.service.catalog.entity.SysitemItemDescExample;

public interface SysitemItemDescMapper {
    int countByExample(SysitemItemDescExample example);

    int deleteByExample(SysitemItemDescExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(SysitemItemDesc record);

    int insertSelective(SysitemItemDesc record);

    List<SysitemItemDesc> selectByExampleWithBLOBs(SysitemItemDescExample example);

    List<SysitemItemDesc> selectByExample(SysitemItemDescExample example);

    SysitemItemDesc selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") SysitemItemDesc record, @Param("example") SysitemItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") SysitemItemDesc record, @Param("example") SysitemItemDescExample example);

    int updateByExample(@Param("record") SysitemItemDesc record, @Param("example") SysitemItemDescExample example);

    int updateByPrimaryKeySelective(SysitemItemDesc record);

    int updateByPrimaryKeyWithBLOBs(SysitemItemDesc record);
}