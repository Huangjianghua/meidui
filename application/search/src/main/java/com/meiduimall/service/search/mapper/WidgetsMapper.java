package com.meiduimall.service.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meiduimall.service.search.entity.Widgets;


@Mapper
public interface WidgetsMapper {
	
	/**
	 * 查询挂件内容
	 * @param widgetsType
	 * @return
	 * @throws Exception
	 */
	public List<Widgets> queryWidgets(String widgetsType) throws Exception;
}
