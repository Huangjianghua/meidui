package com.meiduimall.service.account.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数名称以及其他特殊字符常量
 * @author chencong
 *
 */
public class SysParamsConst {
	
	public final static String RESULT= "data";
	public final static String STATUS_CODE = "status";
	public final static String RESULT_MSG = "msg";
	
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
	
	/** 附近消费奖励 */
	public static final String MONEY_TRADE_TYPE_FJJL = "FJJL";
	/** 商城购物奖励 */
	public static final String MONEY_TRADE_TYPE_SCJL = "SCJL";
	/** 酒店消费奖励 */
	public static final String MONEY_TRADE_TYPE_JDJL = "JDJL";
	/** 提现 */
	public static final String MONEY_TRADE_TYPE_YETX = "YETX";
	/** 提现手续费 */
	public static final String MONEY_TRADE_TYPE_TXSX = "TXSX";
	/** 充值 */
	public static final String MONEY_TRADE_TYPE_YECZ = "YECZ";
	/** 商家-充值到余额 */
	public static final String MONEY_TRADE_TYPE_SCYE= "SCYE";
	/** 个代-充值到余额 */
	public static final String MONEY_TRADE_TYPE_GCYE = "GCYE";
	/** 区代-充值到余额 */
	public static final String MONEY_TRADE_TYPE_QGYE = "QGYE";
	/** 商城余额导入会员系统 */
	public static final String MONEY_TRADE_TYPE_YEDR = "YEDR";
	/** 余额消费 */
	public static final String MONEY_TRADE_TYPE_YEXF= "YEXF";
	/** 退款-售后退款 */
	public static final String MONEY_TRADE_TYPE_TKSH = "TKSH";
	/** 退款-取消订单 */
	public static final String MONEY_TRADE_TYPE_TKQX = "TKQX";
	
	/** 账户类型-积分账户：AT01 */
	public static final String ACCOUNT_TYPE_POINTS = "AT01";
	/** 账户类型-现金账户：AT02 */
	public static final String ACCOUNT_TYPE_MONEY = "AT02";
	
	/** 积分操作类型 */
	public static Map<String, String> scoreOpType = new HashMap<String, String>();
	static {
		scoreOpType.put("005CBCBA-B541-11E6-A063-FCAA149389FF", "新注册赠送");
		scoreOpType.put("05E7AF9E-B541-11E6-A063-FCAA149389FF", "邀请注册赠送");
		scoreOpType.put("0A3F1BFA-B541-11E6-A063-FCAA149389FF", "现金充值");
		scoreOpType.put("0F1264FB-B541-11E6-A063-FCAA149389FF", "积分赠送");
		scoreOpType.put("662F6748-B602-11E6-A063-FCAA149389FF", "积分转入");
		scoreOpType.put("BCCB4476-B540-11E6-A063-FCAA149389FF", "其它");
		scoreOpType.put("C48195CB-B601-11E6-A063-FCAA149389FF", "积分转出");
		scoreOpType.put("D90F5998-9CD6-11E6-AB9D-FCAA149389FF", "调增");
		scoreOpType.put("D90F5C23-9CD6-11E6-AB9D-FCAA149389FF", "调减");
		scoreOpType.put("D90F5D10-9CD6-11E6-AB9D-FCAA149389FF", "订单消费");
		scoreOpType.put("D90F5DEA-9CD6-11E6-AB9D-FCAA149389FF", "订单退款");
		scoreOpType.put("E6BB5917-B540-11E6-A063-FCAA149389FF", "充值");
		scoreOpType.put("ED41FA17-B540-11E6-A063-FCAA149389FF", "财务操作");
		scoreOpType.put("F1BD17EC-B540-11E6-A063-FCAA149389FF", "订单取消");
		scoreOpType.put("F6BDCD07-B540-11E6-A063-FCAA149389FF", "附近消费");
		scoreOpType.put("FB739AD7-B540-11E6-A063-FCAA149389FF", "全民推广");
	}

}
