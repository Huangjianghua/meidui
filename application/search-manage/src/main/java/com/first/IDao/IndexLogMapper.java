package com.first.IDao;

import java.util.List;

import com.first.page.PageView;
import com.first.pojo.IndexLog;

public interface IndexLogMapper {
	
    /**
     * 查询索引日志信息
     * @return
     */
	public List<IndexLog> queryIndexLogs(PageView pageView) throws Exception;
	
	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryIndexLogCount() throws Exception;
	
	/**
	 * 根据ID查询索引日志信息
	 * @return
	 */
	public IndexLog queryIndexLogById(Integer id) throws Exception;
	
	/**
	 * 插入索引日志
	 * @return
	 */
	public int insertIndexLog(IndexLog indexLogs) throws Exception;
	
	/**
	 * 根据ID删除索引日志
	 * @return
	 */
	public int deleteIndexLogById(Integer id) throws Exception;

}
