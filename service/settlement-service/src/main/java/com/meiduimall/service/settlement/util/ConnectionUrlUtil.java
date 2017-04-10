package com.meiduimall.service.settlement.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ConnectionUrlUtil.java
 * Author:   不详
 * Date:     2016年12月26日 下午6:15:47
 * Description: HttpURLConnection工具类
 */
public class ConnectionUrlUtil {

	private static Logger log = LoggerFactory.getLogger(ConnectionUrlUtil.class);
	
	public static String sendPostSina(String url, String requestXml) {
		log.debug("sendPost xml:" + requestXml);
		String result = "";
		try {
			URL u0 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
			conn.setRequestMethod("POST");
			byte contentbyte[] = requestXml.getBytes();
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setRequestProperty("Content-Length", (new StringBuilder()).append(contentbyte.length).toString());
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bWriter.write(requestXml);
			bWriter.flush();
			bWriter.close();
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}

			in.close();
			result = new String(buffer.toString().getBytes("iso-8859-1"), "UTF-8");
		} catch (Exception e) {
			log.error(url + "  请求异常: " + e.getMessage(),e);
		}
		return result;
	}

	public static String sendPost(String url, String requestXml, Map<String, String> heads) {
		log.debug("sendPost Url:" + url);
		log.debug("sendPost xml:" + requestXml);
		String result = "";
		try {
			URL u0 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
			conn.setRequestMethod("POST");
			byte contentbyte[] = requestXml.getBytes();
			for (Map.Entry<String, String> header : heads.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}
			conn.setRequestProperty("Content-Type", String.format("application/x-www-form-urlencoded;charset=%s", "UTF-8"));
			conn.setRequestProperty("Content-Length", (new StringBuilder()).append(contentbyte.length).toString());
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream()));
			bWriter.write(requestXml);
			bWriter.flush();
			bWriter.close();
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}

			in.close();
			result = new String(buffer.toString().getBytes("iso-8859-1"),
					"UTF-8");
		} catch (Exception e) {
			log.error("请求异常: " + e.getMessage(), e);
		}
		return result;
	}

	public static String sendPost(String url, String requestXml) {
		log.debug("sendPost Url:" + url);
		log.debug("sendPost xml:" + requestXml);
		String result = "";
		try {
			URL u0 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
			conn.setRequestMethod("POST");
			byte contentbyte[] = requestXml.toString().getBytes();
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setRequestProperty("Content-Length", (new StringBuilder())
					.append(contentbyte.length).toString());
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream()));
			bWriter.write(requestXml.toString());
			bWriter.flush();
			bWriter.close();
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}

			in.close();
			result = new String(buffer.toString().getBytes("iso-8859-1"),
					"UTF-8");
		} catch (Exception e) {
			log.error("请求异常: url:"+url+" msg:" + e.getMessage(), e);
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	public static int getPostStatus(String url, String requestXml) {
		log.debug("sendPost Url:" + url);
		log.debug("sendPost xml:" + requestXml);
		String result="";
		int status=0;
		try {
			URL u0 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
			conn.setRequestMethod("POST");
			byte contentbyte[] = requestXml.getBytes();
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setRequestProperty("Content-Length", (new StringBuilder())
					.append(contentbyte.length).toString());
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream()));
			bWriter.write(requestXml);
			status = conn.getResponseCode();
			bWriter.flush();
			bWriter.close();
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}

			in.close();
			result = new String(buffer.toString().getBytes("iso-8859-1"),
					"UTF-8");
			
		} catch (Exception e) {
			log.error("请求异常: " + e.getMessage(), e);
		}
		return status;
	}

	public static String sendPostByContentType(String url, String data, String contentType) {
		log.debug("sendPost Url:" + url);
		log.debug("sendPost xml:" + data);
		String result = "";
		try {
			URL u0 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
			conn.setRequestMethod("POST");
			byte contentbyte[] = data.getBytes();
			conn.setRequestProperty("Content-Type", contentType);
			conn.setRequestProperty("Content-Length", (new StringBuilder())
					.append(contentbyte.length).toString());
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream()));
			bWriter.write(data);
			bWriter.flush();
			bWriter.close();
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}

			in.close();
			result = new String(buffer.toString().getBytes("iso-8859-1"),
					"UTF-8");
		} catch (Exception e) {
			log.error("请求异常: " + e.getMessage(), e);
		}
		return result;
	}
	
	public static String sendPostByContentType(String url, String data, Map<String, String> headers) {
		log.debug("sendPost Url:" + url);
		log.debug("sendPost xml:" + data);
		String result = "";
		try {
			URL u0 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
			conn.setRequestMethod("POST");
			byte contentbyte[] = data.getBytes();
			for ( Map.Entry<String, String> d : headers.entrySet()) {
				conn.setRequestProperty(d.getKey(), d.getValue());
			}
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Length", (new StringBuilder())
					.append(contentbyte.length).toString());
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream()));
			bWriter.write(data);
			bWriter.flush();
			bWriter.close();
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}

			in.close();
			result = new String(buffer.toString().getBytes("iso-8859-1"),
					"UTF-8");
		} catch (Exception e) {
			log.error("请求异常: " + e.getMessage(), e);
		}
		return result;
	}

	public static String fetchMo(String reqUrl, String param) {
		URL url = null;
		try {
			url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			StringBuffer params = new StringBuffer();

			params.append(param);
			byte[] bypes = params.toString().getBytes("utf-8");
			conn.getOutputStream().write(bypes);

			InputStream inStream = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inStream));
			StringBuffer res = new StringBuffer("");
			String line = "";
			while ((line = br.readLine()) != null) {
				res.append(line);
			}
			return res.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readContentFromPost(String url, String param)
			throws IOException {
		URL u0 = new URL(url);
		// HttpURLConnection con = (HttpURLConnection) u0.openConnection();
		URLConnection con = u0.openConnection();

		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestProperty("Content-type",
				"application/x-www-form-urlencoded");
		con.setConnectTimeout(30000);
		con.setReadTimeout(30000);

		OutputStream os = con.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(os, "utf-8");
		writer.write(param);
		writer.flush();
		// DataInputStream dis = new DataInputStream(con.getInputStream());
		InputStream in = con.getInputStream();

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i != -1;) {
			i = in.read();
			if (i != -1)
				buffer.append((char) i);
		}

		in.close();
		String result = new String(buffer.toString().getBytes("iso-8859-1"),
				"UTF-8");
		return result;
	}

	
	public static String sendWJPost(String url, String requestXml, String cookie) {
		String result = "";
		try {
			URL u0 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
			conn.setRequestMethod("POST");
			byte contentbyte[] = requestXml.getBytes();
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			conn.setRequestProperty("Content-Length", (new StringBuilder()).append(contentbyte.length).toString());
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.125 Safari/537.36");
			conn.setRequestProperty("Referer", "http://qpay.s-shell.com/pages/web/unicompay.jsp?status=0&data=%7B%22appname%22%3A%22%E8%B0%83%E6%95%99%E7%BE%8E%E8%87%80%22%2C%22cpname%22%3A%22%E6%B7%B1%E5%9C%B3%E5%B8%82%E6%8E%8C%E6%B8%B8%E4%BA%92%E5%8A%A8%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%22%2C%22goodsname%22%3A%2220%E6%A1%83%E5%AD%90%22%2C%22fee%22%3A%224%22%2C%22telnumber%22%3A%22400-601-0255%22%2C%22price%22%3A%224%22%2C%22amount%22%3A%221%22%2C%22cpid%22%3A%22946%22%2C%22strdate%22%3A%222016-05-19%22%2C%22mobile%22%3A%2213163943605%22%2C%22paymenttype%22%3A%2231%22%2C%22clienttype%22%3A%221%22%2C%22operatortype%22%3A%221%22%2C%22goodscode%22%3A%22031772000%22%2C%22chcode%22%3A%221%22%2C%22gameid%22%3A%224176%22%7D");
			if(cookie!=null){
				conn.setRequestProperty("Cookie", cookie);
			}
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bWriter.write(requestXml);
			bWriter.flush();
			bWriter.close();
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}
			in.close();
			result = new String(buffer.toString().getBytes("iso-8859-1"), "UTF-8");
		} catch (Exception e) {
			log.error("连接失败: " + e.getMessage(),e);
		}
		return result;
	}

	public static Map<String, String> readContentPost(String url, String param)
			throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		URL u0 = new URL(url);
		// HttpURLConnection con = (HttpURLConnection) u0.openConnection();
		URLConnection con = u0.openConnection();

		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestProperty("Content-type",
				"application/x-www-form-urlencoded");

		OutputStream os = con.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(os, "utf-8");
		writer.write(param);
		writer.flush();
		// DataInputStream dis = new DataInputStream(con.getInputStream());
		InputStream in = con.getInputStream();

		String sessionid = con.getHeaderField("sessionid");

		map.put("sessionid", sessionid);

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i != -1;) {
			i = in.read();
			if (i != -1)
				buffer.append((char) i);
		}

		in.close();
		String result = new String(buffer.toString().getBytes("iso-8859-1"),
				"UTF-8");
		map.put("result", result);
		return map;
	}
	

	/*public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		HttpURLConnection httpConn = null;
		InputStream input = null;
		BufferedReader bufferedReader = null;
		InputStreamReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			System.out.println("请求地址:" + requestUrl);
			System.out.println("请求方法:" + requestMethod);
			
			URL url = new URL(requestUrl);
			//打开Http链接
			httpConn = (HttpURLConnection)url.openConnection();
			
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false); //不使用缓存
			httpConn.setRequestMethod(requestMethod);//设置请求方式
			
			if (null != outputStr) {
				//获取输出工作流
				OutputStream output = httpConn.getOutputStream();
				//写入输出数据
				output.write(outputStr.getBytes("UTF-8"));
				//释放
				output.close();
			}
			
			//从输入流读取返回的内容
			input = httpConn.getInputStream();
			reader = new InputStreamReader(input, "UTF-8");
			bufferedReader = new BufferedReader(reader);
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			System.out.println("http请求异常!");
			e.printStackTrace();
		} finally {
			//释放
			try {
				if(bufferedReader!=null){
					bufferedReader.close();
				}
				if(reader!=null){
					reader.close();
				}
				if(input!=null){
					input.close();
				}
				
			} catch (IOException e) {
				System.out.println("关闭流异常!");
				e.printStackTrace();
			}
			httpConn.disconnect(); //关闭Http连接
		}
		//返回JSON
		return JSONObject.parseObject(buffer.toString());
	}*/
	
	/**
	 * Description : Http请求
	 * Created By : Fkx 
	 * Creation Time : 2016-6-15 上午10:29:25 
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		HttpURLConnection httpConn = null;
		InputStream input = null;
		BufferedReader bufferedReader = null;
		InputStreamReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			System.out.println("请求地址:" + requestUrl);
			System.out.println("请求方法:" + requestMethod);
			
			URL url = new URL(requestUrl);
			//打开Http链接
			httpConn = (HttpURLConnection)url.openConnection();
			
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false); //不使用缓存
			httpConn.setRequestMethod(requestMethod);//设置请求方式
			
			if (null != outputStr) {
				//获取输出工作流
				OutputStream output = httpConn.getOutputStream();
				//写入输出数据
				output.write(outputStr.getBytes("UTF-8"));
				//释放
				output.close();
			}
			
			//从输入流读取返回的内容
			input = httpConn.getInputStream();
			reader = new InputStreamReader(input, "UTF-8");
			bufferedReader = new BufferedReader(reader);
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			System.out.println("http请求异常!");
			e.printStackTrace();
		} finally {
			//释放
			try {
				if(bufferedReader!=null){
					bufferedReader.close();
				}
				if(reader!=null){
					reader.close();
				}
				if(input!=null){
					input.close();
				}
				
			} catch (IOException e) {
				System.out.println("关闭流异常!");
				e.printStackTrace();
			}
			httpConn.disconnect(); //关闭Http连接
		}
		//返回JSON
		//return JSONObject.parseObject(buffer.toString());
		return buffer.toString();
	}
	
	
	
	public static String httpRequestTest(String requestUrl, String requestMethod, String outputStr) {
		HttpURLConnection httpConn = null;
		InputStream input = null;
		BufferedReader bufferedReader = null;
		InputStreamReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			System.out.println("请求地址:" + requestUrl);
			System.out.println("请求方法:" + requestMethod);
			
			URL url = new URL(requestUrl);
			//打开Http链接
			httpConn = (HttpURLConnection)url.openConnection();
			
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false); //不使用缓存
			httpConn.setRequestMethod(requestMethod);//设置请求方式
			
			if (null != outputStr) {
				//获取输出工作流
				OutputStream output = httpConn.getOutputStream();
				//写入输出数据
				output.write(outputStr.getBytes("UTF-8"));
				//释放
				output.close();
			}
			
			//从输入流读取返回的内容
			input = httpConn.getInputStream();
			reader = new InputStreamReader(input, "UTF-8");
			bufferedReader = new BufferedReader(reader);
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			System.out.println("http请求异常!");
			e.printStackTrace();
		} finally {
			//释放
			try {
				if(bufferedReader!=null){
					bufferedReader.close();
				}
				if(reader!=null){
					reader.close();
				}
				if(input!=null){
					input.close();
				}
				
			} catch (IOException e) {
				System.out.println("关闭流异常!");
				e.printStackTrace();
			}
			httpConn.disconnect(); //关闭Http连接
		}
		//返回JSON
		//return JSONObject.parseObject(buffer.toString());
		return buffer.toString();
	}
}
