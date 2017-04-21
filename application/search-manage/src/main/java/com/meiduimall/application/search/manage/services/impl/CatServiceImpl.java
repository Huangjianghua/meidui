package com.meiduimall.application.search.manage.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.mapper.CatMapper;
import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.pojo.CatModel;
import com.meiduimall.application.search.manage.services.CatService;

@Service
public class CatServiceImpl implements CatService {
	
	@Resource
	private CatMapper catMapper;

	@Override
	public List<CatModel> queryCats(PageView pageView)  {
		return catMapper.queryCats(pageView);
	}

	@Override
	public long queryCatsCount()  {
		return catMapper.queryCatsCount();
	}

	@Override
	public CatModel queryCatById(Integer id)  {
		return catMapper.queryCatById(id);
	}

	@Override
	public List<Integer> queryAllCatsId() {
		return catMapper.queryAllCatsId();
	}

}
