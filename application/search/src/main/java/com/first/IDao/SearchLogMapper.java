package com.first.IDao;

import java.util.List;

import com.first.domain.SearchLog;
import com.first.page.PageView;

public interface SearchLogMapper {
	
	/**
	 * 查询搜索日志内容
	 * @param pageView
	 * @return
	 * @throws Exception
	 */
	public List<SearchLog> querySearchLogs(PageView pageView) throws Exception;
	
	/**
	 * 查询搜索日志总数
	 * @return
	 * @throws Exception
	 */
	public long querySearchLogCount() throws Exception;
	
	/**
	 * 根据ID查询搜索日志内容
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public SearchLog querySearchLogById(Integer logId) throws Exception;

	/**
	 * 添加搜索日志信息
	 * @param searchLogs
	 * @return
	 * @throws Exception
	 */
	public int addSearchLog(List<SearchLog> searchLogs) throws Exception;
	
	/**
	 * 修改搜索日志信息
	 * @param searchLog
	 * @return
	 * @throws Exception
	 */
	public int updateSearchLog(SearchLog searchLog) throws Exception;
	
	/**
	 * 删除搜索日志信息
	 * @param dicId
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchLogById(Integer dicId) throws Exception;
}
