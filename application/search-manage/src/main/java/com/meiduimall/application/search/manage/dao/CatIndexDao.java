package com.meiduimall.application.search.manage.dao;

import java.util.List;

import com.meiduimall.application.search.manage.pojo.CatModel;

public interface CatIndexDao {

	public CatModel getCatModelByQuery(String q);
	
	public int updateCatIndex(List<CatModel> catModels);
	
	public int updateCatIndexById(CatModel catModel);
	
	public int deleteCatIndexByQuery(String query);
	
	public int deleteCatIndexById(String id);
}
