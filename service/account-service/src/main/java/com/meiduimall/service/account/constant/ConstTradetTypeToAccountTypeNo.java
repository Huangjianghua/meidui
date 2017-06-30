package com.meiduimall.service.account.constant;

/**
 * 交易类型转换成账户类型
 * @author chencong
 *
 */
public enum ConstTradetTypeToAccountTypeNo {
	
	TRADE_TYPE_KFCZ1("KFCZ1", "KFCZ1"),
	TRADE_TYPE_KFCZ2("KFCZ2", "KFCZ2"),
	TRADE_TYPE_CWTZ1("CWTZ1", "CWTZ1"),
	TRADE_TYPE_CWTZ2("CWTZ2", "CWTZ2"),
	TRADE_TYPE_CWTZ3("CWTZ3", "CWTZ3"),
	TRADE_TYPE_SJJL("SJJL", "SJJL"),
	TRADE_TYPE_SJYE("SJYE", "SJYE"),
	TRADE_TYPE_GDYE("GDYE", "GDYE"),
	TRADE_TYPE_QDYE("QDYE", "QDYE"),
	TRADE_TYPE_FJJL("FJJL", "FJJL"),
	TRADE_TYPE_SCJL("SCJL", "SCJL"),
	TRADE_TYPE_YEDR("YEDR", "YEDR");

	private String code;
	private String name;

	private ConstTradetTypeToAccountTypeNo(String code, String name) {
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
		ConstTradetTypeToAccountTypeNo[] values = ConstTradetTypeToAccountTypeNo.values();
		for(ConstTradetTypeToAccountTypeNo val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}

}
