package com.meiduimall.application.mall.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meiduimall.application.mall.catalog.interceptor.TokenInterceptor;

/**
 * 注册拦截器到上下文
 * 
 * @author 杨长福
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	// 注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public HandlerInterceptor getTokenInterceptor() {
		return new TokenInterceptor();
	}
}
