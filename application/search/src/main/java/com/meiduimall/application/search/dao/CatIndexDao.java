package com.meiduimall.application.search.dao;

import java.util.List;

import com.meiduimall.application.search.pojo.CatModel;

public interface CatIndexDao {

	public CatModel getCatModelByQuery(String q) throws Exception;
	
	public List<CatModel> getCatModels(String q) throws Exception;
}
