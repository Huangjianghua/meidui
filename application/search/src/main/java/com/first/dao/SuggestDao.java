package com.first.dao;

import java.util.List;

public interface SuggestDao {

	public List<String> querySuggest(String keyword, Integer limit) throws Exception;
	
	public String querySuggest(String keyword) throws Exception;
}
