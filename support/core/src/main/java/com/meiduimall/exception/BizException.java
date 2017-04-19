package com.meiduimall.exception;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: BizException.java
 * Author:   Administrator
 * Date:     2017年4月19日 下午4:57:26
 * Description: 业务层统一错误码异常
 */
public class BizException extends RuntimeException{
	
	private static final long serialVersionUID = 4172573415632244679L;
	
	private Integer code;


	public BizException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public BizException(Integer code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
