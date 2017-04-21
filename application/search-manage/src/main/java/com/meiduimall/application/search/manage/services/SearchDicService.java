package com.meiduimall.application.search.manage.services;

import java.util.List;

import com.meiduimall.application.search.manage.domain.SearchDic;
import com.meiduimall.application.search.manage.page.PageView;

public interface SearchDicService {
	
	/**
	 * 查询词典内容
	 * @param pageView
	 * @return
	 * @throws Exception
	 */
	public List<SearchDic> querySearchDics(PageView pageView) ;
	
	/**
	 * 查询词典总记录
	 * @return
	 * @throws Exception
	 */
	public long querySearchDicsCount() ;
	
	/**
	 * 根据ID查询词典内容
	 * @param dicId
	 * @return
	 * @throws Exception
	 */
	public SearchDic querySearchDicById(Integer dicId);

	/**
	 * 添加搜索词典信息
	 * @param searchDic
	 * @return
	 * @throws Exception
	 */
	public int addSearchDic(SearchDic searchDic) ;
	
	/**
	 * 修改搜索词典信息
	 * @param searchDic
	 * @return
	 * @throws Exception
	 */
	public int updateSearchDic(SearchDic searchDic) ;
	
	/**
	 * 删除搜索词典信息
	 * @param dicId
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchDicById(Integer dicId) ;
	
	/**
	 * 删除搜索词典信息
	 * @param dicName
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchDicByName(String dicName) ;
}
