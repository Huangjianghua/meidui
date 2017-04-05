package com.meiduimall.application.search.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.IDao.XFCPriceMapper;
import com.meiduimall.application.search.pojo.XFCPrice;
import com.meiduimall.application.search.services.XFCPriceService;

@Service
public class XFCPriceServiceImpl implements XFCPriceService {

	@Autowired
	private XFCPriceMapper xfcPriceMapper;
	
	@Override
	public LinkedList<XFCPrice> getXfcPrices(List<Integer> ids) throws Exception {
		return xfcPriceMapper.getXfcPrices(ids);
	}

}
