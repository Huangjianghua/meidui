package com.meiduimall.service.financial.exception;

import com.meiduimall.exception.ServiceException;

/**
 * @项目名称: 美兑商城-商品微服务
 *
 * @文件名称: CatalogException.java
 * @Date: 2017-04-19
 * @Copyright: 2017-2022 www.meiduimall.com Inc. All rights reserved.
 * @描述: 统一异常处理
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
