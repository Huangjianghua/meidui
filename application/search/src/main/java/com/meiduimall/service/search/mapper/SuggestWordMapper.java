package com.meiduimall.service.search.mapper;

import java.util.List;

import com.meiduimall.service.search.entity.SuggestWord;
import com.meiduimall.service.search.page.PageView;

public interface SuggestWordMapper {

	/**
	 * 查询搜索提示词内容
	 * 
	 * @param pageView
	 * @return
	 * @throws Exception
	 */
	public List<SuggestWord> querySuggestWords(PageView pageView);

	/**
	 * 查询搜索提示词总数
	 * 
	 * @return
	 * @throws Exception
	 */
	public long querySuggestWordCount(String kw);

	/**
	 * 根据ID查询搜索提示词内容
	 * 
	 * @param suggestId
	 * @return
	 * @throws Exception
	 */
	public SuggestWord querySuggestWordById(Integer suggestId);

	/**
	 * 根据关键词查询搜索提示词内容
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<SuggestWord> querySuggestWordByKey(String key);

	/**
	 * 根据关键词模糊查询搜索提示词内容
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<SuggestWord> fuzzyQuerySuggestWord(String key);

	/**
	 * 添加搜索提示词信息
	 * 
	 * @param suggestWord
	 * @return
	 * @throws Exception
	 */
	public int addSuggestWord(SuggestWord suggestWord);

	/**
	 * 修改搜索提示词信息
	 * 
	 * @param suggestWord
	 * @return
	 * @throws Exception
	 */
	public int updateSuggestWord(SuggestWord suggestWord);

	/**
	 * 删除搜索提示词信息
	 * 
	 * @param suggestId
	 * @return
	 * @throws Exception
	 */
	public int deleteSuggestWordById(Integer suggestId);
}
