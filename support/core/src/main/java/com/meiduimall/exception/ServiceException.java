/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.exception;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: ServiceException.java
 * Author:   Administrator
 * Date:     2017年4月19日 下午4:47:53
 * Description: service层统一错误码异常
 */
public class ServiceException extends BizException {
	
	private static final long serialVersionUID = 3208750423221347264L;


	public ServiceException(Integer code, String msg) {
		super(code,msg);
	}


	public ServiceException(Integer code, String message, Throwable cause) {
		super(code, message,cause);
	}
}
