package com.meiduimall.application.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@ComponentScan({ "com.meiduimall.application.mall" })
public class MallApplication extends SpringBootServletInitializer{    

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}
}
  