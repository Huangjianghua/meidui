package com.meiduimall.application.usercenter.interceptor;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.util.StringUtil;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
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
	 * @throws MdSysException 系统异常
	 */
	public static String valToken(String token) throws MdSysException {
		try {
			if(RedisTemplate.getJedisInstance().execExistsFromCache(token)){
				String memId = RedisTemplate.getJedisInstance().execGetFromCache(token);
				if (!StringUtil.isEmptyByString(memId)) {
					return memId;
				} else {
					logger.warn("redis中token:{}存在，但对应的memId为空",token);
					throw new MdSysException(ApiStatusConst.SYSTEM_ERROR);
				}
			}
			else {
				logger.warn("redis中未找到token:{}",token);
				throw new ServiceException(ApiStatusConst.LOGIN_EXPIRE);
			}
		} catch (Exception e) {
			logger.error("校验token程序异常:{}",e.toString());
			throw new MdSysException(ApiStatusConst.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 判断API接口是否有token注解
	 * @param handler API接口方法
	 * @return true:有token注解   false:无token注解
	 * @throws MdSysException 系统异常
	 */
	public static boolean valApiTokenFlag(HandlerMethod handler) throws MdSysException {
		try {
			Method method = handler.getMethod();
			if (method.getAnnotation(HasToken.class)==null){
				return false;
			}
		}
		catch (Exception e) {
			logger.error("判断API接口是否有token注解异常:{}",e.toString());
			throw new MdSysException(ApiStatusConst.SYSTEM_ERROR);
		}
		logger.info("判断API是否有token注解成功");
		return true;
	}
}