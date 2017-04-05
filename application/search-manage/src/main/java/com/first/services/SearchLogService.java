package com.first.services;

import java.util.List;

import com.first.domain.SearchLog;
import com.first.page.QueryResult;
import com.first.pojo.LogParam;

public interface SearchLogService {
	
	/**
	 * 查询搜索日志内容
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public QueryResult querySearchLogs(LogParam logParam) throws Exception;
	
	/**
	 * 查询统计日志信息
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public QueryResult queryStatisticLogs(LogParam logParam) throws Exception;
	
	/**
	 * 根据关键词查询搜索日志信息
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public SearchLog querySearchLogByKeyword(String keyword) throws Exception;
	
	/**
	 * 根据ID查询搜索日志信息
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
	public int addSearchLog(List<SearchLog> searchLogs) throws Exception;
	
	/**
	 * 删除搜索日志信息
	 * @param dicId
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchLogById(Integer dicId) throws Exception;
}
