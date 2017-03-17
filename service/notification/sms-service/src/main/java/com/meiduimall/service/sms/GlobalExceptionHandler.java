package com.meiduimall.service.sms;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.core.ResBodyData;


@ControllerAdvice(basePackages = "com.meiduimall.service.sms.controller")
@ResponseBody
public class GlobalExceptionHandler {
	
	//添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例  
    @ExceptionHandler(value=MethodArgumentNotValidException.class)  
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,MethodArgumentNotValidException exception) throws Exception {  
        StringBuffer sb=new StringBuffer();
    	//解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息  
        for (FieldError error : exception.getBindingResult().getFieldErrors()) { 
        	sb.append(error.getDefaultMessage()).append(";");
        }  
        return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
    }  

}
