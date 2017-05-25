package com.meiduimall.service.account.constant;

/**
 * 账户调整状态
 * @author huangjianhua
 *
 */
public enum ConstAccountAdjustStatus {
	
	WAIT_CHECK("WR", "待审核"),
	ALREADY_CHECK("AR", "已审核"),
	ALREADY_REJECT("RR", "已拒绝");

	private String code;
	private String name;

	private ConstAccountAdjustStatus(String code, String name) {
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
		ConstAccountAdjustStatus[] values = ConstAccountAdjustStatus.values();
		for(ConstAccountAdjustStatus val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}
