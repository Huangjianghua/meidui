package com.meiduimall.application.search.manage.services;

import java.util.List;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.pojo.CatModel;

public interface CatService {

	public List<CatModel> queryCats(PageView pageView) ;
	
	public long queryCatsCount() ;
	
	public CatModel queryCatById(Integer id) ;

	public List<Integer> queryAllCatsId();
}
