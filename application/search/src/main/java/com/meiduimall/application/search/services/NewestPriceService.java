package com.meiduimall.application.search.services;

import java.util.LinkedList;
import java.util.List;

import com.meiduimall.application.search.pojo.NewestPrice;

public interface NewestPriceService {

	public LinkedList<NewestPrice> getNewestPrices(List<Integer> ids) throws Exception;
}
