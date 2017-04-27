package com.meiduimall.application.usercenter.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.BizException;
import com.meiduimall.exception.SystemException;




/**
 * 全局异常处理
 * @author chencong
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = BizException.class)
  public ResBodyData bizeExceptionHandler(HttpServletRequest request, BizException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+ApiStatusConst.getZhMsg(exception.getCode()));
    return new ResBodyData(exception.getCode(),exception.getLocalizedMessage());
  }
  
  @ExceptionHandler(value = SystemException.class)
  public ResBodyData systemExceptionHandler(HttpServletRequest request, SystemException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+ApiStatusConst.getZhMsg(exception.getCode()));
    return new ResBodyData(exception.getCode(),exception.getLocalizedMessage());
  }


  @ExceptionHandler(value = BindException.class)
  public ResBodyData errorHandlerOverJson(HttpServletRequest request, BindException exception) {
	  StringBuilder sb=new StringBuilder();
      exception.getBindingResult().getFieldErrors().forEach((error)->{
      	sb.append(error.getDefaultMessage()).append(";");
      });
      return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
  }
  
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResBodyData errorHandlerByJson(HttpServletRequest request, MethodArgumentNotValidException exception) {
	  StringBuilder sb=new StringBuilder();
      exception.getBindingResult().getFieldErrors().forEach((error)->{
      	sb.append(error.getDefaultMessage()).append(";");
      });
      return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
  }

}