package com.first.services;

import com.first.page.PageView;
import com.first.page.QueryResult;

public interface IndexLogService {

	public QueryResult queryIndexLogs(PageView pageView) throws Exception;

	public int insertIndexLog(String remark) throws Exception;
	
	public int deleteIndexLogById(int logId) throws Exception;
}
