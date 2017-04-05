package com.first.dao;

import java.util.List;

import com.first.domain.Suggest;

public interface SuggestDao {

	public List<String> querySuggest(String keyword, Integer limit) throws Exception;
	
	public int saveSuggestIndex(Suggest suggest) throws Exception;
	
	public int saveSuggestIndexes(List<Suggest> suggests) throws Exception;
	
	public int deleteSuggestById(String id) throws Exception;
	
	public int deleteSuggestByQuery(String query) throws Exception;
}
