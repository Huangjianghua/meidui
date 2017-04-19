package com.meiduimall.application.search.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.dao.SuggestDao;
import com.meiduimall.application.search.manage.domain.Suggest;
import com.meiduimall.core.util.JsonUtils;

@Repository
public class SuggestDaoImpl implements SuggestDao {
	
	private static Logger logger = LoggerFactory.getLogger(SuggestDaoImpl.class);
	
	@Resource
	private SolrClient suggest;
	
	@Override
	public List<String> querySuggest(String keyword, Integer limit)  {
		List<String> list=null;
		try {
			SolrQuery params = getSuggestQuery(keyword, limit);
			QueryResponse response = suggest.query(params);
			List<Suggest> suggests = response.getBeans(Suggest.class);
			list = new ArrayList<String>();
			for (Suggest suggest : suggests) {
				list.add(suggest.getKw());
			}
		} catch (Exception e) {
			logger.error("搜索提示查询异常:{},keyword:{},limit:{}",e,keyword,limit);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return list;
	}

	/**
	 * 搜索提示查询条件
	 * @param prefix	输入前缀
	 * @param limit		查询返回数量
	 * @return
	 */
	private SolrQuery getSuggestQuery(String prefix, Integer limit) {
	    SolrQuery solrQuery = new SolrQuery();
	    StringBuilder sb = new StringBuilder();
	    sb.append("suggest:").append(prefix).append("*");
	    solrQuery.setQuery(sb.toString());
	    solrQuery.addField("kw");
	    solrQuery.addField("kwfreq");
	    solrQuery.addSort("kwfreq", SolrQuery.ORDER.desc);
	    solrQuery.setStart(0);
	    solrQuery.setRows(limit);
	    return solrQuery;
	}

	@Override
	public int saveSuggestIndex(Suggest s){
		UpdateResponse response=new UpdateResponse();
		try {
			response = suggest.addBean(s);
			suggest.commit();
		} catch (Exception e) {
			logger.error("搜索提示保存异常:{},Suggest:{}",e,JsonUtils.beanToJson(s));
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}

	@Override
	public int saveSuggestIndexes(List<Suggest> suggests)  {
		UpdateResponse response=new UpdateResponse();
		try {
			response = suggest.addBeans(suggests);
			suggest.commit();
		} catch (Exception e) {
			logger.error("搜索提示批量保存异常:{},suggests:{}",e,JsonUtils.beanToJson(suggests));
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}

	@Override
	public int deleteSuggestById(String id) {
		UpdateResponse response=new UpdateResponse();
		try {
			response = suggest.deleteById(id);
			suggest.commit();
		} catch (Exception e) {
			logger.error("搜索提示删除异常:{},id:{}",e,id);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}

	@Override
	public int deleteSuggestByQuery(String query){
		UpdateResponse response=new UpdateResponse();
		try {
			response = suggest.deleteByQuery(query);
			suggest.commit();
		} catch (Exception e) {
			logger.error("搜索提示批量删除异常:{},query:{}",e,query);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return response.getStatus();
	}
	
}
