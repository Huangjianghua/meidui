package com.meiduimall.application.search.services;

import java.util.List;

import com.meiduimall.application.search.page.PageView;
import com.meiduimall.application.search.pojo.CatModel;

public interface CatService {

	public List<CatModel> queryCats(PageView pageView) throws Exception;
	
	public long queryCatsCount() throws Exception;
	
	public CatModel queryCatById(Integer id) throws Exception;

	public List<Integer> queryAllCatsId();
}
