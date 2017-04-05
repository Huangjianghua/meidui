package com.first.services;

import java.util.LinkedList;
import java.util.List;

import com.first.pojo.NewestPrice;

public interface NewestPriceService {

	public LinkedList<NewestPrice> getNewestPrices(List<Integer> ids) throws Exception;
}
