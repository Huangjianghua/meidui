package com.meiduimall.application.usercenter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 * @author chencong
 *
 */
public class ValInterceptor implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(ValInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		if(ValRequest.apiReqData!=null)
			ValRequest.apiReqData.remove();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView){
		
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		logger.info("拦截器收到API请求");
		boolean hasToken=ValToken.valApiTokenFlag((HandlerMethod)object);
		ValRequest.validate(request,hasToken);
        return true;
	}

}