package com.meiduimall.application.search.manage.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.application.search.manage.constant.Constant;

import net.sf.json.JSONObject;


/**
 * Action基本方法
 * @author Liujun
 * @date 2016年4月25日
 */
public class BaseController {
	
	private Logger log = LoggerFactory.getLogger(BaseController.class);
	
	
	/**
	 * 获取当前网络ip
	 * 
	 * @param request
	 * @return
	 */
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
					log.error("获取ip地址异常:{}",e);
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
	
	public JSONObject getReqJSONBean(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try {
			Map parameterMap = request.getParameterMap();
			Iterator paIter = parameterMap.keySet().iterator();
			while (paIter.hasNext()) {
				String key = paIter.next().toString();
				String[] values = (String[]) parameterMap.get(key);
				jsonObject.accumulate(key, values[0]);
			}
		} catch (Exception e) {
			log.error("解析封装json异常:{}",e);
			return null;
		}

		return jsonObject;
	}
	
}
