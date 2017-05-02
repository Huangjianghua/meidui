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



public class ApiException extends MdBizException {


	private static final long serialVersionUID = -2704857873923117602L;

  public ApiException(Throwable cause) {
    super(cause);
  }

  public ApiException(Throwable cause, int code, String... params) {
    super(cause, code, params);
  }

  public ApiException(int code, String... params) {
    super(code, params);
  }
}
