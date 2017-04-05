package com.first.system.services;

import com.first.page.QueryResult;
import com.first.system.domain.Systemlog;

public interface ISystemlogService {
	/**
	 * 增加登入日志
	 * @param log
	 */
	public void addSysLog(Systemlog log);

	/**
	 * 分页查询系统登入日志
	 * @return QueryResult
	 */ 
	public QueryResult selectPageList(Systemlog log);

}
