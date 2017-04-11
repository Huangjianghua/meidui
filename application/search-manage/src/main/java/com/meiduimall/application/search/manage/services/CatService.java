package com.meiduimall.application.search.manage.services;

import java.util.List;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.pojo.CatModel;

public interface CatService {

	public List<CatModel> queryCats(PageView pageView) throws Exception;
	
	public long queryCatsCount() throws Exception;
	
	public CatModel queryCatById(Integer id) throws Exception;

	public List<Integer> queryAllCatsId();
}
