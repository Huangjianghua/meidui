package com.meiduimall.service.catalog.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.annotation.HasToken;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.service.UserService;

public class TokenInterceptor implements HandlerInterceptor {

	// public static ThreadLocal<SysuserAccount> threadLocal = new
	// ThreadLocal<>();

	@Autowired
	private UserService userService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// if (threadLocal != null) {
		// threadLocal.remove();
		// }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.getAnnotation(HasToken.class) != null) {
			// 需要校验token
			SysuserAccount sysuserAccount = userService.getUserByToken(request.getParameter("token"));
			if (sysuserAccount == null) {
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.NO_LOGIN);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NO_LOGIN));
				result.setData(new JSONObject());
				String resultJson = JSON.toJSONString(result);
				response.getWriter().write(resultJson);
				return false;
			} else {
				// threadLocal.set(sysuserAccount);
				request.setAttribute("sysuserAccount", sysuserAccount);
				return true;
			}
		} else {
			// 不需要校验token
			return true;
		}
	}

}
