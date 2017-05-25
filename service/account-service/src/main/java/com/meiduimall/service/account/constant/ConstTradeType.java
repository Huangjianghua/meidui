package com.meiduimall.service.account.constant;

/**
 * 交易类型定义
 * @author chencong
 *
 */
public enum ConstTradeType {
	
	TRADE_TYPE_KFCZ1("KFCZ1", "凯富充值1"),
	TRADE_TYPE_KFCZ2("KFCZ2", "凯富充值2"),
	TRADE_TYPE_CWTZ1("CWTZ1", "财务调整（需手续费）"),
	TRADE_TYPE_CWTZ2("CWTZ2", "财务调整（免手续费）"),
	TRADE_TYPE_CWTZ3("CWTZ3", "财务调整（不可提现）"),
	TRADE_TYPE_SJJL("SJJL", "商家订单奖励"),
	/*TRADE_TYPE_SCYE("SCYE", "商家充值"),*/
	TRADE_TYPE_SJYE("SJYE", "商家充值"),
	/*TRADE_TYPE_GCYE("GCYE", "个代充值"),*/
	TRADE_TYPE_GDYE("GDYE", "个代充值"),
	/*TRADE_TYPE_QGYE("QGYE", "区代充值"),*/
	TRADE_TYPE_QDYE("QDYE", "区代充值"),
	TRADE_TYPE_FJJL("FJJL", "附近消费奖"),
	TRADE_TYPE_SCJL("SCJL", "商城购物奖励"),
	TRADE_TYPE_YEDR("YEDR", "商城余额导入会员系统 "),
	
	TRADE_TYPE_YETX("YETX", "提现"),
	TRADE_TYPE_TXSX("TXSX", "提现手续费 "),
	
	TRADE_TYPE_JDJL("JDJL", "酒店消费奖励 "),
	
	TRADE_TYPE_YECZ("YECZ", "余额充值"),
	TRADE_TYPE_YEXF("YEXF", "余额消费"),
	TRADE_TYPE_TKSH("TKSH", "退款-售后退款 "),
	TRADE_TYPE_TKQX("TKQX", "退款-取消订单 ");

	private String code;
	private String name;

	private ConstTradeType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(String code){
		ConstTradeType[] values = ConstTradeType.values();
		for(ConstTradeType val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}

}
