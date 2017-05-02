package com.meiduimall.service.member;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.meiduimall.exception.SystemException;



@EnableHystrix
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableEurekaClient
public class MemberServiceApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) throws SystemException{
		/*System.out.println(DESC.deyption("NgkARCV4j14=","79e642c2-86bb-4d89-86a7-02d6c81270e2"));*/
		SpringApplication.run(MemberServiceApplication.class, args);
	}
}
