package com.meiduimall.constant;

/**
 * API返回状态编码和编码语义
 * @author chencong
 *
 */
public abstract class ApiStatusConst {
	
	//成功
	/*public final static String SUCCESS = "0";*/
	//临时代码，适配老的APP
	public final static String SUCCESS = "1";
	
	public final static String SUCCESS_C = "Success";
	//服务器错误
	public final static String SERVER_ERROR="9999";
	public final static String SERVER_ERROR_C="服务器错误";
	//非JSON格式请求
	public final static String NOT_JSON_FORMAT_REQUEST="1111";
	public final static String NOT_JSON_FORMAT_REQUEST_C="非JSON格式请求";
	//用户名输入错误
	public final static String USERNAME_ERROR="1001";
	public final static String USERNAME_ERROR_C="用户名输入错误";
	//用户令牌错误
	public final static String TOKEN_ERROR="1008";
	public final static String TOKEN_ERROR_C="用户令牌错误";
	//密码输入错误
	public final static String PASSWORD_ERROR="1009";
	public final static String PASSWORD_ERROR_C="密码输入错误";
	//该手机号已经被注册
	public final static String PHONE_ALREADY_REGISTED="1028";
	public final static String PHONE_ALREADY_REGISTED_C="该手机号已经被注册";
	//该用户名已经被注册
	public final static String LOGINNAME_ALREADY_REGISTED="2068";
	public final static String LOGINNAME_ALREADY_REGISTED_C="该用户名已经被注册";
	
	//用户名或密码输入错误
	public final static String PASSWORD_OR_USERNAME_ERROR="1010";
	public final static String PASSWORD_OR_USERNAME_ERROR_C="用户或密码输入错误";
	//当前用户不存在
	public final static String USER_NOT_EXIST="1002";
	public final static String USER_NOT_EXIST_C="当前用户不存在";
	//推荐人不能是自己
	public final static String SHARE_MAN_CANNOT_ITSELF="1022";
	public final static String SHARE_MAN_CANNOT_ITSELF_C="推荐人不能是自己";
	//您输入的推荐人不存在
	public final static String SHARE_MAN_NOT_EXIST="1024";
	public final static String SHARE_MAN_NOT_EXIST_C="您输入的推荐人不存在";
	//验证码错误
	public final static String VALIDATE_ERROR="1023";
	public final static String VALIDATE_ERROR_C="验证码错误";
	//用户输入密码错误过多已被锁定，24小时后解除锁定
	public final static String USER_LOCK="1002";
	public final static String USER_LOCK_C="用户输入密码错误过多已被锁定，24小时后解除锁定";
	//当前会员被禁用
	public final static String MEMBER_FORBIDDEN="2056";
	public final static String MEMBER_FORBIDDEN_C="用户输入密码错误过多已被锁定，明天0点0分自动解除";
	//积分不能大于金额
	public final static String POINTS_BIGGERTHAN_MONEY="2038";
	public final static String POINTS_BIGGERTHAN_MONEY_C="消费积分不能大于消费金额";
	//混合支付支付模式
	public final static String MIX_PAYTYPE_ERROR="2064";
	public final static String MIX_PAYTYPE_ERROR_C="混合支付支付模式，美兑积分不能为小于或等于0";
	//支付类型错误
	public final static String PAYTYPE_ERROR="2019";
	public final static String PAYTYPE_ERROR_C="支付类型错误";
	//重复提交的订单
	public final static String REPEAT_ORDER="2021";
	public final static String REPEAT_ORDER_C="重复提交的订单";
	//积分余额不足
	public final static String NOT_ENOUGH_POINTS="2020";
	public final static String NOT_ENOUGH_POINTS_C="积分余额不足";
	//重复提交的冻结或解冻订单
	public final static String REPEAT_FREEZ_ORDER="2025";
	public final static String REPEAT_FREEZ_ORDER_C="重复提交的冻结或解冻订单";
	//订单解冻积分不等于冻结积分
	public final static String DJ_NOT_EQUALS_DJ="2026";
	public final static String DJ_NOT_EQUALS_DJ_C="订单解冻积分不等于冻结积分";
	//没有冻结的积分
	public final static String NO_DJ_POINTS="2027";
	public final static String NO_DJ_POINTS_C="没有冻结的积分";
	//订单状态错误
	public final static String ORDER_STATUS_ERROR="2028";
	public final static String ORDER_STATUS_ERROR_C="订单状态错误";
	// 短信验证码校验失败
	public final static String VAL_SMSMSG_ERROR = "2029";
	public final static String VAL_SMSMSG_ERROR_C = "短信校验码验证失败";
	// 短信验证码校验失败
	public final static String UPDATE_NEWPHONE_ERROR = "2030";
	public final static String UPDATE_NEWPHONE_ERROR_C = "当前号码已被注册";
	// 短信验证码校验失败
	public final static String PHONE_ERROR = "2031";
	public final static String PHONE_ERROR_C = "手机号码有误"; 
}
