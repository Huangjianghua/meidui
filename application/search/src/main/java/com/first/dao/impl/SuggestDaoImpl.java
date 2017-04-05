package com.first.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Repository;

import com.first.dao.SuggestDao;
import com.first.domain.Suggest;

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
	
	@Override
	public String querySuggest(String keyword) throws Exception {
		SolrQuery params = new SolrQuery();
	    StringBuilder sb = new StringBuilder();
	    sb.append("suggest:").append(keyword);
	    params.setQuery(sb.toString());
	    params.addField("kw");
	    params.addField("kwfreq");
	    params.addSort("kwfreq", ORDER.desc);
	    params.setStart(0);
	    params.setRows(1);
		QueryResponse response = suggest.query(params);
		List<Suggest> list = response.getBeans(Suggest.class);
		if (list != null && list.size() > 0) {
			return list.get(0).getKw();
		}
		return null;
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
	    solrQuery.addSort("kwfreq", ORDER.desc);
	    solrQuery.setStart(0);
	    solrQuery.setRows(limit);
	    return solrQuery;
	}
}
