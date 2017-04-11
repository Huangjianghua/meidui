package com.meiduimall.application.search.manage.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.IDao.SearchDicMapper;
import com.meiduimall.application.search.manage.domain.SearchDic;
import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.services.SearchDicService;

@Service
public class SeachDicServiceImpl implements SearchDicService {

	@Resource
	private SearchDicMapper searchDicMapper;
	
	@Override
	public List<SearchDic> querySearchDics(PageView pageView) throws Exception {
		return searchDicMapper.querySearchDics(pageView);
	}

	@Override
	public long querySearchDicsCount() throws Exception {
		return searchDicMapper.querySearchDicCount();
	}

	@Override
	public SearchDic querySearchDicById(Integer dicId) throws Exception {
		return searchDicMapper.querySearchDicById(dicId);
	}

	@Override
	public int addSearchDic(SearchDic searchDic) throws Exception {
		return searchDicMapper.addSearchDic(searchDic);
	}

	@Override
	public int updateSearchDic(SearchDic searchDic) throws Exception {
		return searchDicMapper.updateSearchDic(searchDic);
	}

	@Override
	public int deleteSearchDicById(Integer dicId) throws Exception {
		return searchDicMapper.deleteSearchDicById(dicId);
	}

	@Override
	public int deleteSearchDicByName(String dicName) throws Exception {
		return searchDicMapper.deleteSearchDicByName(dicName);
	}

}
