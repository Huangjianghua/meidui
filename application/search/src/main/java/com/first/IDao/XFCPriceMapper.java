package com.first.IDao;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.first.pojo.XFCPrice;

public interface XFCPriceMapper {

	public LinkedList<XFCPrice> getXfcPrices(@Param("ids") List<Integer> ids) throws Exception;
}
