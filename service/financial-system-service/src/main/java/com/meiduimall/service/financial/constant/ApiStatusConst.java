package com.meiduimall.service.financial.constant;

/**
 * API返回状态编码和编码语义
 * 
 * @author chencong
 *
 */
public abstract class ApiStatusConst {

	// 返回状态，1表示成功
	public final static String SUCCESS = "0";
	public final static String SUCCESS_C = "Success";

	// 暂无数据
	public final static String NONE = "9001";
	public final static String NONE_C = "暂无数据";

	// 缺少请求参数
	public final static String MISS_PARAMS = "9002";
	public final static String MISS_PARAMS_C = "请求参数错误";

	// 服务器错误
	public final static String SERVER_ERROR = "9999";
	public final static String SERVER_ERROR_C = "服务器错误";

}
