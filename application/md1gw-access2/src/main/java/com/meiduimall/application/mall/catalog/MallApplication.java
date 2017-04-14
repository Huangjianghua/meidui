package com.meiduimall.application.mall.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangchangfu
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@ComponentScan({ "com.meiduimall.application.mall" })
public class MallApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}
}
