package com.meiduimall.application.search.manage;
import com.meiduimall.core.BaseApiCode;

public class SearchManageApiCode extends BaseApiCode{

	public static final Integer EXCEPTION_LOGIN=7001;
	static {
		zhMsgMap.put(EXCEPTION_LOGIN, "登录异常！");
	}
}
