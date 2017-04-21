package com.meiduimall.application.search.manage.services;

import java.util.List;

import com.meiduimall.application.search.manage.domain.SearchLog;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.LogParam;

public interface SearchLogService {
	
	/**
	 * 查询搜索日志内容
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public QueryResult querySearchLogs(LogParam logParam);
	
	/**
	 * 查询统计日志信息
	 * @param logParam
	 * @return
	 * @throws Exception
	 */
	public QueryResult queryStatisticLogs(LogParam logParam) ;
	
	/**
	 * 根据关键词查询搜索日志信息
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public SearchLog querySearchLogByKeyword(String keyword) ;
	
	/**
	 * 根据ID查询搜索日志信息
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public SearchLog querySearchLogById(Integer logId);

	/**
	 * 添加搜索日志信息
	 * @param searchLog
	 * @return
	 * @throws Exception
	 */
	public int addSearchLog(List<SearchLog> searchLogs);
	
	/**
	 * 删除搜索日志信息
	 * @param dicId
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchLogById(Integer dicId) ;
}
