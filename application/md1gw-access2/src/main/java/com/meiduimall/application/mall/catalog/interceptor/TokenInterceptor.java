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
import com.meiduimall.application.mall.catalog.constant.ApplicationMallApiCode;
import com.meiduimall.application.mall.catalog.entity.MemIdResult;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.JsonUtils;

public class TokenInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	@Autowired
	private Environment env;

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
		// try {
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
				return outPut(response, ApplicationMallApiCode.NO_LOGIN);
			}

			// 调用会员系统，验证token
			if (checkToken(request, response, token)) {
				// 验证通过，放行
				return true;
			} else {
				// 验证不通过，不放行
				return outPut(response, ApplicationMallApiCode.NO_LOGIN);
			}

		} else {
			/** token不是必须传递的参数时，先验证token，获取mem_id；不管验证是否通过，都放行 */
			if (!StringUtils.isBlank(token)) {
				// 如果token不为空，需要调用会员系统根据token获取mem_id
				checkToken(request, response, token);
			}
			return true;
		}
		// } catch (Exception e) {
		// logger.info("验证token，拦截器出现异常：" + e);
		// return outPut(response, BaseApiCode.TOKEN_VALIDATE_ERROR);
		// }
	}

	/**
	 * 调用会员系统，验证token
	 * 
	 * @param request
	 * @param response
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private boolean checkToken(HttpServletRequest request, HttpServletResponse response, String token) {

		String host = env.getProperty("member.host");
		String uri = "/member/front_user_center/v1/get_memid_by_token";
		String url = host + uri + "?token=" + token;

		String result = "";
		try {
			result = HttpUtils.get(url);
		} catch (IOException e) {
			logger.info("根据token获取mem_id，请求会员系统异常：" + e);
			return false;
		}

		MemIdResult memIdResult = JsonUtils.jsonToBean(result, MemIdResult.class);
		if (memIdResult != null && memIdResult.getData() != null) {
			String memId = memIdResult.getData().getMemId();
			if (!StringUtils.isBlank(memId)) {
				request.setAttribute("mem_id", memId);
				logger.info("根据token获取mem_id成功：" + memId);
				return true;
			}
		}

		logger.info("根据token获取mem_id失败：" + token);
		return false;
	}

	/**
	 * 拦截请求，并输出错误信息
	 * 
	 * @param response
	 * @param status
	 * @return
	 * @throws IOException
	 */
	private boolean outPut(HttpServletResponse response, Integer status) throws IOException {
		ResBodyData result = new ResBodyData(status, ApplicationMallApiCode.getZhMsg(status),
				JsonUtils.getInstance().createObjectNode());
		response.getWriter().write(JsonUtils.beanToJson(result));
		return false;
	}
}
