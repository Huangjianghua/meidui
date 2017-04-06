package com.meiduimall.application.search.IDao;

import org.apache.ibatis.annotations.Mapper;

import com.meiduimall.application.search.pojo.MeiduiPoint;


@Mapper
public interface MeiduiPointMapper {

	MeiduiPoint queryMeiduiPoint();
}
