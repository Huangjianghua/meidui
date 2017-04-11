package com.meiduimall.application.search.manage.services;

import java.util.LinkedList;
import java.util.List;

import com.meiduimall.application.search.manage.pojo.NewestPrice;

public interface NewestPriceService {

	public LinkedList<NewestPrice> getNewestPrices(List<Integer> ids) throws Exception;
}
