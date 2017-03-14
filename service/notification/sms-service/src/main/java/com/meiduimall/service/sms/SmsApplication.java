package com.meiduimall.service.sms;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableEurekaClient
@MapperScan("com.meidui.shortmsg.mapper")
public class SmsApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
	}
}
