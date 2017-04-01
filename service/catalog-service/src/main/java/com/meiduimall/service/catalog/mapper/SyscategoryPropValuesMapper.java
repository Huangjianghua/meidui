package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SyscategoryPropValues;
import com.meiduimall.service.catalog.entity.SyscategoryPropValuesExample;

public interface SyscategoryPropValuesMapper {
    int countByExample(SyscategoryPropValuesExample example);

    int deleteByExample(SyscategoryPropValuesExample example);

    int deleteByPrimaryKey(Integer propValueId);

    int insert(SyscategoryPropValues record);

    int insertSelective(SyscategoryPropValues record);

    List<SyscategoryPropValues> selectByExample(SyscategoryPropValuesExample example);

    SyscategoryPropValues selectByPrimaryKey(Integer propValueId);

    int updateByExampleSelective(@Param("record") SyscategoryPropValues record, @Param("example") SyscategoryPropValuesExample example);

    int updateByExample(@Param("record") SyscategoryPropValues record, @Param("example") SyscategoryPropValuesExample example);

    int updateByPrimaryKeySelective(SyscategoryPropValues record);

    int updateByPrimaryKey(SyscategoryPropValues record);
}