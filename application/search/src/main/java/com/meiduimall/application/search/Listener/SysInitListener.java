package com.meiduimall.application.search.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SysInitListener  implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		
		/**
		 * 系统名称
		 */
		ServletContext  context =event.getServletContext();
//		String name = context.getInitParameter("sysname");
		
		//全局上下文路径
		context.setAttribute("ctx", context.getContextPath());
		
	}

}
