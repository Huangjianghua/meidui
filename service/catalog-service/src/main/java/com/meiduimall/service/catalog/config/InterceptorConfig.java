package com.meiduimall.service.catalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meiduimall.service.catalog.interceptor.TokenInterceptor;

/**
 * 注册拦截器到上下文
 * 
 * @author 杨长福
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	// 注册拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
	}
}
