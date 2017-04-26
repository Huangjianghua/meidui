package com.meiduimall.service.catalog.exception;

import com.meiduimall.exception.ServiceException;

/**
 * 自定义异常
 * 
 * @author yangchang
 *
 */
public class CatalogException extends ServiceException {

	private static final long serialVersionUID = 1113381905689226116L;

	public CatalogException(Integer code, String msg) {
		super(code, msg);
	}

	public CatalogException(Integer code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
