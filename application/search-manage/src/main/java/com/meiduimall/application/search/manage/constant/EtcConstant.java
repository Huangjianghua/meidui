package com.meiduimall.application.search.manage.constant;

/**
 * @desc 其它常量{文件路径、错误码...}
 * @author lftan
 * 
 */
public class EtcConstant {
	
	//===========================PATH===========================================
	/**授权管理::平台端允许登陆的IP白名单配置*/
	public static final String CONFIG_PROPERTY_IP_PATH = "config/ip.properties";
	//========================END PATH===========================================
	
	//===========================KEY=============================================
	public static final String CONFIG_PROPERTY_IP_VAL_KEY = "ip";
	
	//ip校验开关(val[on|off]),大小写不敏感
	public static final String CONFIG_PROPERTY_IP_SWITCH_KEY = "ip.check.switch";
	//开启ip校验
	public static final String CONFIG_PROPERTY_IP_SWITCH_ON = "on";
	//禁用ip校验
	public static final String CONFIG_PROPERTY_IP_SWITCH_OFF = "OFF";
	
	//逗号
	public static final String CONFIG_PROPERTY_SYMBOL_COMMA = ",";
	
	//========================END KEY============================================
	
	//===========================STATUS_CODE======================================
	public static final String STATUS_CODE_8800 = "8800";
	public static final String STATUS_CODE_8801 = "8801";
	public static final String STATUS_CODE_8802 = "8802";
	public static final String STATUS_CODE_1002 = "1002";
	public static final String STATUS_CODE_1003 = "1003";
	//========================END STATUS_CODE=====================================

}
