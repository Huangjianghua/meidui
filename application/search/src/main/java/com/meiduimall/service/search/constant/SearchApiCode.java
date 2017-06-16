package com.meiduimall.service.search.constant;

import com.meiduimall.core.BaseApiCode;

public class SearchApiCode extends BaseApiCode {

	public static final Integer EXCEPTION_DATA_QUERY = -1;

	public static final Integer NO_DATA = 7001;

	static {
		zhMsgMap.put(EXCEPTION_DATA_QUERY, "数据查询异常");
		zhMsgMap.put(NO_DATA, "查询不到数据");
	}

}
