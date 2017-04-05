package com.first.services;

import java.util.LinkedList;
import java.util.List;

import com.first.pojo.XFCPrice;

public interface XFCPriceService {

	public LinkedList<XFCPrice> getXfcPrices(List<Integer> ids) throws Exception;
}
