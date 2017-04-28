package com.meiduimall.service.account.constant;


/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: MemSignSourceEnum.java
 * @Author:   jianhua.huang 
 * @Date:     2017年4月19日 上午9:32:05
 * @Description:调整类型枚举
 */
public enum AccountReviseStatusEnum {
	/**待审核  **/
	WAIT_REVIEW("WR", "待审核"),
	/**已审核  **/
	AUDITED_REVIEW("AR", "已审核"),
	/**已拒绝		*/
	REJECTED_REVIEW("RR", "已拒绝");

	private String code;
	
	private String name;

	private AccountReviseStatusEnum(String code, String name) {
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
		AccountReviseStatusEnum[] values = AccountReviseStatusEnum.values();
		for(AccountReviseStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}
