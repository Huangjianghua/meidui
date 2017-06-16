package com.meiduimall.service.search.solr.dao;

import java.util.List;

import com.meiduimall.service.search.entity.CatModel;

public interface CatIndexDao {

	public CatModel getCatModelByQuery(String q) throws Exception;

	public List<CatModel> getCatModels(String q) throws Exception;

	public int updateCatIndex(List<CatModel> catModels) throws Exception;

	public int updateCatIndexById(CatModel catModel) throws Exception;

	public int deleteCatIndexByQuery(String query) throws Exception;

	public int deleteCatIndexById(String id) throws Exception;
}
