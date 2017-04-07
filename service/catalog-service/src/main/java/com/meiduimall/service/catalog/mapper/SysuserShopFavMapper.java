package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysuserShopFav;
import com.meiduimall.service.catalog.entity.SysuserShopFavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysuserShopFavMapper {
    int countByExample(SysuserShopFavExample example);

    int deleteByExample(SysuserShopFavExample example);

    int deleteByPrimaryKey(Integer snotifyId);

    int insert(SysuserShopFav record);

    int insertSelective(SysuserShopFav record);

    List<SysuserShopFav> selectByExample(SysuserShopFavExample example);

    SysuserShopFav selectByPrimaryKey(Integer snotifyId);

    int updateByExampleSelective(@Param("record") SysuserShopFav record, @Param("example") SysuserShopFavExample example);

    int updateByExample(@Param("record") SysuserShopFav record, @Param("example") SysuserShopFavExample example);

    int updateByPrimaryKeySelective(SysuserShopFav record);

    int updateByPrimaryKey(SysuserShopFav record);
}