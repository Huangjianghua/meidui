package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemItemStore;
import com.meiduimall.service.catalog.entity.SysitemItemStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysitemItemStoreMapper {
    int countByExample(SysitemItemStoreExample example);

    int deleteByExample(SysitemItemStoreExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(SysitemItemStore record);

    int insertSelective(SysitemItemStore record);

    List<SysitemItemStore> selectByExample(SysitemItemStoreExample example);

    SysitemItemStore selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") SysitemItemStore record, @Param("example") SysitemItemStoreExample example);

    int updateByExample(@Param("record") SysitemItemStore record, @Param("example") SysitemItemStoreExample example);

    int updateByPrimaryKeySelective(SysitemItemStore record);

    int updateByPrimaryKey(SysitemItemStore record);
}