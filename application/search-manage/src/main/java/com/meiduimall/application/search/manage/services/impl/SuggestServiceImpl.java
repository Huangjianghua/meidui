package com.meiduimall.application.search.manage.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.dao.SuggestDao;
import com.meiduimall.application.search.manage.services.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService {

	@Resource
	private SuggestDao suggestDao;
	
	@Override
	public List<String> querySuggest(String keyword, Integer limit){
		return suggestDao.querySuggest(keyword, limit);
	}

}
