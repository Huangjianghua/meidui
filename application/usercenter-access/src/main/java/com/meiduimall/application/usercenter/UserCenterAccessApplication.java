package com.meiduimall.application.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UserCenterAccessApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(UserCenterAccessApplication.class, args);
	}
}
