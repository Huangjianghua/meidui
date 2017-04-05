package com.first.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.first.base.JqGridTableViewPage;
import com.first.dao.BaseDao;
import com.first.utility.HttpTooUtils;

public class BaseDaoImpl<T> implements BaseDao<T> {

	
	/**
	 * 按接口查询(jqGrid)
	 * @param url                接口地址
	 * @param pageNo             当前查询的页码
	 * @param pageSize           每页显示的条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JqGridTableViewPage<T> findJqGridPageByPort(String url,
			 int pageNo, int pageSize, Class objectClass) {		
		JqGridTableViewPage<T> jqTable = new JqGridTableViewPage<T>();
        int currentPage = pageNo > 1 ? pageNo : 1;
        jqTable.setRecords(20);
//        long total = filterTotal.intValue() % pageSize > 0 ? filterTotal.intValue() / pageSize + 1 : filterTotal.intValue() / pageSize;
        jqTable.setTotal(200);
//        List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        String resultJson = HttpTooUtils.sendGet(url);
        resultJson = resultJson.replace("null", "\"\"");
        JSONObject jsonObj = JSONObject.fromObject(resultJson);
        if(jsonObj.get("RESULTS") == null 
        		|| jsonObj.get("RESULTS").equals("") 
        		|| jsonObj.getJSONArray("RESULTS").size() == 0) {
        	return null;
        }
        List<T> itemList = JSONArray.toList(jsonObj.getJSONArray("RESULTS"),objectClass);
        if (itemList == null)
        {
            itemList = new ArrayList<T>();
        }
        jqTable.setRows(itemList);
        return jqTable;
	}
	
	/**
	 * 按接口查询
	 * @param url                接口地址
	 * @param pageNo             当前查询的页码
	 * @param pageSize           每页显示的条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findPageByPort(String url,
			 int pageNo, int pageSize, Class objectClass) {		
//        int currentPage = pageNo > 1 ? pageNo : 1;
        String resultJson = HttpTooUtils.sendGet(url);
        resultJson = resultJson.replace("null", "\"\"");
        JSONObject jsonObj = JSONObject.fromObject(resultJson);
        if(jsonObj.get("RESULTS") == null 
        		|| jsonObj.get("RESULTS").equals("") 
        		|| jsonObj.getJSONArray("RESULTS").size() == 0) {
        	return null;
        }
        List<T> itemList = JSONArray.toList(jsonObj.getJSONArray("RESULTS"),objectClass);
        if (itemList == null)
        {
            itemList = new ArrayList<T>();
        }
        return itemList;
	}
	
}
