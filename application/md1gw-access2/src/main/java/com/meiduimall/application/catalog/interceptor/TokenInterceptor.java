package com.meiduimall.application.catalog.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.catalog.annotation.HasToken;
import com.meiduimall.application.catalog.util.StringUtil;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.HttpUtils;

public class TokenInterceptor implements HandlerInterceptor {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

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
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			String token = request.getParameter("token");

			if (method.getAnnotation(HasToken.class) != null) {
				/** token为必传参数时 */
				logger.info("验证token：" + token);

				if (StringUtil.isEmptyByString(token)) {
					// token为空，不通过
					return outPut(response, BaseApiCode.NO_LOGIN);
				}

				// 调用会员系统，验证token
				if (checkToken(request, response, token)) {
					// 验证通过，放行
					return true;
				} else {
					// 验证不通过，不放行
					return outPut(response, BaseApiCode.NO_LOGIN);
				}

			} else {
				/** token不是必须传递的参数时，先验证token，获取mem_id；不管验证是否通过，都放行 */
				if (!StringUtil.isEmptyByString(token)) {
					// 如果token不为空，需要调用会员系统根据token获取mem_id
					checkToken(request, response, token);
				}
				return true;
			}
		} catch (Exception e) {
			logger.info("验证token，拦截器出现异常：" + e);
			return outPut(response, BaseApiCode.TOKEN_VALIDATE_ERROR);
		}
	}

	private boolean checkToken(HttpServletRequest request, HttpServletResponse response, String token)
			throws Exception {
		// 调用会员系统，验证token
		String host = env.getProperty("member.host");
		String uri = "/member/front_user_center/v1/get_memid_by_token";
		String url = host + uri + "?token=" + token;

		String result = HttpUtils.get(url);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj != null) {
			int status = jsonObj.getIntValue("status");
			if (status == 0) {
				JSONObject data = jsonObj.getJSONObject("data");
				if (data != null) {
					String memId = data.getString("memId");
					if (StringUtil.isEmptyByString(memId)) {
						request.setAttribute("mem_id", memId);
						logger.info("验证token通过：" + memId);
						return true;
					}
				}
			}
		}

		logger.info("验证token不通过：" + token);
		return false;
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
