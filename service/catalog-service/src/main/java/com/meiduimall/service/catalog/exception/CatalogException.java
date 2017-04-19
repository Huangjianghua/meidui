package com.meiduimall.service.catalog.exception;

/**
 * @项目名称: 美兑商城-商品微服务
 *
 * @文件名称: CatalogException.java
 * @Date: 2017-04-19
 * @Copyright: 2017-2022 www.meiduimall.com Inc. All rights reserved.
 * @描述: 统一异常处理
 */
public class CatalogException extends Exception {

	private static final long serialVersionUID = 1113381905689226116L;
	
	private Integer code;

	public CatalogException(Integer code) {
		this.code = code;
	}

	public CatalogException(String e) {
		super(e);
	}

	public CatalogException(Throwable cause) {
		super(cause);
	}

	public CatalogException(Integer code, String e) {
		super(e);
		this.code = code;
	}

	public CatalogException(Integer code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public CatalogException(String message, Throwable cause) {
		super(message, cause);
	}

	public CatalogException(Integer code, String message, Throwable cause) {
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
