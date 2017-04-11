package com.meiduimall.application.search.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;




@SpringBootApplication
@ComponentScan({"com.meiduimall.application.search.manage"})
@EnableAsync
public class SearchManageApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SearchManageApplication.class,args); 
	}

}
