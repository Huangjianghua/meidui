package com.meiduimall.service.member;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.meiduimall.service.member.util.DESC;

@EnableHystrix
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableEurekaClient
public class MemberServiceApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args){
		System.out.println(DESC.encryption("1gw_18938884424"));
		SpringApplication.run(MemberServiceApplication.class, args);
	}
}

