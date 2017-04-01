package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysshopShop;
import com.meiduimall.service.catalog.entity.SysshopShopExample;
import com.meiduimall.service.catalog.entity.SysshopShopWithBLOBs;

public interface SysshopShopMapper {
    int countByExample(SysshopShopExample example);

    int deleteByExample(SysshopShopExample example);

    int deleteByPrimaryKey(Integer shopId);

    int insert(SysshopShopWithBLOBs record);

    int insertSelective(SysshopShopWithBLOBs record);

    List<SysshopShopWithBLOBs> selectByExampleWithBLOBs(SysshopShopExample example);

    List<SysshopShop> selectByExample(SysshopShopExample example);

    SysshopShopWithBLOBs selectByPrimaryKey(Integer shopId);

    int updateByExampleSelective(@Param("record") SysshopShopWithBLOBs record, @Param("example") SysshopShopExample example);

    int updateByExampleWithBLOBs(@Param("record") SysshopShopWithBLOBs record, @Param("example") SysshopShopExample example);

    int updateByExample(@Param("record") SysshopShop record, @Param("example") SysshopShopExample example);

    int updateByPrimaryKeySelective(SysshopShopWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysshopShopWithBLOBs record);

    int updateByPrimaryKey(SysshopShop record);
}