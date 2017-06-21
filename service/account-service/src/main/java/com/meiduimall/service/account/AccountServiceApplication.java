package com.meiduimall.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.util.DESC;


@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableDiscoveryClient
public class AccountServiceApplication extends SpringBootServletInitializer {

	    
    @Autowired  
    private RestTemplateBuilder builder;  
  
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() { 
        return builder.build();
    }  
	

	public static void main(String[] args) throws MdSysException {
		System.out.println(DESC.encryption("18938884424"));
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	   
}

