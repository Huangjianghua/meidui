package com.meiduimall.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * HTTP处理相关工具类
 * @author chencong
 *
 */
public class HttpClientUtil {
	
	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "UTF-8";

	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(30000)
				.setSocketTimeout(15000).build();
		 PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	    // 将最大连接数增加到200
	    cm.setMaxTotal(500);
	    // 将每个路由基础的连接增加到20
	    cm.setDefaultMaxPerRoute(200);

	    httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();
		 
	}
	
	
	/**
	 * get请求
	 * @param url
	 * @param params map对象
	 * @param heads 头部
	 * @return
	 * @throws Exception 
	 */
	public static String doGet(String url, Map<String, String> params,
			Map<String, String> heads) throws Exception {
		return doGet(url, params, heads, CHARSET);
	}

	/**
	 * post请求
	 * @param url
	 * @param params map对象
	 * @param heads 头部
	 * @return
	 * @throws Exception 
	 */
	public static String doPost(String url,String jsonStr,
			Map<String, String> heads) throws Exception {
		return doPost(url, jsonStr, heads, CHARSET);
	}
	
	/**
	 * 将HTTP GET String转换为JsonObject
	 * @param request
	 * @return
	 */
	public static JSONObject readGetStringToJsonObject(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String,String[]> parameterMap = request.getParameterMap();
			Iterator<String> paIter = parameterMap.keySet().iterator();
			while (paIter.hasNext()) {
				String key = paIter.next().toString();
				String[] values = (String[]) parameterMap.get(key);
				jsonObject.put(key, values[0]);
			}
		} catch (Exception e) {
			Logger.error("解析GET请求生成JSONObject对象异常，URL:"+request.getRequestURL()+",Reason："+e.getMessage());
			throw e;
		}
		return jsonObject;
	}
	
	/**
	 * 将HTTP POST FORM形式请求转成jsonobject
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject readPostFormToJsonObject(HttpServletRequest request) throws Exception{
		JSONObject jsonObject = new JSONObject();
		InputStreamReader isr=null;
		try {
			
			Logger.info("请求参数集合："+request.getParameterNames());
			Logger.info("姓名："+request.getParameter("user_name"));
			Logger.info("请求内容长度："+request.getContentLength());
			Enumeration<String> parameterMap = request.getParameterNames();
			while (parameterMap.hasMoreElements()) {
				String key =parameterMap.nextElement();
				Logger.info("key："+key);
				String value= (String) request.getParameter(key);
				Logger.info("value："+value);
				jsonObject.put(key,value);
			}
					
			BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(request.getInputStream()));  
		/*	isr = new InputStreamReader(request.getInputStream(), "UTF-8");
			StringBuffer sb = new StringBuffer();
			int size = 0;
			char[] buf = new char[1024];
			while ((size = isr.read(buf)) != -1) {
				sb.append(buf, 0, size);
			}*/
			
			/*StringBuffer sb = new StringBuffer();
			String item=null;
			while((item=bufferedReader.readLine())!=null)
			{
				Logger.info("当前字符"+item);
				sb.append(item);
			}
			Logger.info("字符串"+sb.toString());
			String[] arr=sb.toString().split("&");
			for (String item2 : arr) {
				jsonObject.put(item2.split("=")[0], item2.split("=")[1]);
			}*/
			
		} catch (Exception e) {
			Logger.error("解析POST FORM请求生成JSONObject对象异常，URL:"+request.getRequestURL()+",Reason："+e.getMessage());
			throw e;
		}
		return jsonObject;
	}

	/**
	 * 将HTTP POST JSON转换为JsonObject
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public static JSONObject readStreamToJsonObject(HttpServletRequest request) throws IOException {
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(request.getInputStream(), "UTF-8");
			StringBuffer sb = new StringBuffer();
			int size = 0;
			char[] buf = new char[1024];
			while ((size = isr.read(buf)) != -1) {
				sb.append(buf, 0, size);
			}
			JSONObject jsonObject=JSONObject.parseObject(sb.toString());
			/*isr.close();*/
			return jsonObject;
		} catch (Exception e) {
			Logger.error("解析POST请求生成JSONObject对象异常:"+request.getRequestURL()+",Reason："+e.getMessage());
			throw e;
		} 
		/*finally {
			isr.close();
		}*/
	}
	

	/**
	 * 获取当前请求的IP地址
	 * @param request
	 * @return
	 */
	public  static String getIpAddr(HttpServletRequest request) {
		String ipAddress =null; 
		try {
			ipAddress=request.getHeader("x-forwarded-for");
			
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
				Logger.info("当前请求ip地址未过滤="+ipAddress);												// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return ipAddress;
	}
	
	
	private static String doGet(String url, Map<String, String> params,
			Map<String, String> heads, String charset) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(
						params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
				url += "?"
						+ EntityUtils.toString(new UrlEncodedFormEntity(pairs,
								charset));
			}
			HttpGet httpGet = new HttpGet(url);
			if (heads != null) {
				for (Entry e : heads.entrySet()) {
					httpGet.addHeader(e.getKey().toString(), e.getValue()
							.toString());
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			Logger.info("请求的响应码："+statusCode);
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :"
						+ statusCode);
			}
			response.getAllHeaders();
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static String doPost(String url, String jsonStr,
			Map<String, String> heads, String charset) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			if (heads != null) {
				for (Entry e : heads.entrySet()) {
					httpPost.addHeader(e.getKey().toString(), e.getValue()
							.toString());
				}
			}
			if (jsonStr != null) {
				StringEntity params = new StringEntity(jsonStr, charset);
				httpPost.setEntity(params);
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
			/*response.close();*/
			return result;
		} catch (Exception e) {
			throw e;
		}
	}
}
