package com.meiduimall.application.search.IDao;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meiduimall.application.search.pojo.XFCPrice;

public interface XFCPriceMapper {

	public LinkedList<XFCPrice> getXfcPrices(@Param("ids") List<Integer> ids) throws Exception;
}
