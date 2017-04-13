package com.meiduimall.service;

import java.util.Map;

import com.meiduimall.core.BaseApiCode;

public class SettlementApiCode extends BaseApiCode {
	
	public static final Integer DEDUCT_DEPOSIT_FAILED = -1;
	public static final Integer UPD_BALANCE_FAILD = -2;
	public static final Integer AGENCY_ACCOUNT_NOT_FOUND = -3;
	public static final Integer CALLBACK_O2O_UPD_BALANCE_FAILD = -5;
	public static final Integer ALREADY_SHAREPROIFT = -6;
	public static final Integer ALREADY_EXIST_ACCOUNT = -7;
	public static final Integer SEND_STORE_SCORE_FAILE = -8;
	public static final Integer ORDER_SHARE_DATA_EMPTY = -8;
	
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

	}

}
