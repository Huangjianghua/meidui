package com.meiduimall.application.search.manage.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.meiduimall.application.search.manage.dao.ProductIndexDao;
import com.meiduimall.core.util.JsonUtils;

@Repository
public class ProductIndexDaoImpl implements ProductIndexDao {
	
	private static Logger logger = LoggerFactory.getLogger(ProductIndexDaoImpl.class);
	
	@Resource
	private SolrClient products;
	
	@Override
	public QueryResponse query(SolrQuery params)  {
		QueryResponse res=new QueryResponse();
		 try {
			 res=products.query(params);
		} catch (Exception e) {
			logger.error("查询商品信息异常:{}", e);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		} 
		return res; 
	}

	@Override
	public int saveIndex(SolrInputDocument doc) {
		UpdateResponse response=new UpdateResponse();
		try {
			products.add(doc);
			response = products.commit();
		} catch (Exception e) {
			logger.error("保存商品信息异常:{}", e);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}

	@Override
	public int saveIndexes(List<SolrInputDocument> docs){
		UpdateResponse response=new UpdateResponse();
		try {
			products.add(docs);
			response = products.commit();
		} catch (Exception e) {
			logger.error("批量保存商品信息异常:{}", e);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}

	@Override
	public int deleteById(String id){
		UpdateResponse response=new UpdateResponse();
		try {
			response = products.deleteById(id);
			products.commit();
		} catch (Exception e) {
			logger.error("通过id删除商品信息异常:{},删除id:{}", e,id);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}

	@Override
	public int deleteByIds(List<String> ids) {
		UpdateResponse response=new UpdateResponse();
		try {
			response = products.deleteById(ids);
			products.commit();
		} catch (Exception e) {
			logger.error("通过id批量删除商品信息异常:{},删除ids:{}", e,JsonUtils.beanToJson(ids));
			e.printStackTrace();
		}
		return response.getStatus();
	}

	@Override
	public int deleteByQuery(String query) {
		UpdateResponse response=new UpdateResponse();
		try {
			response = products.deleteByQuery(query);
			products.commit();
		} catch (Exception e) {
			logger.error("按照条件删除商品信息异常:{},删除条件:{}", e,query);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}

}
