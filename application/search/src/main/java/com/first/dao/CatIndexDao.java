package com.first.dao;

import java.util.List;

import com.first.pojo.CatModel;

public interface CatIndexDao {

	public CatModel getCatModelByQuery(String q) throws Exception;
	
	public List<CatModel> getCatModels(String q) throws Exception;
}
