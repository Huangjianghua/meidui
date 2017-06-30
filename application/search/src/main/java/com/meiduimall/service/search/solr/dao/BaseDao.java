package com.meiduimall.service.search.solr.dao;

import java.util.List;

import com.meiduimall.service.search.page.JqGridTableViewPage;

public interface BaseDao<T> {

	/**
	 * 按接口查询(jqGrid)
	 * 
	 * @param url
	 *            接口地址
	 * @param pageNo
	 *            当前查询的页码
	 * @param pageSize
	 *            每页显示的条数
	 * @return
	 */
	public JqGridTableViewPage<T> findJqGridPageByPort(String url, int pageNo, int pageSize, Class objectClass);

	/**
	 * 按接口查询
	 * 
	 * @param url
	 *            接口地址
	 * @param pageNo
	 *            当前查询的页码
	 * @param pageSize
	 *            每页显示的条数
	 * @return
	 */
	public List<T> findPageByPort(String url, int pageNo, int pageSize, Class objectClass);
}
