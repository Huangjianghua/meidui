package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysshopShopCat;
import com.meiduimall.service.catalog.entity.SysshopShopCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysshopShopCatMapper {
    int countByExample(SysshopShopCatExample example);

    int deleteByExample(SysshopShopCatExample example);

    int deleteByPrimaryKey(Integer catId);

    int insert(SysshopShopCat record);

    int insertSelective(SysshopShopCat record);

    List<SysshopShopCat> selectByExample(SysshopShopCatExample example);

    SysshopShopCat selectByPrimaryKey(Integer catId);

    int updateByExampleSelective(@Param("record") SysshopShopCat record, @Param("example") SysshopShopCatExample example);

    int updateByExample(@Param("record") SysshopShopCat record, @Param("example") SysshopShopCatExample example);

    int updateByPrimaryKeySelective(SysshopShopCat record);

    int updateByPrimaryKey(SysshopShopCat record);
}