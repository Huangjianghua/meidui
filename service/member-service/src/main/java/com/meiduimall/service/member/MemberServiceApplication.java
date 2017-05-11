package com.meiduimall.service.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@EnableHystrix
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableEurekaClient
public class MemberServiceApplication extends SpringBootServletInitializer{
	
    @Autowired  
    private RestTemplateBuilder builder;  
  
    @Bean  
    public RestTemplate restTemplate() { 
        return builder.build();
    } 
	
	public static void main(String[] args){
		SpringApplication.run(MemberServiceApplication.class, args);
	}
}

