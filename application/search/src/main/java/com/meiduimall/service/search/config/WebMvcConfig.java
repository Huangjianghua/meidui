package com.meiduimall.service.search.config;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/statics/**").addResourceLocations("/statics/");
	}

	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		registry.viewResolver(viewResolver);
	}

	@Bean(value = "products")
	public HttpSolrClient products(@Value("${search_server_url}") String url) {
		return new HttpSolrClient(url + "/products");
	}

	@Bean(value = "cats")
	public HttpSolrClient cats(@Value("${search_server_url}") String url) {
		return new HttpSolrClient(url + "/cats");
	}

	@Bean(value = "suggest")
	public HttpSolrClient suggest(@Value("${search_server_url}") String url) {
		return new HttpSolrClient(url + "/suggest");
	}

}
