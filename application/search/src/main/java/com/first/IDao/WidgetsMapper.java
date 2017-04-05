package com.first.IDao;

import java.util.List;

import com.first.pojo.Widgets;

public interface WidgetsMapper {
	
	/**
	 * 查询挂件内容
	 * @param widgetsType
	 * @return
	 * @throws Exception
	 */
	public List<Widgets> queryWidgets(String widgetsType) throws Exception;

}
