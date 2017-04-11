package com.meiduimall.service.catalog.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.annotation.HasMemId;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.service.UserService;
import com.meiduimall.service.catalog.util.StringUtil;

public class MemIdInterceptor implements HandlerInterceptor {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MemIdInterceptor.class);

	@Autowired
	private UserService userService;

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

			if (method.getAnnotation(HasMemId.class) != null) {
				/** 需要校验mem_id */
				String mem_id = request.getParameter("mem_id");
				if (StringUtil.isEmptyByString(mem_id)) {
					// mem_id为空，不通过
					return outPut(response, BaseApiCode.NO_LOGIN);
				}

				SysuserAccount sysuserAccount = userService.getUserByMemId(mem_id);
				if (sysuserAccount == null) {
					// 验证不通过
					return outPut(response, BaseApiCode.NO_LOGIN);
				} else {
					// 验证通过，放行
					request.setAttribute("sysuserAccount", sysuserAccount);
					return true;
				}
			} else {
				/** 不需要校验mem_id，放行 */
				return true;
			}
		} catch (Exception e) {
			logger.error("验证mem_id，拦截器报异常： " + e);
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
