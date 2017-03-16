package com.meiduimall.oauth;
import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.annotation.HasToken;
import com.meiduimall.constant.ApiRespConst;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.StringUtil;

/**
 * Title : Token 
 * Description : TokenTools
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月15日 下午3:38:29 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public class OauthToken {
	
	/**
	 * Description : 验证token
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午4:59:47 
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validateToken(String token) {
		JSONObject json = new JSONObject();
		try {
			String memid = JedisUtil.getJedisInstance().execGetFromCache(token);
			//取出memid
			if (!StringUtil.isEmptyByString(memid)) {
				json.put(ApiRespConst.STATUS_CODE, "0");
				json.put(ApiRespConst.RESULT_MSG, "token验证通过!");
				json.put(ApiRespConst.RESULT,memid);
				Logger.info("token验证通过!");
			} else {
				json.put(ApiRespConst.STATUS_CODE, "1008");
				json.put(ApiRespConst.RESULT_MSG, "用户令牌错误!");
				Logger.info("token验证失败!");
			}
		} catch (Exception e) {
			json.put(ApiRespConst.STATUS_CODE, "9999");
			json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
			Logger.error("验证token错误: %s", e.getMessage());
		}
		return json;
	}
	
	/**
	 * Description : 判断方法之上是否存在HasToken注解类
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月20日 上午11:04:22 
	 * 
	 * @param className
	 * @param methodName
	 * @param parametersTypes
	 * @return
	 */
	public static JSONObject validateTokenFlag(String className, String methodName, Object[] args) {
		JSONObject json = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			if (StringUtil.checkStr(className) && StringUtil.checkStr(methodName)) {
				Class<?>[] parametersTypes = null;
				if (null != args && args.length > 0) {
					parametersTypes = new Class<?>[args.length];
					for (int i = 0; i < args.length; i++) {
						parametersTypes[i] = args[i].getClass();
					}
				}
				Method method = Class.forName(className).getMethod(methodName, parametersTypes);
				json.put(ApiRespConst.STATUS_CODE, "0");
				json.put(ApiRespConst.RESULT_MSG, "token标识验证完毕!");
				if (method.isAnnotationPresent(HasToken.class)) {
					result.put("flag", "Y");
					json.put(ApiRespConst.RESULT, result);
				} else {
					result.put("flag", "N");
					json.put(ApiRespConst.RESULT, result);
				}
			} else {
				json.put(ApiRespConst.STATUS_CODE, "9999");
				json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
				Logger.error("验证token注解错误: 类名或方法名为空!");
			}
		} catch (Exception e) {
			json.put(ApiRespConst.STATUS_CODE, "9999");
			json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
			Logger.error("验证token注解错误: %s", e.getMessage());
		}
		return json;
	}
	
	/**
	 * Description : 拦截器判断接口是否需要token验证，需要验证的接口要加上自定义的@token注解，注解定义在com.meiduimall.annotaion
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月20日 下午4:15:34 
	 * 
	 * @param handler
	 * @return
	 */
	public static JSONObject validateTokenFlag(HandlerMethod handler) {
		JSONObject json = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			Method method = handler.getMethod();
			method.getName();
			json.put(ApiRespConst.STATUS_CODE, "0");
			json.put(ApiRespConst.RESULT_MSG, "token标识验证完毕!");
			if (method.getAnnotation(HasToken.class)!=null) {
				result.put("flag", "Y");
				json.put(ApiRespConst.RESULT, result);
			} else {
				result.put("flag", "N");
				json.put(ApiRespConst.RESULT, result);
			}
		} catch (Exception e) {
			json.put(ApiRespConst.STATUS_CODE, "9999");
			json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
			Logger.error("验证token注解错误: %s", e.getMessage());
		}
		return json;
	}
}