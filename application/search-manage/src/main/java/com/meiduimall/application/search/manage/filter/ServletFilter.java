package com.meiduimall.application.search.manage.filter;

import java.io.IOException;



import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.application.search.manage.constant.Constant;




public class ServletFilter implements Filter {
	public static final String AXIS_SERVICE_URI = "/ValidCode";
	public static final String URI_SPRIT = "/";
	public static final String URI_HOST = "/WebAPI/";
	public static final String URI_FACTOR = "?";
	public static final int WIDTH = 172;// 生成的图片的宽度
	public static final int HEIGHT = 50;// 生成的图片的高度
	
	private static Logger logger = LoggerFactory.getLogger(ServletFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("Access-Control-Allow-Origin", "*");
		chain.doFilter(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	public String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		
		if (ipAddress == null || ipAddress.length() == 0
				|| Constant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| Constant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| Constant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (Constant.LOCALHOST_IPV4.equals(ipAddress)
					|| Constant.LOCALHOST_IPV6.equals(ipAddress)) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
					ipAddress = inet.getHostAddress();
				} catch (UnknownHostException e) {
					logger.error("获取ip地址异常:{}",e);
				}
			
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			if (ipAddress.indexOf(',') > -1) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(','));
			}
		}
		return ipAddress;
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	@Override
	public void destroy() {
		
	}
	

}
