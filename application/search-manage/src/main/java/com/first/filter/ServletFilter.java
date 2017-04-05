package com.first.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.first.utility.LoadPropertyUtil;

public class ServletFilter implements Filter {
	public static final String AXIS_SERVICE_URI = "/ValidCode";
	public static final String URI_SPRIT = "/";
	public static final String URI_HOST = "/WebAPI/";
	public static final String URI_FACTOR = "?";
	public static final int WIDTH = 172;// 生成的图片的宽度
	public static final int HEIGHT = 50;// 生成的图片的高度
	
	private static final Log log = LogFactory.getLog(ServletFilter.class);

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
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")
					|| ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			log.info("当前请求多IP="+ipAddress);												// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public Map<String, String> GetConfig() {
		Map<String, String> map = null;

		try {
			map = LoadPropertyUtil.getPropertyValues("config.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}

	/**
	 * Init lisenceKey
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		try {
//			OAuthProvider.loadConsumers(null);
//
//			String sql = " select mem_id,mem_email,mem_login_name,mem_phone,mem_nick_name,mem_license_key from ms_members where mem_license_key is not null and mem_license_key!='' and dict_mem_status='21B260C3-A230-4601-8D62-420OPT20AT44'";
//			List<Map<String, Object>> list = DBUtil.queryBySql(sql);
//
//			StringBuilder sb = null;
//
//			for (Map<String, Object> map : list) {
//
//				sb = new StringBuilder();
////				sb.append("MEMBERS;");
//				
//				sb.append(map.get("mem_id").toString() + ";");
//				sb.append(map.get("mem_email") == null ? "" : DESC.firstDeyption(map.get(
//						"mem_email").toString())
//						+ ";");
//				sb.append(map.get("mem_login_name") == null ? "" : DESC.firstDeyption(map.get(
//						"mem_login_name").toString())
//						+ ";");
//				sb.append(map.get("mem_phone") == null ? "" : DESC.firstDeyption(map.get(
//						"mem_phone").toString())
//						+ ";");
//				sb.append(map.get("mem_nick_name") == null ? "" : DESC.firstDeyption(map.get("mem_nick_name").toString()));
////				SessionManager.put(sb.toString(), map.get("mem_license_key")
////						.toString());
////				MemSession.put(sb.toString(), map.get("mem_license_key").toString());
//			}
//			
//			String sql1 = " select pf_id,pf_login_name,pf_phone,pf_nick_name,pf_license_key from ms_platforms where pf_license_key is not null and pf_license_key!='' and isLock='0'";
//			List<Map<String, Object>> list1 = DBUtil.queryBySql(sql1);
//
//			StringBuilder sb1 = null;
//
//			for (Map<String, Object> map1 : list1) {
//
//				sb1 = new StringBuilder();
////				sb1.append("MANAGER;");
//				sb1.append(map1.get("pf_id").toString() + ";");
//				sb1.append(map1.get("pf_login_name") == null ? "" : DESC.firstDeyption(map1.get(
//						"pf_login_name").toString())
//						+ ";");
//				sb1.append(map1.get("pf_phone") == null ? "" : DESC.firstDeyption(map1.get(
//						"pf_phone").toString())
//						+ ";");
//				sb1.append(map1.get("pf_email") == null ? "" : DESC.firstDeyption(map1.get(
//						"pf_email").toString())
//						+ ";");
//				sb1.append(map1.get("pf_nick_name") == null ? "" : DESC.firstDeyption(map1.get(
//						"pf_nick_name").toString())
//						+ ";");
////				ManagerMemSession.put(sb1.toString(), map1.get("pf_license_key").toString());
//
//			}
//					
//			
//			IBussinessConfigService bussinessConfigService = (IBussinessConfigService) SpringUtil.webApplication
//					.getBean("BussinessConfigService");
//			
//			
//			
//			
//			IDictService dictService = (IDictService) SpringUtil.webApplication
//					.getBean("DictService");
//			
//			SessionManager.put(ApplicationConstant.RANK_DICT, dictService.getRankConfigDict());
//			SessionManager.put(ApplicationConstant.INTEGRAL_DICT, dictService.getIntegralConfigDict());
//			SessionManager.put(ApplicationConstant.OPTGROUP_DICT, dictService.getAllOptGroupDict());
//			SessionManager.put(ApplicationConstant.RANK_CONFIG, bussinessConfigService.getRankConfig());
//			SessionManager.put(ApplicationConstant.INTEGRAL_CONGIF,bussinessConfigService.getIntegralConfig());
//			SessionManager.put(ApplicationConstant.CITY_DICT, dictService.getAllCity());
//			SessionManager.put(ApplicationConstant.BUSINESS_CONFIG, bussinessConfigService.getIntegralInfoConfig());
			
			//add by yangfei 20151116 begin
//			SettingTimer st = new SettingTimer();
//			new SettingTimer();
//			//add by yangfei 20151116 end
//			//add by gjl 2016-2-2 启动定时监测xfc价格
//			Runnable run = new XfcUpPriceThread();
//			Thread thread = new Thread(run);
//			thread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void destroy() {

	}
	

}
