package com.meiduimall.service.search.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.service.search.service.SuggestService;
import com.meiduimall.service.search.solr.dao.SuggestDao;

@Service
public class SuggestServiceImpl implements SuggestService {

	@Resource
	private SuggestDao suggestDao;

	public List<String> querySuggest(String keyword, Integer limit) throws Exception {
		return suggestDao.querySuggest(keyword, limit);
	}

	public String querySuggest(String keyword) throws Exception {
		return suggestDao.querySuggest(keyword);
	}

}
