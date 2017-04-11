package com.meiduimall.application.search.manage;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.application.search.manage.constant.SysConstant;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: GlobalExceptionHandler.java
 * Author:   Administrator
 * Date:     2017年4月11日 下午3:41:29
 * Description: 全局参数验证不通过的处理
 */
@ControllerAdvice(basePackages = "com.meiduimall.application.search.manage.controller")
@ResponseBody
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value=MethodArgumentNotValidException.class)  
    public Object MethodJsonArgumentNotValidHandler(HttpServletRequest request,MethodArgumentNotValidException exception) throws Exception {  
        StringBuffer sb=new StringBuffer();
        exception.getBindingResult().getFieldErrors().forEach((error)->{
        	sb.append(error.getDefaultMessage()).append(";");
        });
        Map<String, Object> result=new HashMap<String, Object>();
        result.put(SysConstant.STATUS_CODE,HttpStatus.SC_BAD_REQUEST);
		result.put(SysConstant.RESULT_MSG, sb.toString());
        return result;  
    }  
    
    
    @ExceptionHandler(value=BindException.class)  
    public Object MethodFromArgumentNotValidHandler(HttpServletRequest request,BindException exception) throws Exception {  
        StringBuffer sb=new StringBuffer();
        exception.getBindingResult().getFieldErrors().forEach((error)->{
        	sb.append(error.getDefaultMessage()).append(";");
        });
        Map<String, Object> result=new HashMap<String, Object>();
        result.put(SysConstant.STATUS_CODE,HttpStatus.SC_BAD_REQUEST);
		result.put(SysConstant.RESULT_MSG, sb.toString());
        return result;  
    }  


}
