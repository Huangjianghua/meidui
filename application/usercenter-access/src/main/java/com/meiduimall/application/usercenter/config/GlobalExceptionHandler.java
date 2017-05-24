package com.meiduimall.application.usercenter.config;

import javax.servlet.http.HttpServletRequest;

import com.meiduimall.exception.MdBizException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;




/**
 * 全局异常处理
 * @author chencong
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = MdBizException.class)
  public ResBodyData bizeExceptionHandler(HttpServletRequest request, MdBizException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+ApiStatusConst.getZhMsg(exception.getCode()));
    return new ResBodyData(exception.getCode(),ApiStatusConst.getZhMsg(exception.getCode()));
  }
  
  @ExceptionHandler(value = MdSysException.class)
  public ResBodyData systemExceptionHandler(HttpServletRequest request, MdSysException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+ApiStatusConst.getZhMsg(exception.getCode()));
    return new ResBodyData(exception.getCode(),ApiStatusConst.getZhMsg(exception.getCode()));
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