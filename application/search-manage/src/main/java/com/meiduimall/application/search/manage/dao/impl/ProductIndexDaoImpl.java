package com.meiduimall.application.search.manage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.dao.ProductIndexDao;

@Repository
public class ProductIndexDaoImpl implements ProductIndexDao {
	
	@Resource
	private SolrClient products;
	
	@Override
	public QueryResponse query(SolrQuery params) throws Exception {
		return products.query(params);
	}

	@Override
	public int saveIndex(SolrInputDocument doc) throws Exception {
		products.add(doc);
		UpdateResponse response = products.commit();
		return response.getStatus();
	}

	@Override
	public int saveIndexes(List<SolrInputDocument> docs) throws Exception {
		products.add(docs);
		UpdateResponse response = products.commit();
		return response.getStatus();
	}

	@Override
	public int deleteById(String id) throws Exception {
		UpdateResponse response = products.deleteById(id);
		products.commit();
		return response.getStatus();
	}

	@Override
	public int deleteByIds(List<String> ids) throws Exception {
		UpdateResponse response = products.deleteById(ids);
		products.commit();
		return response.getStatus();
	}

	@Override
	public int deleteByQuery(String query) throws Exception {
		UpdateResponse response = products.deleteByQuery(query);
		products.commit();
		return response.getStatus();
	}

}
