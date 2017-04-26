package com.meiduimall.service.financial.exception;

import com.meiduimall.exception.ServiceException;

/**
 * 自定义异常
 * 
 * @author yangchang
 */
public class FinancialException extends ServiceException {

	private static final long serialVersionUID = 1113381905689226116L;

	public FinancialException(Integer code, String msg) {
		super(code, msg);
	}

	public FinancialException(Integer code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
