package com.meiduimall.application.search;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan({"com.meiduimall.application.search"})
@EnableAsync
public class SearchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class,args); 
	}

}
