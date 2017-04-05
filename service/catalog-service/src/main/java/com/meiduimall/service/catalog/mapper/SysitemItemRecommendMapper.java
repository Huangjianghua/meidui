package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysitemItemRecommend;
import com.meiduimall.service.catalog.entity.SysitemItemRecommendExample;

public interface SysitemItemRecommendMapper {
    int countByExample(SysitemItemRecommendExample example);

    int deleteByExample(SysitemItemRecommendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysitemItemRecommend record);

    int insertSelective(SysitemItemRecommend record);

    List<SysitemItemRecommend> selectByExampleWithBLOBs(SysitemItemRecommendExample example);

    List<SysitemItemRecommend> selectByExample(SysitemItemRecommendExample example);

    SysitemItemRecommend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysitemItemRecommend record, @Param("example") SysitemItemRecommendExample example);

    int updateByExampleWithBLOBs(@Param("record") SysitemItemRecommend record, @Param("example") SysitemItemRecommendExample example);

    int updateByExample(@Param("record") SysitemItemRecommend record, @Param("example") SysitemItemRecommendExample example);

    int updateByPrimaryKeySelective(SysitemItemRecommend record);

    int updateByPrimaryKeyWithBLOBs(SysitemItemRecommend record);

    int updateByPrimaryKey(SysitemItemRecommend record);
}