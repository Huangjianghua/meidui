package com.meiduimall.application.mall.catalog.constant;

import com.meiduimall.core.BaseApiCode;

public class ApplicationMallApiCode extends BaseApiCode {

	/** 操作成功 */
	public static final Integer OPERAT_SUCCESS = 4000;
	/** 操作失败 */
	public static final Integer OPERAT_FAIL = 4001;
	/** 请求参数错误 */
	public static final Integer REQUEST_PARAMS_ERROR = 4002;
	/** 暂无数据 */
	public static final Integer NONE_DATA = 4003;
	/** 请先登录 */
	public static final Integer NO_LOGIN = 4004;
	/** 没有这个店铺 */
	public static final Integer NO_THIS_SHOP = 4005;
	/** 该店铺已被收藏，再次收藏失败 */
	public static final Integer ALREADY_COLLECT = 4006;
	/** 收藏成功 */
	public static final Integer COLLECT_SUCCESS = 4007;
	/** 收藏失败 */
	public static final Integer COLLECT_FAIL = 4008;
	/** 取消收藏成功 */
	public static final Integer CANCEL_COLLECT_SUCCESS = 4009;
	/** 取消收藏失败 */
	public static final Integer CANCEL_COLLECT_FAIL = 4010;
	/** token验证异常 */
	public static final Integer TOKEN_VALIDATE_ERROR = 4011;
	/** 请求微服务出错 */
	public static final Integer REQUEST_SERVICE_ERROR = 4012;
	
	static {
		zhMsgMap.put(OPERAT_SUCCESS, "操作成功");
		zhMsgMap.put(OPERAT_FAIL, "操作失败");
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "请求参数错误");
		zhMsgMap.put(NONE_DATA, "暂无数据");
		zhMsgMap.put(NO_LOGIN, "请先登录");
		zhMsgMap.put(NO_THIS_SHOP, "没有这个店铺");
		zhMsgMap.put(ALREADY_COLLECT, "该店铺已被收藏，再次收藏失败");
		zhMsgMap.put(COLLECT_SUCCESS, "收藏成功");
		zhMsgMap.put(COLLECT_FAIL, "收藏失败");
		zhMsgMap.put(CANCEL_COLLECT_SUCCESS, "取消收藏成功");
		zhMsgMap.put(CANCEL_COLLECT_FAIL, "取消收藏失败");
		zhMsgMap.put(TOKEN_VALIDATE_ERROR, "token验证异常");
		zhMsgMap.put(REQUEST_SERVICE_ERROR, "请求微服务出错");
	}
}
