package com.first.mapper;

import java.util.List;

import com.first.page.PageView;
import com.first.pojo.CatModel;

public interface CatMapper {
	
    /**
     * 查询类目信息
     * @return
     */
	public List<CatModel> queryCats(PageView pageView) throws Exception;
	
	/**
	 * 查询总记录数
	 * @return
	 */
	public long queryCatsCount() throws Exception;
	
	/**
	 * 根据ID查询类目信息
	 * @return
	 */
	public CatModel queryCatById(Integer id) throws Exception;

	/**
	 * 查询所有类目ID
	 * @return
	 */
	public List<Integer> queryAllCatsId();

}
