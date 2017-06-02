package com.meiduimall.service.member.constant;

import com.meiduimall.core.BaseApiCode;

/**
 * API返回状态编码和编码语义
 * @author chencong
 *
 */
public abstract class ConstApiStatus extends BaseApiCode {
	
    /**系统内部错误*/
	//请求参数为空
	public final static Integer REQUIRED_PARAM_EMPTY= 1002;
	//MD5错误
	public final static Integer MD5_EXCEPTION= 1003;
	//解密异常
	public final static Integer DECRYPTION_EXCEPTION= 1004;
	//加密异常
	public final static Integer ENCRYPTION_EXCEPTION= 1005;
	//HTTP请求异常
	public final static Integer HTTP_EXCEPTION= 1006;
	//解析数据异常
	public final static Integer PARSE_DATE_EXCEPTION= 1007;
	//数据库查询异常
	public final static Integer DB_SELECT_EXCEPTION= 1008;
	//数据库更新异常
	public final static Integer DB_UPDATE_EXCEPTION= 1009;
	//数据库删除异常
	public final static Integer DB_DELETE_EXCEPTION= 1010;
	//数据库删除异常
	public final static Integer DB_INSERT_EXCEPTION= 1011;
	//系统错误
	public final static Integer SYSTEM_ERROR= 1012;
	//json解析异常
	public final static Integer JSON_PARSE_EXCEPTION= 1013;
	
	/**会员账号校验相关*/
	//用户名错误
	public final static Integer USERNAME_ERROR=8001;
	//当前会员不存在
	public final static Integer MEMBER_NOT_EXIST=8002;
	//登录密码错误
	public final static Integer PASSWORD_ERROR=8003;
	//该手机号已经被注册
	public final static Integer PHONE_ALREADY_REGISTED=8006;
	//该登录名已被注册
	public final static Integer LOGINNAME_ALREADY_REGISTED=8007;
	//分享人不能是自己
	public final static Integer SHARE_MAN_CANNOT_IS_ITSELF=8008;
	//分享人不存在
	public final static Integer SHARE_MAN_NOT_EXIST=8009;
	//账号异常
	public final static Integer ACCOUNT_EXCEPTION=8010;
	//会员账号ID不能为空
	public final static Integer ACCOUNT_MEMBER_ID_NULL=8011;
	//该账号不存在
	public final static Integer USERID_IS_NOT_EXIST=8012;
	//该账号已存在
	public final static Integer USERID_IS_EXIST=8013;
	
	/**会员账号权限相关*/
	//token不存在
	public final static Integer TOKEN_NOT_EXISTS=8201;
	//token校验不通过
	public final static Integer CHECK_TOKEN_NOT_PASS=8202;
	//会员被禁用
	public final static Integer MEMBER_FORBIDDEN=8203;
	//会员已被锁定
	public final static Integer MEMBER_LOCK=8204;
	//解禁账号异常
	public final static Integer UNDISABLED_ACCOUNT_EXCEPTION=8205;
	//禁用账号异常
	public final static Integer DISABLED_ACCOUNT_EXCEPTION=8206;
	//获取用户登陆解锁列表异常
	public final static Integer LOGIN_UNLOCK_LIST_EXCEPTION=8207;
	//解锁异常
	public final static Integer LOGIN_UNLOCK_EXCEPTION=8208;
	//重置账号密码异常
	public final static Integer RESET_ACCOUNT_PWD_EXCEPTION=8209;
	
	/**API请求异常相关*/
	//获取会员基本信息异常
	public final static Integer GET_USERINFO_EXCEPTION=8301;
	//设置支付密码开关状态异常
	public final static Integer SET_PAYPWD_STATUS_EXCEPTION=8302;
	//登录异常
	public final static Integer LOGIN_EXCEPTION=8304;
	//注册异常
	public final static Integer REGISTER_EXCEPTION=8305;
	//退出异常
	public final static Integer EXIT_ERROR=8306;

	
	/**短信相关*/
	//校验短信验证码不通过
	public final static Integer CHECK_VALIDATE_CODE_NOT_PASS=8801;
	//发送短信失败
	public final static Integer SEND_SMS_FAILED=8802;
	//获取短信验证码异常
	public final static Integer GET_VALIDATE_CODE_EXCEPTION=8803;
	
	/**第三方SDK（例如微信）相关*/
	//微信openID绑定失败
	public static final Integer WEIXIN_OPENID_BINGDING_FAIL = 8821;
	//未绑定微信openID
	public static final Integer NOT_BINGDING_WEIXIN_OPENID = 8822;

	/**其他*/
	//查询不在会员手机归属地表异常
	public static final Integer FIND_MEMBER_EXCEPTION= 8841;
	//查询手机前7位确定归属地异常
	public static final Integer QUERY_MOBILE_EXCEPTION= 8842;
	//批量插入会员手机归属地表异常
	public static final Integer INSERT_SELECTIVE_EXCEPTION= 8843;

