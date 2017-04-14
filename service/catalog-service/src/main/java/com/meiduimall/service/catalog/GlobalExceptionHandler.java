package com.meiduimall.service.catalog;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: GlobalExceptionHandler.java
 * Author:   yangchangfu
 * Date:     2017年3月17日 下午4:11:30
 * Description: 全局参数验证不通过的处理
 */
@ControllerAdvice(basePackages = "com.meiduimall.service.catalog.controller")
@ResponseBody
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value=MethodArgumentNotValidException.class)  
    public Object MethodJsonArgumentNotValidHandler(HttpServletRequest request,MethodArgumentNotValidException exception) throws Exception {  
        return new ResBodyData(HttpStatus.SC_BAD_REQUEST, "请求参数错误", new JSONObject());  
    }  
    
    @ExceptionHandler(value=BindException.class)  
    public Object MethodFromArgumentNotValidHandler(HttpServletRequest request,BindException exception) throws Exception {  
        return new ResBodyData(HttpStatus.SC_BAD_REQUEST, "请求参数错误", new JSONObject());  
    }  
    
    @ExceptionHandler(value = ServiceException.class)
	public Object ServiceExceptionHandler(HttpServletRequest request, ServiceException exception) {
		return new ResBodyData(exception.getCode(), BaseApiCode.getZhMsg(exception.getCode()),
				JsonUtils.getInstance().createObjectNode());
	}
}
