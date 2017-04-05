package com.first.system.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.IDao.SystemlogMapper;
import com.first.page.QueryResult;
import com.first.system.domain.Systemlog;
import com.first.system.services.ISystemlogService;

@Service
public class SystemlogServiceImpl implements ISystemlogService{
    @Resource
    private  SystemlogMapper systemLogDao;
    
    /**
	 * 增加登入日志
	 * @param log
	 */
	public void addSysLog(Systemlog log) {
		systemLogDao.addSysLog(log);
	}
    
	/**
	 * 分页查询系统登入日志
	 * @param log
	 * @return QueryResult
	 */
	public QueryResult selectPageList(Systemlog log) {
		QueryResult result = new QueryResult();
		result.setDateList(systemLogDao.selectPageList(log));
		result.setTotalCount(systemLogDao.pageCount(log));
		return result;
	}

}
