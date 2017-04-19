package com.meiduimall.application.search.manage.dao;

import java.util.List;

import com.meiduimall.application.search.manage.domain.Suggest;

public interface SuggestDao {

	public List<String> querySuggest(String keyword, Integer limit);
	
	public int saveSuggestIndex(Suggest suggest);
	
	public int saveSuggestIndexes(List<Suggest> suggests);
	
	public int deleteSuggestById(String id) ;
	
	public int deleteSuggestByQuery(String query);
}
