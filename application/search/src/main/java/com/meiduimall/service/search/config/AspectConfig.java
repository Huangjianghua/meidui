package com.meiduimall.service.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meiduimall.core.advice.MethodLogAdvice;

/**
 * aop配置
 * 
 * @author yangchangfu
 *
 */
@Configuration
public class AspectConfig {

	@Bean
	public MethodLogAdvice myAspect() {
		return new MethodLogAdvice();
	}
}