	static {
		zhMsgMap.put(REQUIRED_PARAM_EMPTY, "必填参数为空");
		zhMsgMap.put(MD5_EXCEPTION, "生成MD5程序异常");
		zhMsgMap.put(DECRYPTION_EXCEPTION, "解密程序异常");
		zhMsgMap.put(ENCRYPTION_EXCEPTION, "加密程序异常");
		zhMsgMap.put(HTTP_EXCEPTION, "HTTP请求异常");
		zhMsgMap.put(PARSE_DATE_EXCEPTION, "日期解析异常");
		zhMsgMap.put(DB_SELECT_EXCEPTION, "数据库查询失败");
		zhMsgMap.put(DB_UPDATE_EXCEPTION, "数据库更新失败");
		zhMsgMap.put(DB_DELETE_EXCEPTION, "数据库删除失败");
		zhMsgMap.put(DB_INSERT_EXCEPTION, "数据库插入失败");
		zhMsgMap.put(SYSTEM_ERROR, "系统错误，请联系客服");

		zhMsgMap.put(USERNAME_ERROR, "用户名输入错误");
		zhMsgMap.put(MEMBER_NOT_EXIST, "该用户不存在，请先注册");
		zhMsgMap.put(PASSWORD_ERROR, "密码输入错误");
		zhMsgMap.put(TOKEN_NOT_EXISTS, "token不存在");
		zhMsgMap.put(EXIT_ERROR, "退出登录异常，请联系客服");
		zhMsgMap.put(PHONE_ALREADY_REGISTED, "该手机号已经被注册");
		zhMsgMap.put(LOGINNAME_ALREADY_REGISTED, "该用户名已经被注册");
		zhMsgMap.put(SHARE_MAN_CANNOT_IS_ITSELF, "推荐人不能是自己");
		zhMsgMap.put(SHARE_MAN_NOT_EXIST, "您的推荐人不存在");
		zhMsgMap.put(MEMBER_LOCK, "密码输入错误次数超过5次已被锁定，明天0点0分自动解除");
		zhMsgMap.put(MEMBER_FORBIDDEN, "会员账号已被禁用，请联系客服");

		zhMsgMap.put(GET_USERINFO_EXCEPTION, "获取用户信息程序异常");
		zhMsgMap.put(SET_PAYPWD_STATUS_EXCEPTION, "设置支付密码开关程序异常");
		zhMsgMap.put(LOGIN_EXCEPTION, "登录异常，请联系客服");
		zhMsgMap.put(GET_VALIDATE_CODE_EXCEPTION, "获取短信验证码异常，请联系客服");
		zhMsgMap.put(REGISTER_EXCEPTION, "注册异常，请联系客服");
		zhMsgMap.put(CHECK_TOKEN_NOT_PASS, "登录已过期，请重新登录");
		zhMsgMap.put(ACCOUNT_EXCEPTION, "账号异常，请联系客服");
		zhMsgMap.put(CHECK_VALIDATE_CODE_NOT_PASS, "短信验证码校验不通过");
		zhMsgMap.put(SEND_SMS_FAILED, "短信发送失败");

		zhMsgMap.put(LOGIN_UNLOCK_LIST_EXCEPTION, "获取用户登陆解锁列表异常");
		zhMsgMap.put(LOGIN_UNLOCK_EXCEPTION, "解锁异常");
		zhMsgMap.put(RESET_ACCOUNT_PWD_EXCEPTION, "重置账号密码异常");
		zhMsgMap.put(UNDISABLED_ACCOUNT_EXCEPTION, "解禁账号异常");
		zhMsgMap.put(DISABLED_ACCOUNT_EXCEPTION, "禁用账号异常");
		zhMsgMap.put(ACCOUNT_MEMBER_ID_NULL, "会员账号ID不能为空");
		zhMsgMap.put(WEIXIN_OPENID_BINGDING_FAIL, "微信openID绑定失败");
		zhMsgMap.put(NOT_BINGDING_WEIXIN_OPENID, "未绑定微信openID");
		zhMsgMap.put(USERID_IS_NOT_EXIST, "该账号不存在");
		zhMsgMap.put(USERID_IS_EXIST, "该账号已存在");
		
		zhMsgMap.put(NOT_BINGDING_WEIXIN_OPENID, "未绑定微信openID");
		zhMsgMap.put(FIND_MEMBER_EXCEPTION, "查询不在会员手机归属地表异常");
		zhMsgMap.put(QUERY_MOBILE_EXCEPTION, "查询手机前7位确定归属地异常");
		zhMsgMap.put(INSERT_SELECTIVE_EXCEPTION, "批量插入会员手机归属地表异常");
	}

}
