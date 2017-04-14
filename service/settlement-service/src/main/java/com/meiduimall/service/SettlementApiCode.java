package com.meiduimall.service;

import java.util.Map;

import com.meiduimall.core.BaseApiCode;

public class SettlementApiCode extends BaseApiCode {
	
	public static final Integer DEDUCT_DEPOSIT_FAILED = 7000;
	public static final Integer UPD_BALANCE_FAILD = 7001;
	public static final Integer AGENCY_ACCOUNT_NOT_FOUND = 7002;
	public static final Integer CALLBACK_O2O_UPD_BALANCE_FAILD = 7003;
	public static final Integer ALREADY_SHAREPROIFT = 7004;
	public static final Integer ALREADY_EXIST_ACCOUNT = 7005;
	public static final Integer SEND_STORE_SCORE_FAILE = 7006;
	public static final Integer ORDER_SHARE_DATA_EMPTY = 7008;
	public static final Integer BALANCE_NOT_ENOUGH = 7009;
	public static final Integer WATERID_OR_WATERTYPE_ISNULL = 7010;
	public static final Integer LOGIN_TYPE_ISNULL = 7011;
	public static final Integer LOGIN_TYPE_AGENTCODE_ISNULL = 7012;
	
	static {
		Map<Integer, String> zhMsgMap = BaseApiCode.zhMsgMap;
		zhMsgMap.put(DEDUCT_DEPOSIT_FAILED, "区代30%代理费抵扣保证金失败");
		zhMsgMap.put(UPD_BALANCE_FAILD, "更新账户余额失败");
		zhMsgMap.put(AGENCY_ACCOUNT_NOT_FOUND, "代理账户不存在,无法更新账户余额");
		zhMsgMap.put(CALLBACK_O2O_UPD_BALANCE_FAILD, "回调o2o更新余款、抵扣保证金插入缴费记录失败");
		zhMsgMap.put(ALREADY_SHAREPROIFT, "当前个代已分润，不可重复分润");
		zhMsgMap.put(ALREADY_EXIST_ACCOUNT, "当前个代账户已存在，不可重复创建账户");
		zhMsgMap.put(SEND_STORE_SCORE_FAILE, "新商家送积分失败");
		zhMsgMap.put(ORDER_SHARE_DATA_EMPTY, "订单分润数据为空");
		zhMsgMap.put(BALANCE_NOT_ENOUGH, "提现金额不能大于账号可提现金额，请重新输入");
		zhMsgMap.put(WATERID_OR_WATERTYPE_ISNULL, "流水编号或流水类型不能为空");
		zhMsgMap.put(LOGIN_TYPE_ISNULL, "查询账单流水详情，登陆类型不能为空");
		zhMsgMap.put(LOGIN_TYPE_AGENTCODE_ISNULL, "查询账单流水详情，登陆类型为代理，代理编号不能为空");

	}

}
