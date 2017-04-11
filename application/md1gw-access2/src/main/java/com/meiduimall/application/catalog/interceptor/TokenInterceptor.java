package com.meiduimall.application.catalog.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.catalog.annotation.HasToken;
import com.meiduimall.application.catalog.util.StringUtil;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

public class TokenInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			if (method.getAnnotation(HasToken.class) != null) {
				/** 需要校验token */
				String token = request.getParameter("token");
				if (StringUtil.isEmptyByString(token)) {
					// token为空，不通过
					return outPut(response, BaseApiCode.NO_LOGIN);
				}

				// 调用会员系统，验证token
				return true;
				
			} else {
				/** 不需要校验token，放行 */
				return true;
			}
		} catch (Exception e) {
			return outPut(response, BaseApiCode.TOKEN_VALIDATE_ERROR);
		}
	}

	private boolean outPut(HttpServletResponse response, Integer code) throws Exception {
		ResBodyData result = new ResBodyData();
		result.setStatus(code);
		result.setMsg(BaseApiCode.getZhMsg(code));
		result.setData(new JSONObject());
		String resultJson = JSON.toJSONString(result);
		response.getWriter().write(resultJson);
		return false;
	}
}
