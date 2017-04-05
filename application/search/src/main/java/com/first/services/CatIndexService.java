package com.first.services;

import com.first.pojo.CatModel;

public interface CatIndexService {

	public CatModel getCatModelByQuery(String q) throws Exception;
}
