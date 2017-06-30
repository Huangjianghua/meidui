package com.meiduimall.service.search.mapper;

import java.util.List;

import com.meiduimall.service.search.entity.Props;

public interface PropMapper {

	/**
	 * 查询属性名信息
	 * 
	 * @return
	 */
	public List<Props> queryProps();

	/**
	 * 根据ID查询属性名信息
	 * 
	 * @return
	 */
	public Props queryPropById(Integer id);

}
