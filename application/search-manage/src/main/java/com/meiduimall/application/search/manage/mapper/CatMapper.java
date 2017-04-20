package com.meiduimall.application.search.manage.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.pojo.CatModel;


@Repository
public interface CatMapper {
	
    /**
     * 查询类目信息
     * @return
     */
	public List<CatModel> queryCats(PageView pageView) ;
	
	/**
	 * 查询总记录数
	 * @return
	 */
	public long queryCatsCount() ;
	
	/**
	 * 根据ID查询类目信息
	 * @return
	 */
	public CatModel queryCatById(Integer id) ;

	/**
	 * 查询所有类目ID
	 * @return
	 */
	public List<Integer> queryAllCatsId();

}
