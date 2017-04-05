package com.meiduimall.application.search.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.IDao.SearchLogMapper;
import com.meiduimall.application.search.domain.SearchLog;
import com.meiduimall.application.search.page.PageView;
import com.meiduimall.application.search.services.SearchLogService;

@Service
public class SearchLogServiceImpl implements SearchLogService {

	@Autowired
	private SearchLogMapper searchLogMapper;
	
	@Override
	public List<SearchLog> querySearchLogs(PageView pageView) throws Exception {
		return searchLogMapper.querySearchLogs(pageView);
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
	public int updateSearchLog(SearchLog searchLog) throws Exception {
		return searchLogMapper.updateSearchLog(searchLog);
	}

	@Override
	public int deleteSearchLogById(Integer dicId) throws Exception {
		return searchLogMapper.deleteSearchLogById(dicId);
	}

}
