package com.meiduimall.application.search.IDao;

import java.util.List;

import com.meiduimall.application.search.domain.SearchWord;
import com.meiduimall.application.search.page.PageView;

public interface SearchWordMapper {
	
	/**
	 * 查询搜索词内容
	 * @param pageView
	 * @return
	 * @throws Exception
	 */
	public List<SearchWord> querySearchWords(PageView pageView) throws Exception;
	
	/**
	 * 查询搜索词总数
	 * @return
	 * @throws Exception
	 */
	public long querySearchWordCount() throws Exception;
	
	/**
	 * 根据ID查询搜索词内容
	 * @param wordId
	 * @return
	 * @throws Exception
	 */
	public SearchWord querySearchWordById(Integer wordId) throws Exception;

	/**
	 * 添加搜索词信息
	 * @param searchWord
	 * @return
	 * @throws Exception
	 */
	public int addSearchWord(SearchWord searchWord) throws Exception;
	
	/**
	 * 修改搜索词信息
	 * @param searchWord
	 * @return
	 * @throws Exception
	 */
	public int updateSearchWord(SearchWord searchWord) throws Exception;
	
	/**
	 * 删除搜索词信息
	 * @param dicId
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchWordById(Integer dicId) throws Exception;
	
	/**
	 * 删除搜索词信息
	 * @param dicName
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchWordByName(String dicName) throws Exception;
}
