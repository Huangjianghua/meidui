package com.meiduimall.application.search.manage.services;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.page.QueryResult;

public interface IndexLogService {

	public QueryResult queryIndexLogs(PageView pageView) throws Exception;

	public int insertIndexLog(String remark) throws Exception;
	
	public int deleteIndexLogById(int logId) throws Exception;
}
