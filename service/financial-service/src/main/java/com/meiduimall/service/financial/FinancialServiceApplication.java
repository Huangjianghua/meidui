package com.meiduimall.service.financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author yangchangfu
 *
 */
@EnableHystrix
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@ComponentScan({ "com.meiduimall.service.financial" })
@EnableEurekaClient
public class FinancialServiceApplication extends SpringBootServletInitializer {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(FinancialServiceApplication.class, args);
	}
}
