package com.meiduimall.aspect.advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.meiduimall.FastJsonUtil;

@Aspect
public class MethodLogAdvice {
	
	private static Logger logger = LoggerFactory.getLogger(MethodLogAdvice.class);
	
	/**
	 * 功能描述:  定义切入点allMethod()的环绕通知,环绕通知方法一定要按照下面的形式定义(只可以修改方法名和参数名)
	 * Author: 陈建宇
	 * Date:   2017年1月18日 下午2:52:56   
	 * return  Object
	 */
	@Around("com.meiduimall.aspect.pointcut.MethodLogPointcut.pointcutLog()")
	public Object doSomethingAround(ProceedingJoinPoint point) throws Throwable {
		long startTime = System.currentTimeMillis();
		//拦截的方法名称
		String methodName = point.getSignature().getName();
		//拦截方法参数值
		Object[] args=point.getArgs();
		String reqStr=FastJsonUtil.serialize(args);
		logger.info(">>>>>>>>>>>>>>>>>>方法名：{}>>start,参数:{}",methodName,reqStr);
		//执行方法逻辑
		Object obj = point.proceed();
		long runTime = System.currentTimeMillis() - startTime;
		logger.info(">>>>>>>>>>>>>>>>>>方法名：{}>>end,返回值:{},耗时{}",methodName,reqStr,runTime);
		return obj;
	}
	
	
   /**
=======
    /**
>>>>>>> master
	 * 功能描述:  处理程序中未处理的异常
	 * Author: 陈建宇
	 * Date:   2017年1月18日 下午3:26:03   
	 * return  void
	 */
	/*@AfterThrowing(pointcut="com.meiduimall.aspect.pointcut.MethodLogPointcut.pointcutLog()",throwing="ex")
	public void doAfterThrowing(JoinPoint joinPoint,Throwable ex){
		//拦截的方法名称
		String methodName = joinPoint.getSignature().getName();
		//拦截方法参数值
		Object[] args=joinPoint.getArgs();
		String reqStr = FastJsonUtil.serialize(args);
		logger.error(">>>>>>>>>>>>>>>>>>方法名：{},参数:{},异常信息:{}",methodName,reqStr,ExceptionUtils.getFullStackTrace(ex));
	}*/

}
