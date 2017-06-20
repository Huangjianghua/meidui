package com.meiduimall.service.search.solr.dao;

import java.util.List;

import com.meiduimall.service.search.entity.Suggest;

public interface SuggestDao {

	public String querySuggest(String keyword) throws Exception;

	public List<String> querySuggest(String keyword, Integer limit) throws Exception;

	public int saveSuggestIndex(Suggest suggest) throws Exception;

	public int saveSuggestIndexes(List<Suggest> suggests) throws Exception;

	public int deleteSuggestById(String id) throws Exception;

	public int deleteSuggestByQuery(String query) throws Exception;
}
