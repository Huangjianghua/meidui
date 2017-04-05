package com.first.IDao;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.first.pojo.NewestPrice;

public interface NewestPriceMapper {

	public LinkedList<NewestPrice> getNewestPrices(@Param("ids") List<Integer> ids) throws Exception;
}
