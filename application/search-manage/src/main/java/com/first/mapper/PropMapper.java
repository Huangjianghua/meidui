package com.first.mapper;

import java.util.List;

import com.first.pojo.Props;

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
