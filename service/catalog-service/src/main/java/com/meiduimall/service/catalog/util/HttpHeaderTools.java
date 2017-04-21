package com.meiduimall.service.catalog.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHeaderTools {

	private static Logger logger = LoggerFactory.getLogger(HttpHeaderTools.class);

	private static final String LOCAL_LOOPBACK_ADDRESS = "127.0.0.1";

	private static final String LOCAL_UNKNOW_ADDRESS = "0:0:0:0:0:0:0:1";

	private static final String UNKNOWN = "unknown";

	private HttpHeaderTools() {
	}

	/**
	 * 获取当前请求的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = "";
		try {
			ipAddress = request.getHeader("x-forwarded-for");

			if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (LOCAL_LOOPBACK_ADDRESS.equals(ipAddress) || LOCAL_UNKNOW_ADDRESS.equals(ipAddress)) {
					// 根据网卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
						ipAddress = inet.getHostAddress();
					} catch (UnknownHostException e) {
						logger.error("获取IP失败：" + e);
					}
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
				logger.info("当前请求ip地址未过滤=" + ipAddress); // = 15
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(',', 1));
			}
		} catch (Exception e) {
			logger.error("获取当前请求的IP地址异常: " + e);
		}
		return ipAddress;
	}
}
