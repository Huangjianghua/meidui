package com.meiduimall.service.financial.mapper;

import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.entity.DownloadStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DownloadStatisticsMapper {
	
    int countByExample(DownloadStatisticsExample example);

    int deleteByExample(DownloadStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DownloadStatistics record);

    int insertSelective(DownloadStatistics record);

    List<DownloadStatistics> selectByExampleWithBLOBs(DownloadStatisticsExample example);

    List<DownloadStatistics> selectByExample(DownloadStatisticsExample example);

    DownloadStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DownloadStatistics record, @Param("example") DownloadStatisticsExample example);

    int updateByExampleWithBLOBs(@Param("record") DownloadStatistics record, @Param("example") DownloadStatisticsExample example);

    int updateByExample(@Param("record") DownloadStatistics record, @Param("example") DownloadStatisticsExample example);

    int updateByPrimaryKeySelective(DownloadStatistics record);

    int updateByPrimaryKeyWithBLOBs(DownloadStatistics record);

    int updateByPrimaryKey(DownloadStatistics record);
}