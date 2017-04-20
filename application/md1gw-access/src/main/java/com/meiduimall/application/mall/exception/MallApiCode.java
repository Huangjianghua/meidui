package com.meiduimall.application.mall.exception;


import com.meiduimall.core.BaseApiCode;

public class MallApiCode extends BaseApiCode {

	public static final Integer DECRYPT_ERROR = 10001;
	public static final Integer ENCRYPT_ERROR = 10002;
	public static final Integer UPDATEEPS_FAIL = 10003;
	public static final Integer LISTETPb_EMPTY = 10004;
	public static final Integer PAYMENTBILL_EMPTY = 10005;
	public static final Integer TRADES_EMPTY = 10006;
	public static final Integer UPDATEETPB_FAIL = 10007;
	public static final Integer UPDATESTPT_FAIL = 10008;
	public static final Integer UPDATEWALLETPAY_FAIL = 10009;
	public static final Integer UPDATECSP_FAIL = 10010;
	public static final Integer FREEZEUNFREEZE_FAIL = 10011;
	public static final Integer UDPATESPT_FAIL = 10012;
	public static final Integer TRADELIST_EMPTY = 10013;
	public static final Integer TRADEPAYFINISH_ERROR = 10014;
	public static final Integer MEMIDBYUSERID_FAIL = 10015;
	public static final Integer SERIALIZABLE_FAIL = 10016;
	public static final Integer UNFREEZEDEDUCT_FAIL = 10017;
	public static final Integer UPDATEERRORNUM_FAIL = 10018;
	public static final Integer WALLETPAY_FAIL = 10019;
	public static final Integer EPSucc_FAIL = 10020;
	public static final Integer UPDATEERRORMONEY_FAIL = 10021;
	public static final Integer PAYMENT_ERROR = 10022;
	public static final Integer GETMEMBER_ERROR = 10023;
	public static final Integer VALIDEPAYPWD_ERROR = 10024;
	public static final Integer TOKENTOMEMID_ERROR = 10025;
	public static final Integer FREEZEUNFREEZE_ERROR = 10026;
	public static final Integer GETMEMIDBYUSERID_ERROR = 10027;
	public static final Integer UNFREEZEDEDUCT_ERROR = 10028;
	public static final Integer SENDSMSMESSAGE_ERROR = 10029;
	public static final Integer DATE_ERROR = 10030;
	public static final Integer NOTNULL_EMPTY = 10031;
	public static final Integer XML_ERROR = 10032;
	public static final Integer SAVEORDER_FAIL = 10033;
	public static final Integer SIGN_ERROR = 10034;
	public static final Integer MD5_ERROR = 10035;
	public static final Integer MD5ENCRYPT_EXCEPTION = 10036;
	public static final Integer SERIALIZABLE_EXCEPTION = 10037;
	public static final Integer DESERIALIZABLE_EXCEPTION = 10038;
	
	
	
	static {
		zhMsgMap.put(DECRYPT_ERROR, "解密错误!");
		zhMsgMap.put(ENCRYPT_ERROR, "加密错误!");
		zhMsgMap.put(UPDATEEPS_FAIL, "更新支付单信息失败!");
		zhMsgMap.put(LISTETPb_EMPTY, "子订单获取所有商家订单为空!");
		zhMsgMap.put(PAYMENTBILL_EMPTY, "获取支付单信息为空!");
		zhMsgMap.put(TRADES_EMPTY, "通过tid获取订单列表为空!");
		zhMsgMap.put(UPDATEETPB_FAIL, "更新子支付失败!");
		zhMsgMap.put(UPDATESTPT_FAIL, "更新平台订单表第三方支付的款和状态失败!");
		zhMsgMap.put(UPDATEWALLETPAY_FAIL, "结余额更新到平台表失败!");
		zhMsgMap.put(UPDATECSP_FAIL, "把冻结的金额到平台订单表失败!");
		zhMsgMap.put(FREEZEUNFREEZE_FAIL, "冻结积分,余额到会员系统失败!");
		zhMsgMap.put(UDPATESPT_FAIL, "更新平台订单表失败!");
		zhMsgMap.put(TRADELIST_EMPTY, "获取支付单号对应的所有商家订单信息为空!");
		zhMsgMap.put(TRADEPAYFINISH_ERROR, "订单支付状态更改错误!");
		zhMsgMap.put(MEMIDBYUSERID_FAIL, "获取memId失败!");
		zhMsgMap.put(SERIALIZABLE_FAIL, "序列化失败!");
		zhMsgMap.put(UNFREEZEDEDUCT_FAIL, "回滚更新用户余额失败!");
		zhMsgMap.put(UPDATEERRORNUM_FAIL, "推送次数加1 失败!");
		zhMsgMap.put(WALLETPAY_FAIL, "写入钱包支付记录失败!");
		zhMsgMap.put(EPSucc_FAIL, "记录支付成功记录失败!");
		zhMsgMap.put(UPDATEERRORMONEY_FAIL, "更新支付单错误金额失败!");
		zhMsgMap.put(PAYMENT_ERROR, "第三方支付系统错误!");
		zhMsgMap.put(GETMEMBER_ERROR, "获取会员信息错误!");
		zhMsgMap.put(VALIDEPAYPWD_ERROR, "验证密码错误!");
		zhMsgMap.put(TOKENTOMEMID_ERROR, "tokenTOmemId错误!");
		zhMsgMap.put(FREEZEUNFREEZE_ERROR, "冻结错误!");
		zhMsgMap.put(GETMEMIDBYUSERID_ERROR, "getMemIdByUserId错误!");
		zhMsgMap.put(UNFREEZEDEDUCT_ERROR, "解冻错误!");
		zhMsgMap.put(SENDSMSMESSAGE_ERROR, "sendSmsMessage错误!");
		zhMsgMap.put(DATE_ERROR, "日期转换错误!");
		zhMsgMap.put(NOTNULL_EMPTY, "参数字符串不能为null!");
		zhMsgMap.put(XML_ERROR, "设置xml扫描路径错误!");
		zhMsgMap.put(SAVEORDER_FAIL, "同步订单到会员系统失败!");
		zhMsgMap.put(SIGN_ERROR, "产生签名的字符串错误!");
		zhMsgMap.put(MD5_ERROR, "MD5算法加密错误!");
		zhMsgMap.put(MD5ENCRYPT_EXCEPTION, "MD5Encrypt异常!");
		zhMsgMap.put(SERIALIZABLE_EXCEPTION, "序列化异常!");
		zhMsgMap.put(DESERIALIZABLE_EXCEPTION, "反序列化异常!");
	}
}
