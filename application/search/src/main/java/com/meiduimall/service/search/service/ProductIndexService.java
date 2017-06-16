package com.meiduimall.service.search.service;

import java.util.List;

import com.meiduimall.service.search.entity.Item;
import com.meiduimall.service.search.entity.QueryIndexResult;
import com.meiduimall.service.search.entity.SearchParam;

public interface ProductIndexService {

	/**
	 * 查询索引信息
	 * @param searchParam	查询参数
	 * @return
	 * @throws Exception
	 */
	public QueryIndexResult query(SearchParam searchParam) throws Exception;
	
	/**
	 * 根据ID查询索引信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Item queryById(String id) throws Exception;
	
	/**
	 * 查询索引库ID是否存在
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isExists(Integer id) throws Exception;
	
	/**
	 * 查询索引索引ID
	 * @return
	 * @throws Exception
	 */
	public List<Integer> queryIds() throws Exception;
	
	
	/**
	 * 根据条件查询索引ID
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<String> queryIndexByQuery(String query) throws Exception;
	
}
