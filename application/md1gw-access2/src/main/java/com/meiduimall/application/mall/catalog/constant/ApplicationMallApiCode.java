package com.meiduimall.application.mall.catalog.constant;

import com.meiduimall.core.BaseApiCode;

public class ApplicationMallApiCode extends BaseApiCode {

	/** 请求微服务出错 */
	public static final Integer REQUEST_SERVICE_ERROR = 7500;
	/** 请求参数错误 */
	public static final Integer REQUEST_PARAMS_ERROR = 7501;
	/** 请先登录 */
	public static final Integer NO_LOGIN = 7502;
	/** token验证异常 */
	public static final Integer TOKEN_VALIDATE_ERROR = 7503;

	static {
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "请求参数错误");
		zhMsgMap.put(NO_LOGIN, "请先登录");
		zhMsgMap.put(REQUEST_SERVICE_ERROR, "请求微服务出错");
		zhMsgMap.put(TOKEN_VALIDATE_ERROR, "token验证异常");
	}
}
