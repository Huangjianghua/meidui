package com.meiduimall.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodLogAdvice {
	
	@Pointcut("execution(* com.meiduimall.application.md1gwaccess.api.*.*(..))")
	public void pointcutLog() {
		
	}
}
