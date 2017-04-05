package com.first.services;

import java.util.List;

import com.first.page.PageView;
import com.first.pojo.CatModel;

public interface CatService {

	public List<CatModel> queryCats(PageView pageView) throws Exception;
	
	public long queryCatsCount() throws Exception;
	
	public CatModel queryCatById(Integer id) throws Exception;

	public List<Integer> queryAllCatsId();
}
