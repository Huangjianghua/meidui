package com.meiduimall.service.search.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.search.entity.IndexLog;
import com.meiduimall.service.search.mapper.IndexLogMapper;
import com.meiduimall.service.search.page.PageView;
import com.meiduimall.service.search.page.QueryResult;
import com.meiduimall.service.search.service.IndexLogService;

@Service
public class IndexLogServiceImpl implements IndexLogService {

	@Autowired
	private IndexLogMapper indexLogMapper;

	@Override
	public QueryResult queryIndexLogs(PageView pageView) throws Exception {
		List<IndexLog> indexLogs = indexLogMapper.queryIndexLogs(pageView);
		int count = indexLogMapper.queryIndexLogCount();
		QueryResult qr = new QueryResult();
		qr.setDateList(indexLogs);
		qr.setTotalCount(count);
		return qr;
	}

	@Override
	public int insertIndexLog(String remark) throws Exception {
		IndexLog indexLogs = new IndexLog();
		indexLogs.setRemark(remark);
		indexLogs.setLogTime(new Date());
		return indexLogMapper.insertIndexLog(indexLogs);
	}

	@Override
	public int deleteIndexLogById(int logId) throws Exception {
		return indexLogMapper.deleteIndexLogById(logId);
	}

}
