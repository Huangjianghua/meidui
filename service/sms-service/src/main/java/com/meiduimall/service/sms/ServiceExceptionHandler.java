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
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;

/**
 * 统一错误码异常处理
 *
 * Created by simon on 14/03/2017.
 */
@RestControllerAdvice
public class ServiceExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

  @ExceptionHandler(value = ServiceException.class)
  public ResBodyData serviceExceptionHandler(HttpServletRequest request, ServiceException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+exception.getLocalizedMessage());
    ResBodyData result = new ResBodyData(exception.getCode(),exception.getLocalizedMessage());
    return result;
  }

  @ExceptionHandler(value = BindException.class)
  public ResBodyData errorHandlerOverJson(HttpServletRequest request, BindException exception) {
	  StringBuilder sb=new StringBuilder();
      exception.getBindingResult().getFieldErrors().forEach((error)->{
      	sb.append(error.getDefaultMessage()).append(";");
      });
      return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
  }
}