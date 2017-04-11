package com.meiduimall.application.search.manage.dao;

import java.util.List;

import com.meiduimall.application.search.manage.pojo.CatModel;

public interface CatIndexDao {

	public CatModel getCatModelByQuery(String q) throws Exception;
	
	public int updateCatIndex(List<CatModel> catModels) throws Exception;
	
	public int updateCatIndexById(CatModel catModel) throws Exception;
	
	public int deleteCatIndexByQuery(String query) throws Exception;
	
	public int deleteCatIndexById(String id) throws Exception;
}
