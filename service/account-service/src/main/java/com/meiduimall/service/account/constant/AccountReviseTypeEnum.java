package com.meiduimall.service.account.constant;


/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: MemSignSourceEnum.java
 * @Author:   jianhua.huang 
 * @Date:     2017年4月19日 上午9:32:05
 * @Description:调整类型枚举
 */
public enum AccountReviseTypeEnum {
	/**1表示调增  **/
	UP("1", "调增"),
	/**2表示 调减 **/
	CUT_DOWN("2", "调减");

	private String code;
	
	private String name;

	private AccountReviseTypeEnum(String code, String name) {
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
		AccountReviseTypeEnum[] values = AccountReviseTypeEnum.values();
		for(AccountReviseTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}
