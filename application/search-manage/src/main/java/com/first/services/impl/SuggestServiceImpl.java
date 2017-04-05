package com.first.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.dao.SuggestDao;
import com.first.services.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService {

	@Resource
	private SuggestDao suggestDao;
	
	@Override
	public List<String> querySuggest(String keyword, Integer limit) throws Exception {
		return suggestDao.querySuggest(keyword, limit);
	}

}
