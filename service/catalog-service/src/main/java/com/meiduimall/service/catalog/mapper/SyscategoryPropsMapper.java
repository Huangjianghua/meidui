package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SyscategoryProps;
import com.meiduimall.service.catalog.entity.SyscategoryPropsExample;

public interface SyscategoryPropsMapper {
    int countByExample(SyscategoryPropsExample example);

    int deleteByExample(SyscategoryPropsExample example);

    int deleteByPrimaryKey(Integer propId);

    int insert(SyscategoryProps record);

    int insertSelective(SyscategoryProps record);

    List<SyscategoryProps> selectByExample(SyscategoryPropsExample example);

    SyscategoryProps selectByPrimaryKey(Integer propId);

    int updateByExampleSelective(@Param("record") SyscategoryProps record, @Param("example") SyscategoryPropsExample example);

    int updateByExample(@Param("record") SyscategoryProps record, @Param("example") SyscategoryPropsExample example);

    int updateByPrimaryKeySelective(SyscategoryProps record);

    int updateByPrimaryKey(SyscategoryProps record);
}