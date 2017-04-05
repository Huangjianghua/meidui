package com.meiduimall.application.search.services;

import java.util.LinkedList;
import java.util.List;

import com.meiduimall.application.search.pojo.XFCPrice;

public interface XFCPriceService {

	public LinkedList<XFCPrice> getXfcPrices(List<Integer> ids) throws Exception;
}
