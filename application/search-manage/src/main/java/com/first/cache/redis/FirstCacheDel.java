package com.first.cache.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstCacheDel {
	
	/**
	 * 需要删除缓存的名称 多个的情况使用逗号分开
	 * @return
	 */
	public String values();
}
