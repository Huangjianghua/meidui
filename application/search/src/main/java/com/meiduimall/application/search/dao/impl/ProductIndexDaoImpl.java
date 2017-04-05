package com.meiduimall.application.search.dao.impl;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.dao.ProductIndexDao;

@Repository
public class ProductIndexDaoImpl implements ProductIndexDao {
	
	@Resource
	private SolrClient products;
	
	@Override
	public QueryResponse query(SolrQuery params) throws Exception {
		return products.query(params);
	}

}
