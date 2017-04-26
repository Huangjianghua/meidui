package com.meiduimall.application.usercenter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.core.ResBodyData;

/**
 * 拦截器
 * @author chencong
 *
 */
public class ValInterceptor implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(ValInterceptor.class);
	
	/**拦截器校验结果*/
	public static ThreadLocal<ResBodyData> apiValResult=new ThreadLocal<>();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		if(ValRequest.apiReqData!=null)
			ValRequest.apiReqData.remove();
		if(apiValResult!=null)
			apiValResult.remove();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView){
		
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		logger.info("拦截器收到API请求");
		ResBodyData resBodyData = ValToken.valApiTokenFlag((HandlerMethod)object);
        if (resBodyData.getStatus()==0){
        	resBodyData= ValRequest.validate(request,"Y".equals(resBodyData.getData().toString())?true:false);
        }
        apiValResult.set(resBodyData);
        logger.info("拦截器校验API请求结果：{}",resBodyData.toString());
        return true;
	}

}