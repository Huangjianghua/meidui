package com.meiduimall.service.member.constant;

/**
 * 注册来源
 * @author chencong
 *
 */
public enum ConstRegisterSource  {
	APP_REG(1, "原生APP"),
	H5_REG(2, "H5页面"),
	PC_REG(3, "PC商城"),
	PEOPLE_GUESS_REG(4, "全民推广扫码"),
	PEOPLE_SEPARATION_REG(5, "全民推广分享"),
	MERCHANT_PAY_REG(6, "商家支付"),
	MERCHANT_GUESS_REG(7, "商家推广扫码"),
	PROXY_SYSTEM_REG(8, "代理系统"),
	KAI_FU_MEMBER_REG(9, "凯富会员"),
	MEIDUI_MEMBER_REG(10, "美兑会员"),
	OTHER_REG(11, "其他");
	
	private Integer code;
	private String name;
	
	private ConstRegisterSource(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(Integer code){
		ConstRegisterSource[] values = ConstRegisterSource.values();
		for(ConstRegisterSource val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}
