package com.first.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.dao.CatIndexDao;
import com.first.pojo.CatModel;
import com.first.services.CatIndexService;

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
