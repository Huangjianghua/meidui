package com.meiduimall.application.search.manage.services;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.page.QueryResult;

public interface IndexLogService {

	public QueryResult queryIndexLogs(PageView pageView) ;

	public int insertIndexLog(String remark) ;
	
	public int deleteIndexLogById(int logId) ;
}
