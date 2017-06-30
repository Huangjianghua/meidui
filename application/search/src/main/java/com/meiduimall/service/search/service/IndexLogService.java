package com.meiduimall.service.search.service;

import com.meiduimall.service.search.page.PageView;
import com.meiduimall.service.search.page.QueryResult;

public interface IndexLogService {

	public QueryResult queryIndexLogs(PageView pageView) throws Exception;

	public int insertIndexLog(String remark) throws Exception;

	public int deleteIndexLogById(int logId) throws Exception;
}
