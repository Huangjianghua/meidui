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
	
    @ExceptionHandler(value=MethodArgumentNotValidException.class)  
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,MethodArgumentNotValidException exception) throws Exception {  
        StringBuffer sb=new StringBuffer();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) { 
        	sb.append(error.getDefaultMessage()).append(";");
        }  
        return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
    }  

}
