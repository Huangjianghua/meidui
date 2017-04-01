package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysuserUser;
import com.meiduimall.service.catalog.entity.SysuserUserExample;

public interface SysuserUserMapper {
    int countByExample(SysuserUserExample example);

    int deleteByExample(SysuserUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(SysuserUser record);

    int insertSelective(SysuserUser record);

    List<SysuserUser> selectByExample(SysuserUserExample example);

    SysuserUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") SysuserUser record, @Param("example") SysuserUserExample example);

    int updateByExample(@Param("record") SysuserUser record, @Param("example") SysuserUserExample example);

    int updateByPrimaryKeySelective(SysuserUser record);

    int updateByPrimaryKey(SysuserUser record);
}