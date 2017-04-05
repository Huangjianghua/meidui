package com.first.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.IDao.XFCPriceMapper;
import com.first.pojo.XFCPrice;
import com.first.services.XFCPriceService;

@Service
public class XFCPriceServiceImpl implements XFCPriceService {

	@Autowired
	private XFCPriceMapper xfcPriceMapper;
	
	@Override
	public LinkedList<XFCPrice> getXfcPrices(List<Integer> ids) throws Exception {
		return xfcPriceMapper.getXfcPrices(ids);
	}

}
