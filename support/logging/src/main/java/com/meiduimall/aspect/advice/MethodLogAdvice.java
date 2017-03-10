package com.meiduimall.aspect.advice;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

@Aspect
public class MethodLogAdvice {
	
	private static Logger logger = LoggerFactory.getLogger(MethodLogAdvice.class);
	
	/**
	 * 鍔熻兘鎻忚堪:  瀹氫箟鍒囧叆鐐筧llMethod()鐨勭幆缁曢�氱煡,鐜粫閫氱煡鏂规硶涓�瀹氳鎸夌収涓嬮潰鐨勫舰寮忓畾涔�(鍙彲浠ヤ慨鏀规柟娉曞悕鍜屽弬鏁板悕)
	 * Author: 闄堝缓瀹�
	 * Date:   2017骞�1鏈�18鏃� 涓嬪崍2:52:56   
	 * return  Object
	 */
	@Around("com.meiduimall.aspect.pointcut.MethodLogPointcut.pointcutLog()")
	public Object doSomethingAround(ProceedingJoinPoint point) throws Throwable {
		long startTime = System.currentTimeMillis();
		//鎷︽埅鐨勬柟娉曞悕绉�
		String methodName = point.getSignature().getName();
		//鎷︽埅鏂规硶鍙傛暟鍊�
		Object[] args=point.getArgs();
		String reqStr=JSON.toJSONString(args);
		logger.info(">>>>>>>>>>>>>>>>>>鏂规硶鍚嶏細{}>>start,鍙傛暟:{}",methodName,reqStr);
		//鎵ц鏂规硶閫昏緫
		Object obj = point.proceed();
		long runTime = System.currentTimeMillis() - startTime;
		logger.info(">>>>>>>>>>>>>>>>>>鏂规硶鍚嶏細{}>>end,杩斿洖鍊�:{},鑰楁椂{}",methodName,reqStr,runTime);
		return obj;
	}
	


}
