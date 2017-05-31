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
 * <p>
 * Created by simon on 14/03/2017.
 */
public class ServiceException extends MdBizException {

  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(Throwable cause, int code, String... params) {
    super(cause, code, params);
  }

  public ServiceException(int code, String... params) {
    super(code, params);
  }
}
