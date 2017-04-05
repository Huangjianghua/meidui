package com.first.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.first.domain.SearchLog;
import com.first.pojo.LogParam;
import com.first.pojo.StatisticLog;

public interface SearchLogMapper {
	
	/**
	 * 查询搜索日志内容
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public List<SearchLog> querySearchLogs(LogParam logParam) throws Exception;
	
	/**
	 * 查询搜索日志总数
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public long querySearchLogCount(LogParam logParam) throws Exception;
	
	/**
	 * 查询统计日志内容
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public List<StatisticLog> queryStatisticLogs(LogParam logParam) throws Exception;
	
	/**
	 * 查询统计日志总数
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public long queryStatisticCount(LogParam logParam) throws Exception;
	
	/**
	 * 根据关键词查询搜索日志内容
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public SearchLog querySearchLogByKeyword(String keyword) throws Exception;
	
	/**
	 * 根据ID查询搜索日志内容
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public SearchLog querySearchLogById(Integer logId) throws Exception;

	/**
	 * 添加搜索日志信息
	 * @param searchLog
	 * @return
	 * @throws Exception
	 */
	public int addSearchLog(@Param("logs") List<SearchLog> searchLogs) throws Exception;
	
	/**
	 * 删除搜索日志信息
	 * @param dicId
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchLogById(Integer dicId) throws Exception;
}
