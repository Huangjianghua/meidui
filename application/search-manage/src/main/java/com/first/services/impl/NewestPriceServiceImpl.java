package com.first.services.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.IDao.NewestPriceMapper;
import com.first.pojo.NewestPrice;
import com.first.services.NewestPriceService;

@Service
public class NewestPriceServiceImpl implements NewestPriceService {

	@Resource
	private NewestPriceMapper newestPriceMapper;
	
	@Override
	public LinkedList<NewestPrice> getNewestPrices(List<Integer> ids) throws Exception {
		return newestPriceMapper.getNewestPrices(ids);
	}

}
