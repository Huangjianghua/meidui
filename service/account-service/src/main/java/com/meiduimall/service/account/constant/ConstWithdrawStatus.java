package com.meiduimall.service.account.constant;


/**
 * @author:   jianhua.huang 
 * @version:  2017年5月2日 上午10:47:23 0.1 
 * Description:提现状态
 */
public enum ConstWithdrawStatus {
	
	WAIT_CS_CHECK("0", "待客服审核"),
	WAIT_ACCOUNTANT_CHECK("1", "待财务审核"),
	WAIT_CALCULATE("2", "待结算"),
	WITHDRAW_SUCCESS("3", "提现成功"),
	ALREADY_REJECT("4", "已驳回");
	
	private String code;
	private String name;
	
	private ConstWithdrawStatus(String code, String name) {
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
		ConstWithdrawStatus[] values = ConstWithdrawStatus.values();
		for(ConstWithdrawStatus val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}
