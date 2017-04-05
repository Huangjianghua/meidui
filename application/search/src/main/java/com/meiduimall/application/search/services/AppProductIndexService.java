package com.meiduimall.application.search.services;

import java.util.List;

import com.meiduimall.application.search.domain.Item;
import com.meiduimall.application.search.pojo.AppSearchParam;
import com.meiduimall.application.search.pojo.Brand;
import com.meiduimall.application.search.pojo.QueryIndexResult;
import com.meiduimall.application.search.pojo.SearchParam;

public interface AppProductIndexService {

	/**
	 * 查询索引信息
	 * @param searchParam	查询参数
	 * @return
	 * @throws Exception
	 */
	public QueryIndexResult query(AppSearchParam searchParam) throws Exception;
	
	/**
	 * 类目查询
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	public QueryIndexResult list(AppSearchParam searchParam) throws Exception;
	
	/**
	 * 根据ID查询索引信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Item queryById(String id) throws Exception;

	public List<Brand> getFacetQuery(String keyword) throws Exception;
	
}
