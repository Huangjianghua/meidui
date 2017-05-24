package com.meiduimall.application.mall.catalog.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.mall.catalog.annotation.HasToken;
import com.meiduimall.application.mall.catalog.constant.ApplMallApiCode;
import com.meiduimall.application.mall.catalog.constant.ApplMallConstant;
import com.meiduimall.application.mall.catalog.entity.MemIdResult;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;

public class TokenInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	@Autowired
	private Environment env;

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

			String token = request.getParameter("token");

			if (method.getAnnotation(HasToken.class) != null) {
				/** token为必传参数时 */
				logger.info("验证token：" + token);

				if (StringUtils.isBlank(token)) {
					// token为空，不通过
					outPut(response, ApplMallApiCode.NO_LOGIN);
					return false;
				}

				// 调用会员系统，验证token
				if (checkToken(request, token)) {
					// 验证通过，放行
					return true;
				} else {
					// 验证不通过，不放行
					outPut(response, ApplMallApiCode.NO_LOGIN);
					return false;
				}

			} else {
				/** token不是必须传递的参数时，先验证token，获取memId；不管验证是否通过，都放行 */
				if (!StringUtils.isBlank(token)) {
					// 如果token不为空，需要调用会员系统根据token获取memId
					checkToken(request, token);
				}
				return true;
			}
		} catch (Exception e) {
			logger.info("验证token，拦截器出现异常：" + e);
			outPut(response, ApplMallApiCode.TOKEN_VALIDATE_ERROR);
			return false;
		}
	}

	/**
	 * 调用会员系统，验证token
	 * 
	 * @param request
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private boolean checkToken(HttpServletRequest request, String token) {

		String host = env.getProperty("member-access.host");
		String uri = ApplMallConstant.ACCESS_MEMBER_BASE_URL + "/get_memid_by_token";
		String url = host + uri + "?token=" + token;

		String result = "";
		try {
			result = HttpUtils.get(url);
		} catch (IOException e) {
			logger.info("根据token获取memId，请求会员系统异常：" + e);
			return false;
		}

		MemIdResult memIdResult = JsonUtils.jsonToBean(result, MemIdResult.class);
		if (memIdResult != null && memIdResult.getData() != null) {
			String memId = memIdResult.getData().getMemId();
			if (!StringUtils.isBlank(memId)) {
				request.setAttribute("memId", memId);
				logger.info("根据token获取memId成功：" + memId);
				return true;
			}
		}

		logger.info("根据token获取memId失败：" + token);
		return false;
	}

	/**
	 * 拦截请求，并输出错误信息
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param status
	 *            状态码
	 * @throws IOException
	 */
	private void outPut(HttpServletResponse response, Integer status) throws IOException {
		ResBodyData result = new ResBodyData(status, ApplMallApiCode.getZhMsg(status),
				JsonUtils.getInstance().createObjectNode());
		try {
			response.getWriter().write(JsonUtils.beanToJson(result));
		} catch (IOException e) {
			logger.error("拦截器输出异常: " + e);
			throw new ServiceException(ApplMallApiCode.OUT_PUT_EXCEPTION,
					ApplMallApiCode.getZhMsg(ApplMallApiCode.OUT_PUT_EXCEPTION));
		}
	}
}
