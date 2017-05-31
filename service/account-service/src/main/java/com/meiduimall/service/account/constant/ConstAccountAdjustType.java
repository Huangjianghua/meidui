package com.meiduimall.service.account.constant;

/**
 * 
 * @author:   jianhua.huang 
 * @version:  2017年5月5日 下午5:36:34 0.1 
 * Description:调整类型枚举
 */
public enum ConstAccountAdjustType {
	
	ADD("1", "调增"),
	CUTDOWN("2", "调减");
	
	private String code;
	private String name;
	
	private ConstAccountAdjustType(String code, String name) {
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
		ConstAccountAdjustType[] values = ConstAccountAdjustType.values();
		for(ConstAccountAdjustType val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}
