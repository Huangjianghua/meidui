package com.meiduimall.application.search.config;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.meiduimall.application.search.Listener.SysInitListener;
import com.meiduimall.application.search.filter.ServletFilter;
import com.meiduimall.application.search.servlet.GetValidateCodeServlet;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	
	@Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new GetValidateCodeServlet());
        registration.addUrlMappings("/servlet/getValidateCode");
        return registration;
    }

    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new ServletFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
    
    
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean registration = new ServletListenerRegistrationBean();
        registration.setListener(new SysInitListener());
        return registration;
	
    }

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/statics/").addResourceLocations("/statics/**");
    }
    
    
    
   public void configureViewResolvers(ViewResolverRegistry registry) {
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		registry.viewResolver(viewResolver);
 	}
   
	@Bean(value="products")
    public HttpSolrClient products(@Value("${search_server_url}") String url) {
        return new HttpSolrClient(url+"/products");
    }
	
	@Bean(value="cats")
    public HttpSolrClient cats(@Value("${search_server_url}") String url) {
        return new HttpSolrClient(url+"/cats");
    }
	
	@Bean(value="suggest")
    public HttpSolrClient suggest(@Value("${search_server_url}") String url) {
        return new HttpSolrClient(url+"/suggest");
    }
    
}
 