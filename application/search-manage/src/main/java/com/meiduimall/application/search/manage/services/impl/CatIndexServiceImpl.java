package com.meiduimall.application.search.manage.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.dao.CatIndexDao;
import com.meiduimall.application.search.manage.pojo.CatModel;
import com.meiduimall.application.search.manage.services.CatIndexService;

@Service
public class CatIndexServiceImpl implements CatIndexService {

	@Autowired
	private CatIndexDao catIndexDao;
	
	@Override
	public CatModel getCatModelByQuery(String q) throws Exception {
		return catIndexDao.getCatModelByQuery(q);
	}

	@Override
	public int updateCatIndex(List<CatModel> catModels) throws Exception {
		return catIndexDao.updateCatIndex(catModels);
	}

	@Override
	public int updateCatIndexById(CatModel catModel) throws Exception {
		return catIndexDao.updateCatIndexById(catModel);
	}

	@Override
	public int deleteCatIndexByQuery(String query) throws Exception {
		return catIndexDao.deleteCatIndexByQuery(query);
	}

	@Override
	public int deleteCatIndexById(String id) throws Exception {
		return catIndexDao.deleteCatIndexById(id);
	}

}
