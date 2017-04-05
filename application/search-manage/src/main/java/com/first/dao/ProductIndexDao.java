package com.first.dao;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

public interface ProductIndexDao {

	/**
	 * 查询索引信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public QueryResponse query(SolrQuery params) throws Exception;
	
	/**
	 * 添加单个SolrInputDocument对象到索引库
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public int saveIndex(SolrInputDocument doc) throws Exception;
	
	/**
	 * 添加多个SolrInputDocument对象到索引库
	 * @param docs
	 * @return
	 * @throws Exception
	 */
	public int saveIndexes(List<SolrInputDocument> docs) throws Exception;
	
	/**
	 * 根据ID删除索引信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(String id) throws Exception;
	
	/**
	 * 根据多个ID删除多个索引信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deleteByIds(List<String> ids) throws Exception;
	
	/**
	 * 根据查询条件删除索引信息
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public int deleteByQuery(String query) throws Exception;
}
