package com.meiduimall.service.account.constant;


/**
 * @author:   jianhua.huang 
 * @version:  2017年5月2日 上午10:47:23 0.1 
 * Description:提现状态枚举
 */
public enum MSBankWithDrawDepositStatusEnum {
	/**0待客服审核  **/
	WCR("0", "待客服审核"),
	/**1待财务审核**/
	WFR("1", "待财务审核"),
	/**2待结算**/
	WSA("2", "待结算"),
	/**3提现成功**/
	WS("3", "提现成功"),
	/**4审核不通过/提现失败**/
	WR("4", "已驳回");

	private String code;
	
	private String name;

	private MSBankWithDrawDepositStatusEnum(String code, String name) {
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
		MSBankWithDrawDepositStatusEnum[] values = MSBankWithDrawDepositStatusEnum.values();
		for(MSBankWithDrawDepositStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}
