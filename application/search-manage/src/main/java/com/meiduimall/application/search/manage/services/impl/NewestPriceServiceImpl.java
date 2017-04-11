package com.meiduimall.application.search.manage.services.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.IDao.NewestPriceMapper;
import com.meiduimall.application.search.manage.pojo.NewestPrice;
import com.meiduimall.application.search.manage.services.NewestPriceService;

@Service
public class NewestPriceServiceImpl implements NewestPriceService {

	@Resource
	private NewestPriceMapper newestPriceMapper;
	
	@Override
	public LinkedList<NewestPrice> getNewestPrices(List<Integer> ids) throws Exception {
		return newestPriceMapper.getNewestPrices(ids);
	}

}
