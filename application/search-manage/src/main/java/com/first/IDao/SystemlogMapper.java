package com.first.IDao;

import java.util.List;
import com.first.system.domain.Systemlog;

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
