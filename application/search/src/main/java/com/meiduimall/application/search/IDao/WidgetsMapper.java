package com.meiduimall.application.search.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.pojo.Widgets;


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
