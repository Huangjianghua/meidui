package com.meiduimall.application.search.cache.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * 注解类
 * @author gjl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstCache {
	/**
	 * cache名称，运行为空，如果为空方法名默认作为name的名称
	 * @return
	 */
	public String name();
	
	/**
	 * 缓存的时间，允许为空，单位秒
	 * @return
	 */
	public int cacheTime() default 0;
	
	/**
	 * 对应缓存的key
	 * @return
	 */
	public String key() default "";
}
