package com.meiduimall.application.search.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.dao.SuggestDao;
import com.meiduimall.application.search.manage.domain.Suggest;

@Repository
public class SuggestDaoImpl implements SuggestDao {
	
	@Resource
	private SolrClient suggest;
	
	@Override
	public List<String> querySuggest(String keyword, Integer limit) throws Exception {
		SolrQuery params = getSuggestQuery(keyword, limit);
		QueryResponse response = suggest.query(params);
		List<Suggest> suggests = response.getBeans(Suggest.class);
		List<String> list = new ArrayList<String>();
		for (Suggest suggest : suggests) {
			list.add(suggest.getKw());
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
	public int saveSuggestIndex(Suggest s) throws Exception {
		UpdateResponse response = suggest.addBean(s);
		suggest.commit();
		return response.getStatus();
	}

	@Override
	public int saveSuggestIndexes(List<Suggest> suggests) throws Exception {
		UpdateResponse response = suggest.addBeans(suggests);
		suggest.commit();
		return response.getStatus();
	}

	@Override
	public int deleteSuggestById(String id) throws Exception {
		UpdateResponse response = suggest.deleteById(id);
		suggest.commit();
		return response.getStatus();
	}

	@Override
	public int deleteSuggestByQuery(String query) throws Exception {
		UpdateResponse response = suggest.deleteByQuery(query);
		suggest.commit();
		return response.getStatus();
	}
	
}
