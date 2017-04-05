package com.first.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.mapper.CatMapper;
import com.first.page.PageView;
import com.first.pojo.CatModel;
import com.first.services.CatService;

@Service
public class CatServiceImpl implements CatService {
	
	@Resource
	private CatMapper catMapper;

	@Override
	public List<CatModel> queryCats(PageView pageView) throws Exception {
		return catMapper.queryCats(pageView);
	}

	@Override
	public long queryCatsCount() throws Exception {
		return catMapper.queryCatsCount();
	}

	@Override
	public CatModel queryCatById(Integer id) throws Exception {
		return catMapper.queryCatById(id);
	}

	@Override
	public List<Integer> queryAllCatsId() {
		return catMapper.queryAllCatsId();
	}

}
