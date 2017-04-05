package com.first.services;

import java.util.List;

import com.first.domain.Item;
import com.first.pojo.Brand;
import com.first.pojo.QueryIndexResult;
import com.first.pojo.SearchParam;

public interface ProductIndexService {

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
