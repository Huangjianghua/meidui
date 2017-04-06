package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemSkuStore;
import com.meiduimall.service.catalog.entity.SysitemSkuStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysitemSkuStoreMapper {
    int countByExample(SysitemSkuStoreExample example);

    int deleteByExample(SysitemSkuStoreExample example);

    int deleteByPrimaryKey(Integer skuId);

    int insert(SysitemSkuStore record);

    int insertSelective(SysitemSkuStore record);

    List<SysitemSkuStore> selectByExample(SysitemSkuStoreExample example);

    SysitemSkuStore selectByPrimaryKey(Integer skuId);

    int updateByExampleSelective(@Param("record") SysitemSkuStore record, @Param("example") SysitemSkuStoreExample example);

    int updateByExample(@Param("record") SysitemSkuStore record, @Param("example") SysitemSkuStoreExample example);

    int updateByPrimaryKeySelective(SysitemSkuStore record);

    int updateByPrimaryKey(SysitemSkuStore record);
}