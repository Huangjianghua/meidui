package com.meiduimall.application.search.manage.IDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.system.domain.Systemlog;


public interface SystemlogMapper {

	public  int  addSysLog(Systemlog log);

	public List<Systemlog> selectPageList(Systemlog log);
	
	/**
	 * 查询总记录数
	 * @param user
	 * @return int
	 */
	public int pageCount(Systemlog log); 
}
