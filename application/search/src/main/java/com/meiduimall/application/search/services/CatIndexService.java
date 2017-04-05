package com.meiduimall.application.search.services;

import com.meiduimall.application.search.pojo.CatModel;

public interface CatIndexService {

	public CatModel getCatModelByQuery(String q) throws Exception;
}
