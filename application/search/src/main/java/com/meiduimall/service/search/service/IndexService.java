package com.meiduimall.service.search.service;

import java.util.Map;

public interface IndexService {

	public Map<String, Object> addProductIndex(Integer pageSize);

	public Map<String, Object> addProductIndexById(Integer itemId);

	public Map<String, Object> deleteProductIndexByItemId(String itemId);

	public Map<String, Object> deleteProductIndexByQuery(String query);

	public Map<String, Object> addSuggestIndex(Integer pageSize);

	public Map<String, Object> addSuggestIndexById(String key);

	public Map<String, Object> addSuggestIndexById(int suggestId);

	public Map<String, Object> deleteSuggestIndexById(String key);

	public Map<String, Object> deleteSuggestIndexByQuery(String key);

	public Map<String, Object> deleteAllSuggestIndex();

	public Map<String, Object> addCatlogIndex(int pageSize);

	public Map<String, Object> addCatlogIndexById(int catId);

	public Map<String, Object> deleteCatlogIndexByItemId(String catId);

	public Map<String, Object> deleteCatlogIndexByQuery(String query);

}
