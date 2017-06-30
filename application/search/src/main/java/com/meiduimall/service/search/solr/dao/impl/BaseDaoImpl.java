package com.meiduimall.service.search.solr.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.exception.DaoException;
import com.meiduimall.service.search.page.JqGridTableViewPage;
import com.meiduimall.service.search.solr.dao.BaseDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseDaoImpl<T> implements BaseDao<T> {

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
	@SuppressWarnings("unchecked")
	public JqGridTableViewPage<T> findJqGridPageByPort(String url, int pageNo, int pageSize, Class objectClass) {
		JqGridTableViewPage<T> jqTable = new JqGridTableViewPage<T>();
		jqTable.setRecords(20);
		jqTable.setTotal(200);
		String resultJson = "";
		try {
			resultJson = HttpUtils.get(url);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		resultJson = resultJson.replace("null", "\"\"");
		JSONObject jsonObj = JSONObject.fromObject(resultJson);
		if (jsonObj.get("RESULTS") == null || "".equals(jsonObj.get("RESULTS"))
				|| jsonObj.getJSONArray("RESULTS").isEmpty()) {
			return null;
		}
		List<T> itemList = JSONArray.toList(jsonObj.getJSONArray("RESULTS"), objectClass);
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		jqTable.setRows(itemList);
		return jqTable;
	}

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
	@SuppressWarnings("unchecked")
	public List<T> findPageByPort(String url, int pageNo, int pageSize, Class objectClass) {
		String resultJson = "";
		try {
			resultJson = HttpUtils.get(url);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		resultJson = resultJson.replace("null", "\"\"");
		JSONObject jsonObj = JSONObject.fromObject(resultJson);
		if (jsonObj.get("RESULTS") == null || "".equals(jsonObj.get("RESULTS"))
				|| jsonObj.getJSONArray("RESULTS").isEmpty()) {
			return null;
		}
		List<T> itemList = JSONArray.toList(jsonObj.getJSONArray("RESULTS"), objectClass);
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		return itemList;
	}

}
