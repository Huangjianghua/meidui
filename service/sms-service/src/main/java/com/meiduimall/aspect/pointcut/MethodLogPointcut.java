/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP配置
 * @author chencong
 *
 */
@Aspect
public class MethodLogPointcut {
	
	@Pointcut("execution(* com.meiduimall.service.*.controller.*.*(..))")
	public void pointcutLog() {
		
	}
}
