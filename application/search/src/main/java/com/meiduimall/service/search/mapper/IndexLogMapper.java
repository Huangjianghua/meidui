package com.meiduimall.service.search.mapper;

import java.util.List;

import com.meiduimall.service.search.entity.IndexLog;
import com.meiduimall.service.search.page.PageView;

public interface IndexLogMapper {

	/**
	 * 查询索引日志信息
	 * 
	 * @return
	 */
	public List<IndexLog> queryIndexLogs(PageView pageView);

	/**
	 * 查询总记录数
	 * 
	 * @return
	 */
	public int queryIndexLogCount();

	/**
	 * 根据ID查询索引日志信息
	 * 
	 * @return
	 */
	public IndexLog queryIndexLogById(Integer id);

	/**
	 * 插入索引日志
	 * 
	 * @return
	 */
	public int insertIndexLog(IndexLog indexLogs);

	/**
	 * 根据ID删除索引日志
	 * 
	 * @return
	 */
	public int deleteIndexLogById(Integer id);

}
