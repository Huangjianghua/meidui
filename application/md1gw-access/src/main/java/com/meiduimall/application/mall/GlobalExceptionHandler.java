/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.application.mall;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meiduimall.application.mall.constant.MallApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;

/**
 * 统一错误码异常处理
 * Created by simon on 14/03/2017.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object methodJsonArgumentNotValidHandler(HttpServletRequest request,
			MethodArgumentNotValidException exception) {
		StringBuilder sb = new StringBuilder();
		exception.getBindingResult().getFieldErrors().forEach((error) -> {
			sb.append(error.getDefaultMessage()).append(";");
		});
		logger.error("MethodArgumentNotValidException : " + sb.toString());
		return new ResBodyData(MallApiCode.REQUEST_PARAMS_ERROR, MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = BindException.class)
	public Object methodFromArgumentNotValidHandler(HttpServletRequest request, BindException exception) {
		StringBuilder sb = new StringBuilder();
		exception.getBindingResult().getFieldErrors().forEach((error) -> {
			sb.append(error.getDefaultMessage()).append(";");
		});
		logger.error("BindException : " + sb.toString());
		return new ResBodyData(MallApiCode.REQUEST_PARAMS_ERROR, MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR),
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