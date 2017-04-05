package com.meiduimall.application.search.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.application.search.IDao.SearchWordMapper;
import com.meiduimall.application.search.domain.SearchWord;
import com.meiduimall.application.search.page.PageView;
import com.meiduimall.application.search.services.SearchWordService;

@Service
public class SearchWordServiceImpl implements SearchWordService {

	@Resource
	private SearchWordMapper searchWordMapper;
	
	@Override
	public List<SearchWord> querySearchWords(PageView pageView) throws Exception {
		return searchWordMapper.querySearchWords(pageView);
	}

	@Override
	public SearchWord querySearchWordById(Integer wordId) throws Exception {
		return searchWordMapper.querySearchWordById(wordId);
	}

	@Override
	public int addSearchWord(SearchWord searchWord) throws Exception {
		return searchWordMapper.addSearchWord(searchWord);
	}

	@Override
	public int updateSearchWord(SearchWord searchWord) throws Exception {
		return searchWordMapper.updateSearchWord(searchWord);
	}

	@Override
	public int deleteSearchWordById(Integer wordId) throws Exception {
		return searchWordMapper.deleteSearchWordById(wordId);
	}

	@Override
	public int deleteSearchWordByName(String word) throws Exception {
		return searchWordMapper.deleteSearchWordByName(word);
	}

}
