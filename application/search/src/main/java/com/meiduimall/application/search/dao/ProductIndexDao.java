package com.meiduimall.application.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface ProductIndexDao {

	/**
	 * 查询索引信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public QueryResponse query(SolrQuery params) throws Exception;
	
}
