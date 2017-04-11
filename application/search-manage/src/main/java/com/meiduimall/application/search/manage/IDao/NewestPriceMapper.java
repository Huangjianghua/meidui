package com.meiduimall.application.search.manage.IDao;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.pojo.NewestPrice;


public interface NewestPriceMapper {

	public LinkedList<NewestPrice> getNewestPrices(@Param("ids") List<Integer> ids) throws Exception;
}
