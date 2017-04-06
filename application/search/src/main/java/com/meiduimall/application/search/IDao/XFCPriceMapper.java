package com.meiduimall.application.search.IDao;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.pojo.XFCPrice;


@Mapper
public interface XFCPriceMapper {

	public LinkedList<XFCPrice> getXfcPrices(@Param("ids") List<Integer> ids) throws Exception;
}
