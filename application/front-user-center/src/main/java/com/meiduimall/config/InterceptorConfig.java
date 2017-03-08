package com.meiduimall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meiduimall.interceptor.ValInterceptor;

/**
 * 注册拦截器到上下文
 * @author chencong
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	
	//注册拦截器
	public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new ValInterceptor()).addPathPatterns("/**");
	}
}
