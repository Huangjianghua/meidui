package com.meiduimall.application.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * test
 * @author chencong
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@ComponentScan({"com.meiduimall"})
public class CatalogAccessApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(CatalogAccessApplication.class, args);
	}
}
