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
 * FileName: ApiException.java
 * Author:   Administrator
 * Date:     2017年4月19日 下午4:57:46
 * Description: 控制层统一错误码异常
 */
public class ApiException extends BizException {


	private static final long serialVersionUID = -2704857873923117602L;
	
	public ApiException(Integer code, String msg) {
		super(code,msg);
	}

	public ApiException(Integer code, String msg, Throwable cause) {
		super(code, msg,cause);
	}

}
