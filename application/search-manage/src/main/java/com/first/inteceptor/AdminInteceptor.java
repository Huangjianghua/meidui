package com.first.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.first.constant.SysConstant;
import com.first.system.domain.User;

public class AdminInteceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 //获取请求地址
		String url  = request.getRequestURI();
	      //判断请求地址要不要过滤(后期配置要过滤的地址配置文件中)
	    if(url.contains("/user/login")){
	    	//放行登入页面
			return true ;
	    }else{
	    	//判断是否登入
			  HttpSession   session =request.getSession();
			  User user = (User) session.getAttribute(SysConstant.USER_SESSSION_INFO);
			  if(user !=null){
				  //登入 或者是接口调用的  放行 
				  return true ;
			  }
	    }
		  //身份不存在 跳转到登入页面
		  response.sendRedirect(request.getContextPath()+"/index.jsp");
		  return false ;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
