package com.meiduimall.service.settlement.exception;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.core.ResBodyData;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: GlobalExceptionHandler.java
 * Author:   Administrator
 * Date:     2017年3月17日 下午4:11:30
 * Description: 全局参数验证不通过的处理
 */
@ControllerAdvice(basePackages = "com.meiduimall.service.settlement.api")
@ResponseBody
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value=MethodArgumentNotValidException.class)  
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,MethodArgumentNotValidException exception) throws Exception {  
        StringBuffer sb=new StringBuffer();
        exception.getBindingResult().getFieldErrors().forEach((error)->{
        	sb.append(error.getDefaultMessage()).append(";");
        });
        return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
    }  

}
