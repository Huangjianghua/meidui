package com.meiduimall.application.search.manage.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.pojo.Props;


@Repository
public interface PropMapper {
	
    /**
     * 查询属性名信息
     * @return
     */
	public List<Props> queryProps() throws Exception;
	
	/**
	 * 根据ID查询属性名信息
	 * @return
	 */
	public Props queryPropById(Integer id) throws Exception;

}
