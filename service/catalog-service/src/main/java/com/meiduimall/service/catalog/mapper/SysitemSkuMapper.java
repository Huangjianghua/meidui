package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysitemSku;
import com.meiduimall.service.catalog.entity.SysitemSkuExample;
import com.meiduimall.service.catalog.entity.SysitemSkuWithBLOBs;

public interface SysitemSkuMapper {
    int countByExample(SysitemSkuExample example);

    int deleteByExample(SysitemSkuExample example);

    int deleteByPrimaryKey(Integer skuId);

    int insert(SysitemSkuWithBLOBs record);

    int insertSelective(SysitemSkuWithBLOBs record);

    List<SysitemSkuWithBLOBs> selectByExampleWithBLOBs(SysitemSkuExample example);

    List<SysitemSku> selectByExample(SysitemSkuExample example);

    SysitemSkuWithBLOBs selectByPrimaryKey(Integer skuId);

    int updateByExampleSelective(@Param("record") SysitemSkuWithBLOBs record, @Param("example") SysitemSkuExample example);

    int updateByExampleWithBLOBs(@Param("record") SysitemSkuWithBLOBs record, @Param("example") SysitemSkuExample example);

    int updateByExample(@Param("record") SysitemSku record, @Param("example") SysitemSkuExample example);

    int updateByPrimaryKeySelective(SysitemSkuWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysitemSkuWithBLOBs record);

    int updateByPrimaryKey(SysitemSku record);
}