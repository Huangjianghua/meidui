package com.meiduimall.application.search.manage.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.IDao.SearchLogMapper;
import com.meiduimall.application.search.manage.domain.SearchLog;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.LogParam;
import com.meiduimall.application.search.manage.pojo.StatisticLog;
import com.meiduimall.application.search.manage.services.SearchLogService;

@Service
public class SearchLogServiceImpl implements SearchLogService {

	@Autowired
	private SearchLogMapper searchLogMapper;
	
	@Override
	public QueryResult querySearchLogs(LogParam logParam) throws Exception {
		List<SearchLog> searchLogs = searchLogMapper.querySearchLogs(logParam);
		long count = searchLogMapper.querySearchLogCount(logParam);
		QueryResult results = new QueryResult();
		results.setDateList(searchLogs);
		results.setTotalCount((int)count);
		return results;
	}

	@Override
	public QueryResult queryStatisticLogs(LogParam logParam) throws Exception {
		List<StatisticLog> statisticLogs = searchLogMapper.queryStatisticLogs(logParam);
		long count = searchLogMapper.queryStatisticCount(logParam);
		QueryResult results = new QueryResult();
		results.setDateList(statisticLogs);
		results.setTotalCount((int)count);
		return results;
	}

	@Override
	public SearchLog querySearchLogByKeyword(String keyword) throws Exception {
		return searchLogMapper.querySearchLogByKeyword(keyword);
	}

	@Override
	public SearchLog querySearchLogById(Integer logId) throws Exception {
		return searchLogMapper.querySearchLogById(logId);
	}

	@Override
	public int addSearchLog(List<SearchLog> searchLogs) throws Exception {
		return searchLogMapper.addSearchLog(searchLogs);
	}

	@Override
	public int deleteSearchLogById(Integer dicId) throws Exception {
		return searchLogMapper.deleteSearchLogById(dicId);
	}

}
