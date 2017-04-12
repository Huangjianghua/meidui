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

public class ApiException extends RuntimeException {

	private Integer code;

	public ApiException(Integer code) {
		this.code = code;
	}

	public ApiException(String e) {
		super(e);
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	public ApiException(Integer code, String e) {
		super(e);
		this.code = code;
	}

	public ApiException(Integer code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiException(Integer code, String message, Throwable cause) {
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
