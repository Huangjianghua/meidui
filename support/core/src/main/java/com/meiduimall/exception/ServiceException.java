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

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 5985132497087826419L;
	
	public ServiceException(String e){
		super(e);
	}
	
	public ServiceException(Exception e){
		super(e);
	}

	public ServiceException(String message, Throwable cause){
		super(message, cause);
	}

}
