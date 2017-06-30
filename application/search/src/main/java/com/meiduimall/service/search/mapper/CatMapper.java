package com.meiduimall.service.search.mapper;

import java.util.List;

import com.meiduimall.service.search.entity.CatModel;
import com.meiduimall.service.search.page.PageView;

public interface CatMapper {

	/**
	 * 查询类目信息
	 * 
	 * @return
	 */
	public List<CatModel> queryCats(PageView pageView);

	/**
	 * 查询总记录数
	 * 
	 * @return
	 */
	public long queryCatsCount();

	/**
	 * 根据ID查询类目信息
	 * 
	 * @return
	 */
	public CatModel queryCatById(Integer id);

	/**
	 * 查询所有类目ID
	 * 
	 * @return
	 */
	public List<Integer> queryAllCatsId();

}
