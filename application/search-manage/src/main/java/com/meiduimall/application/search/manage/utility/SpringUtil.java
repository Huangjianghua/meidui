package com.meiduimall.application.search.manage.utility;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringUtil {
	public static WebApplicationContext webApplication = ContextLoader
			.getCurrentWebApplicationContext();

}