package com.meiduimall.service.catalog.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.annotation.HasMemId;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.service.UserService;

public class MemIdInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(MemIdInterceptor.class);

	@Autowired
	private UserService userService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Do nothing because of X and Y.
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Do nothing because of X and Y.
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			request.setCharacterEncoding(Constants.ENCODE_UTF8);
			response.setCharacterEncoding(Constants.ENCODE_UTF8);
			response.setContentType(Constants.CONTENT_TYPE_TEXT_UTF8);

			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			if (method.getAnnotation(HasMemId.class) != null) {
				/** 需要校验memId */
				String memId = request.getParameter("memId");
				if (StringUtils.isBlank(memId)) {
					// memId为空，不通过
					return outPut(response, ServiceCatalogApiCode.NO_LOGIN);
				}

				SysuserAccount sysuserAccount = userService.getUserByMemId(memId);
				if (sysuserAccount == null) {
					// 验证不通过
					return outPut(response, ServiceCatalogApiCode.NO_LOGIN);
				} else {
					// 验证通过，放行
					request.setAttribute("sysuserAccount", sysuserAccount);
					return true;
				}
			} else {
				/** 不需要校验memId，放行 */
				return true;
			}
		} catch (Exception e) {
			logger.error("验证memId，拦截器报异常： " + e);
			return outPut(response, ServiceCatalogApiCode.MEMID_VALIDATE_ERROR);
		}
	}

	private boolean outPut(HttpServletResponse response, Integer code) {
		ResBodyData result = new ResBodyData(code, ServiceCatalogApiCode.getZhMsg(code),
				JsonUtils.getInstance().createObjectNode());
		try {
			response.getWriter().write(JsonUtils.beanToJson(result));
		} catch (IOException e) {
			logger.error("拦截器输出异常: " + e);
			throw new ServiceException(ServiceCatalogApiCode.OUT_PUT_EXCEPTION,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.OUT_PUT_EXCEPTION));
		}
		return false;
	}
}
