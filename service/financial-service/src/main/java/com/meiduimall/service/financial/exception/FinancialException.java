package com.meiduimall.service.financial.exception;

/**
 * @项目名称: 美兑商城-商品微服务
 *
 * @文件名称: CatalogException.java
 * @Date: 2017-04-19
 * @Copyright: 2017-2022 www.meiduimall.com Inc. All rights reserved.
 * @描述: 统一异常处理
 */
public class FinancialException extends Exception {

	private static final long serialVersionUID = 1113381905689226116L;
	
	private Integer code;

	public FinancialException(Integer code) {
		this.code = code;
	}

	public FinancialException(String e) {
		super(e);
	}

	public FinancialException(Throwable cause) {
		super(cause);
	}

	public FinancialException(Integer code, String e) {
		super(e);
		this.code = code;
	}

	public FinancialException(Integer code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public FinancialException(String message, Throwable cause) {
		super(message, cause);
	}

	public FinancialException(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
