package com.meiduimall.core;

import java.util.Map;

public class SearchApiCode {
	
	public static final Integer EXCEPTION_DATA_QUERY=-1;
	
	public static void initResponseCode(){
		Map<Integer,String> zhMsgMap = BaseApiCode.zhMsgMap;
		zhMsgMap.put(EXCEPTION_DATA_QUERY,"数据查询异常");
	}
	

}
