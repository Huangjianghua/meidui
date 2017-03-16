package com.meiduimall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.ApiRespConst;
import com.meiduimall.oauth.OauthToken;
import com.meiduimall.oauth.OauthValidate;
import com.meiduimall.util.Logger;

/**
 * 拦截器，验证token和sign
 * @author chencong
 *
 */
public class ValInterceptor implements HandlerInterceptor {
	
	/*拦截器校验返回的错误信息*/
	public static ThreadLocal<JSONObject> check_result=new ThreadLocal<>();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		/*接口执行完移除本地变量*/
		if(OauthValidate.postjson!=null)
			OauthValidate.postjson.remove();
		if(check_result!=null)
			check_result.remove();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		/**打印日志，例如请求URL，请求类型，IP等**/
		Logger.valInterceptorLog(request);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		/**判断接口是否需要token验证**/
		JSONObject valTokenFlag = OauthToken.validateTokenFlag((HandlerMethod)object);
        if ("0".equals(valTokenFlag.get(ApiRespConst.STATUS_CODE))) {
        	JSONObject result = valTokenFlag.getJSONObject(ApiRespConst.RESULT);
        	JSONObject valOauth = OauthValidate.validate(request,"Y".equals(result.get("flag"))?true:false);
        	//如果请求校验失败
    		if (!"0".equals(valOauth.get(ApiRespConst.STATUS_CODE))) {
    			Logger.info("请求校验失败! 错误信息: %s", valOauth.get(ApiRespConst.RESULT_MSG));
    		}
    		check_result.set(valOauth);
        } else {
        	return false;
        }
		return true;
	}

}