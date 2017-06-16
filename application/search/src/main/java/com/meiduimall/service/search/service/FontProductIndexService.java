package com.meiduimall.service.search.service;

import java.util.List;

import com.meiduimall.service.search.entity.Brand;
import com.meiduimall.service.search.entity.Item;
import com.meiduimall.service.search.entity.QueryIndexResult;
import com.meiduimall.service.search.entity.SearchParam;

public interface FontProductIndexService {

	/**
	 * 查询索引信息
	 * @param searchParam	查询参数
	 * @return
	 * @throws Exception
	 */
	public QueryIndexResult query(SearchParam searchParam) throws Exception;
	
	/**
	 * 类目查询
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	public QueryIndexResult list(SearchParam searchParam) throws Exception;
	
	/**
	 * 根据ID查询索引信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Item queryById(String id) throws Exception;

	public List<Brand> getFacetQuery(String keyword) throws Exception;
	
}
