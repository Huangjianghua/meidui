package com.meiduimall.service.member.constant;

/**
 * 系统参数名称以及其他特殊字符常量
 * @author chencong
 *
 */
public class SysParamsConst {
	
	/**最大失败登录次数*/
	public final static int MAX_LOGIN_FAIL_COUNT=5;
	
	public final static String DATA= "data";
	public final static String STATUS= "status";
	public final static String MSG= "msg";
	public final static String TOKEN= "token";
	public final static String CONNECTION= "&";
	
	/** 账户类型-积分账户：AT01 */
	public static final String ACCOUNT_TYPE_POINTS = "AT01";
	/** 账户类型-现金账户：AT02 */
	public static final String ACCOUNT_TYPE_MONEY = "AT02";
	
	/**美兑密码字符串切割字符串 */
	public static final String MD_PASSWORD_SPLIT_STR = "-";
	
	/**会员系统用户动作*/
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
	
	
	/***redis中key场景前缀定义*/
	public static final String REDIS_LOGIN_LOCK= "loginLock";//登录失败锁定
	public static final String REDIS_CREATE_TOKEN= "createToken";//创建登录令牌
	
	
	
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
	
	
	/** 默认分享人名称 */
	public static final String MD1GW_DEFAULT_SHARE_LOGIN_NAME = "meidui";
	
	/** 新用户注册增加积分 */
	public static final String MD1GW_REGISTER_ADD_POINTS = "100";
	
	/**会员登录失败次数初始化 */
	public static final String REDIS_INIT_LOGIN_LOCK_COUNT = "1";
	
	/**加密解密的key*/
	public static final String DESC_KEY="DESC_KEY";
	/**加密方式*/
	public static final String DES="DES";
	public static final String MD5="MD5";
	/**编码方式*/
	public static final String GBK="GBK";
	
	/**请求头终端ID*/
	public static final String TERMINAL_ID="Terminal-ID";
	/**User-Agent*/
	public static final String USER_AGENT="User-Agent";
	
	/**默认登录名前缀*/
	public static final String DEFAULT_LOGIN_NAME_PREFIX="1gw_";
	
	/**订单号前缀*/
	public static final String DEFAULT_ORDERID_PREFIX="1GW+";
	
	/**短信服务给会员分配的sysKey*/
	public static final String SMS_SYSKEY="member_service";
	
	/**+*/
	public static final String ADD_SYMBOL="+";
	/**初始化解锁0 */
	public static final String INIT_LOGIN_LOCK_COUNT = "0";
	
	public final static String MEM_ID= "memId";

}
