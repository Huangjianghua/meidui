/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms;


import com.meiduimall.core.ErrorInfo;
import com.meiduimall.core.ErrorInfoEnum;
import com.meiduimall.core.ResultBody;
import com.meiduimall.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * 统一错误码异常处理
 *
 * Created by simon on 14/03/2017.
 */
@RestControllerAdvice
public class ServiceExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

  @ExceptionHandler(value = ServiceException.class)
  public ResultBody serviceExceptionHandler(HttpServletRequest request, ServiceException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+exception.getLocalizedMessage());
    ErrorInfo errorInfo = exception.getErrorInfo();
    ResultBody result = new ResultBody(errorInfo,null);
    return result;
  }

  @ExceptionHandler(value = BindException.class)
  public ResultBody errorHandlerOverJson(HttpServletRequest request, BindException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+exception.getLocalizedMessage());
    ResultBody result = new ResultBody(ErrorInfoEnum.VALID_ERROR,exception.getLocalizedMessage());
    return result;
  }
}