package com.meiduimall.application.search.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.application.search.dao.SuggestDao;
import com.meiduimall.application.search.services.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService {

	@Resource
	private SuggestDao suggestDao;
	
	@Override
	public List<String> querySuggest(String keyword, Integer limit) throws Exception {
		return suggestDao.querySuggest(keyword, limit);
	}

	@Override
	public String querySuggest(String keyword) throws Exception {
		return suggestDao.querySuggest(keyword);
	}

}
