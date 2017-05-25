<<<<<<< HEAD
package com.meiduimall.application.mall.catalog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.application.mall.catalog.constant.MallApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;

/**
 * 全局异常处理
 * 
 * @author yangchang
 */
@ControllerAdvice(basePackages = "com.meiduimall.application.mall.catalog.controller")
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object methodJsonArgumentNotValidHandler(HttpServletRequest request,
			MethodArgumentNotValidException exception) {
		return new ResBodyData(MallApiCode.REQUEST_PARAMS_ERROR,
				MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = BindException.class)
	public Object methodFromArgumentNotValidHandler(HttpServletRequest request, BindException exception) {
		return new ResBodyData(MallApiCode.REQUEST_PARAMS_ERROR,
				MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = ApiException.class)
	public Object apiExceptionHandler(HttpServletRequest request, ApiException exception) {
		return new ResBodyData(exception.getCode(), MallApiCode.getZhMsg(exception.getCode()),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = ServiceException.class)
	public Object serviceExceptionHandler(HttpServletRequest request, ServiceException exception) {
		return new ResBodyData(exception.getCode(), MallApiCode.getZhMsg(exception.getCode()),
				JsonUtils.getInstance().createObjectNode());
	}
}
=======
package com.meiduimall.application.mall.catalog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.application.mall.catalog.constant.ApplMallApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;

/**
 * 全局异常处理
 * 
 * @author yangchang
 */
@ControllerAdvice(basePackages = "com.meiduimall.application.mall.catalog.controller")
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object methodJsonArgumentNotValidHandler(HttpServletRequest request,
			MethodArgumentNotValidException exception) {
		return new ResBodyData(ApplMallApiCode.REQUEST_PARAMS_ERROR,
				ApplMallApiCode.getZhMsg(ApplMallApiCode.REQUEST_PARAMS_ERROR),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = BindException.class)
	public Object methodFromArgumentNotValidHandler(HttpServletRequest request, BindException exception) {
		return new ResBodyData(ApplMallApiCode.REQUEST_PARAMS_ERROR,
				ApplMallApiCode.getZhMsg(ApplMallApiCode.REQUEST_PARAMS_ERROR),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = ApiException.class)
	public Object apiExceptionHandler(HttpServletRequest request, ApiException exception) {
		return new ResBodyData(exception.getCode(), ApplMallApiCode.getZhMsg(exception.getCode()),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = ServiceException.class)
	public Object serviceExceptionHandler(HttpServletRequest request, ServiceException exception) {
		return new ResBodyData(exception.getCode(), ApplMallApiCode.getZhMsg(exception.getCode()),
				JsonUtils.getInstance().createObjectNode());
	}
}
>>>>>>> refs/remotes/origin/feature/V4.0.2-Team2
