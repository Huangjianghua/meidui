package com.meiduimall.application.usercenter.interceptor;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.util.StringUtil;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.redis.util.RedisTemplate;

/**
 * API校验token
 * @author chencong
 *
 */
public class ValToken {
	
	private static Logger logger = LoggerFactory.getLogger(ValToken.class);

	/**
	 * 校验token
	 * @param token
	 * @return 统一数据返回格式
	 */
	public static ResBodyData valToken(String token) {
		logger.info("拦截器开始校验token");
		ResBodyData resBodyData = new ResBodyData(null,null);
		try {
			if(RedisTemplate.getJedisInstance().execExistsFromCache(token)){
				String memId = RedisTemplate.getJedisInstance().execGetFromCache(token);
				if (!StringUtil.isEmptyByString(memId)) {
					resBodyData.setStatus(ApiStatusConst.SUCCESS);
					resBodyData.setData(memId);
				} else {
					resBodyData.setStatus(ApiStatusConst.MEMID_OF_TOKEN_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.MEMID_OF_TOKEN_EMPTY));
				}
			}
			else {
				resBodyData.setStatus(ApiStatusConst.TOKEN_NOT_EXIST);
				resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.TOKEN_NOT_EXIST));
			}
		} catch (Exception e) {
			logger.error("exec valToken() error:{}",e.getMessage());
			resBodyData.setStatus(ApiStatusConst.VAL_REDIS_TOKEN_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.VAL_REDIS_TOKEN_EX));
		}
		logger.info("拦截器校验token结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**
	 * 判断API接口是否有token注解
	 * @param handler API接口方法
	 * @return 统一返回数据格式:flag=Y表示有token注解，flag=N表示无token注解
	 */
	public static ResBodyData valApiTokenFlag(HandlerMethod handler) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,null);
		resBodyData.setData("Y");
		try {
			Method method = handler.getMethod();
			if (method.getAnnotation(HasToken.class)==null){
				resBodyData.setData("N");
			}
		}
		catch (Exception e) {
			logger.error("exec valApiTokenFlag() error:{}",e.getMessage());
			resBodyData.setStatus(ApiStatusConst.VAL_TOKEN_ANNOTATION_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.VAL_TOKEN_ANNOTATION_EX));
		}
		logger.info("拦截器判断API接口是否有token注解结果:{}",resBodyData.toString());
		return resBodyData;
	}
}