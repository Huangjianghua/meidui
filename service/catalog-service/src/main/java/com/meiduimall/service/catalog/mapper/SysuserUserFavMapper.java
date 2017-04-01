package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysuserUserFav;
import com.meiduimall.service.catalog.entity.SysuserUserFavExample;

public interface SysuserUserFavMapper {
    int countByExample(SysuserUserFavExample example);

    int deleteByExample(SysuserUserFavExample example);

    int deleteByPrimaryKey(Integer gnotifyId);

    int insert(SysuserUserFav record);

    int insertSelective(SysuserUserFav record);

    List<SysuserUserFav> selectByExampleWithBLOBs(SysuserUserFavExample example);

    List<SysuserUserFav> selectByExample(SysuserUserFavExample example);

    SysuserUserFav selectByPrimaryKey(Integer gnotifyId);

    int updateByExampleSelective(@Param("record") SysuserUserFav record, @Param("example") SysuserUserFavExample example);

    int updateByExampleWithBLOBs(@Param("record") SysuserUserFav record, @Param("example") SysuserUserFavExample example);

    int updateByExample(@Param("record") SysuserUserFav record, @Param("example") SysuserUserFavExample example);

    int updateByPrimaryKeySelective(SysuserUserFav record);

    int updateByPrimaryKeyWithBLOBs(SysuserUserFav record);

    int updateByPrimaryKey(SysuserUserFav record);
}