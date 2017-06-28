package com.meiduimall.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 日志切面
 * @author:   jianhua.huang 
 * @version:  2017年6月28日 下午9:57:51 0.1 
 * Description:
 */
@Aspect
public class MethodLogPointcut {
	 
	@Pointcut("execution(* com.meiduimall.service.account.api.*.*(..))")
	public void pointcutLog() {
		
	} 

}  


