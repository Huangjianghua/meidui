package com.meiduimall.service.search.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SysInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// Do nothing because of X and Y.
	}

	public void contextInitialized(ServletContextEvent event) {
		/**
		 * 系统名称
		 */
		ServletContext context = event.getServletContext();
		// 全局上下文路径
		context.setAttribute("ctx", context.getContextPath());
	}
}
