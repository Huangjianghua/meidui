package com.meiduimall.application.mall.constant;

import java.math.BigDecimal;

/**
 * 系统参数名称以及其他特殊字符常量
 * @author chencong
 *
 */
public class SysParaNameConst {
	
	//状态编码
	/*public final static String STATUS_CODE = "status_code";*/
	 
	
	//状态编码说明
	/*public final static String RESULT_MSG = "result_msg";*/
	
	//数据结果
	/*public final static String RESULT = "result";*/
	
	public final static String DATA= "data";
	public final static String STATUS = "status";
	public final static String MSG = "msg";
	
	/*******会员系统用户动作 start*******/
	public static final String ADD = "添加";
	public static final String MODIFY = "修改";
	public static final String DELETE = "删除";
	public static final String QUERY = "查询";
	public static final String EXPORT = "导出";
	public static final String LOGIN = "登录";
	public static final String LOGIN_REPEAT = "重复登录";
	public static final String REGISERTER = "注册";
	public static final String LOGINOUT = "登出";
	public static final String LOGIN_PC = "PC端";
	public static final String LOGIN_WB = "外部端";
	public static final String QUERY_WB = "外部查询";
	public static final String UPDATE_WB = "外部修改";
	public static final String QUERY_SUB_WB = "外部提交订单";
	public static final String QUERY_TUI_WB = "外部退单";
	public static final String SAVE_MEMBER_WB = "外部修改会员基本信息";
	public static final String SAVE_BANGDINGEMAIL_WB = "外部";
	public static final String PAYMENT = "缴费";
	/*******end*******/
	
	/*******redis中key场景前缀定义start*******/
	public static final String REDISKEY_LoginFail = "loginfail";//登录失败锁定
	public static final String REDISKEY_REGISTER_VALIDATE_CODE = "register";//注册验证码
	public static final String UPDATE_PHONE_VALIDATE_CODE = "updatephone";//注册验证码
	/*******end*******/
	
	/*******旧会员系统SessionManager 里的key*/
	public static final String CUSTOM_MENU = "custon menu";
	public static final String PLATFORM_MENU = "platform menu";
	public static final String DICT_OPTGROUP = "dict optgroup";
	public static final String RANK_DICT = "rank dict";
	public static final String INTEGRAL_DICT = "integral dict";
	public static final String RANK_CONFIG = "rank config";
	public static final String INTEGRAL_CONGIF= "integral config";
	public static final String OPTGROUP_DICT = "optgroup dict";
	public static final String CITY_DICT = "city dict";
	public static final String BUSINESS_CONFIG = "business config";
	public static final String RANK_INTEGRAL = "rank_integral";
	/*******end*******/
	
	
	/** 默认分享人名称 */
	public static final String MD1GW_DEFAULT_SHARE_LOGIN_NAME = "meidui";
	
	/** 新用户注册增加积分 */
	public static final String MD1GW_REGISTER_ADD_POINTS = "100";
	
	/** 更新更新平台订单 **/
	public static final String WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	public static final String SUCC = "succ";
	public static final String progress = "progress";
	
	
	public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	
	/**记录订单操作日志常量***/
	public static final Integer op_id = 0;
	public static final String op_name = "系统";
	public static final String op_role = "system";
	public static final String behavior = "payment";
	public static final String logText = "订单付款成功！";

	
	public static final Byte isSync = 1;
	public static final String paying = "paying";
	public static final Byte isPaying = 1;
	
	/**
	 * 用户输的支付密码拼接userPayKey(商城配置为'{liangping}')
	 */
	public static final String userPayKey = "{liangping}";
	
	/** 支付验证 **/
	public static final String ready = "ready";
	
	public static final BigDecimal error_money = new BigDecimal(3);
	public static final String remark  = "订单消费";
	public static final String appencryptkey  = "%Rs-t&oZ";
	
	
	/**匹配是否是手机号的正则表达式**/
	public final static String PHONE_PATTERN="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0,1,6,7,]))|(18[0-2,5-9]))\\d{8}$";
	
	


}
