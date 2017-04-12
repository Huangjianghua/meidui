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
 * 统一错误码异常
 *
 * Created by simon on 14/03/2017.
 */
public class ServiceException extends RuntimeException {

    private Integer code;
	public ServiceException(Integer code,String e) {
		super(e);
	}
	
	public ServiceException(Integer code,Throwable cause) {
        super(cause);
    }
	
	public ServiceException(Integer code,String message, Throwable cause) {
		super(message, cause);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}
