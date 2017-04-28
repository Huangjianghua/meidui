package com.meiduimall.service.account.constant;


/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: MemSignSourceEnum.java
 * @Author:   jianhua.huang 
 * @Date:     2017年4月19日 上午9:32:05
 * @Description:会员注册来源枚举
 */
public enum MemSignSourceEnum {
	/**0表示PC端注册  **/
	PC_REG(0, "PC端"),
	/**1表示o2o注册  **/
	O2O_REG(1, "O2O"),
	/**2表示会员结算系统数据迁移注册  **/
	MEM_SETTLEMENT_MOVE_REG(2, "结算系统迁移"),
	/**3表示壹购物注册  **/
	ONEGW_REG(3, "壹购物注册"),
	/**4表示壹购物商城迁移 **/
	ONEGW_MOVE_REG(4, "壹购物商城迁移");

	private Integer code;
	
	private String name;

	private MemSignSourceEnum(Integer code, String name) {
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
		MemSignSourceEnum[] values = MemSignSourceEnum.values();
		for(MemSignSourceEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}
