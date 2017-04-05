package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.entity.SysuserAccountExample;

public interface SysuserAccountMapper {
    int countByExample(SysuserAccountExample example);

    int deleteByExample(SysuserAccountExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(SysuserAccount record);

    int insertSelective(SysuserAccount record);

    List<SysuserAccount> selectByExample(SysuserAccountExample example);

    SysuserAccount selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") SysuserAccount record, @Param("example") SysuserAccountExample example);

    int updateByExample(@Param("record") SysuserAccount record, @Param("example") SysuserAccountExample example);

    int updateByPrimaryKeySelective(SysuserAccount record);

    int updateByPrimaryKey(SysuserAccount record);
}