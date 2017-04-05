package com.meiduimall.service.catalog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.meiduimall.service.catalog.entity.SysrateDsr;
import com.meiduimall.service.catalog.entity.SysrateDsrExample;
import com.meiduimall.service.catalog.entity.SysrateDsrWithBLOBs;

public interface SysrateDsrMapper {
    int countByExample(SysrateDsrExample example);

    int deleteByExample(SysrateDsrExample example);

    int deleteByPrimaryKey(Long shopId);

    int insert(SysrateDsrWithBLOBs record);

    int insertSelective(SysrateDsrWithBLOBs record);

    List<SysrateDsrWithBLOBs> selectByExampleWithBLOBs(SysrateDsrExample example);

    List<SysrateDsr> selectByExample(SysrateDsrExample example);

    SysrateDsrWithBLOBs selectByPrimaryKey(Long shopId);

    int updateByExampleSelective(@Param("record") SysrateDsrWithBLOBs record, @Param("example") SysrateDsrExample example);

    int updateByExampleWithBLOBs(@Param("record") SysrateDsrWithBLOBs record, @Param("example") SysrateDsrExample example);

    int updateByExample(@Param("record") SysrateDsr record, @Param("example") SysrateDsrExample example);

    int updateByPrimaryKeySelective(SysrateDsrWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysrateDsrWithBLOBs record);

    int updateByPrimaryKey(SysrateDsr record);
}