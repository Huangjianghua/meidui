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

import com.meiduimall.core.ErrorInfo;

/**
 * 统一错误码异常
 *
 * Created by simon on 14/03/2017.
 */
public class ServiceException extends RuntimeException {

  private ErrorInfo errorInfo;

  public ServiceException(ErrorInfo errorInfo) {
    this.errorInfo = errorInfo;
  }

  public ErrorInfo getErrorInfo() {
    return errorInfo;
  }

  public void setErrorInfo(ErrorInfo errorInfo) {
    this.errorInfo = errorInfo;
  }
}
