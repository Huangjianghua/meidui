package com.meiduimall.application.search.manage.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpTooUtils {
	
	private static Logger logger = LoggerFactory.getLogger(HttpTooUtils.class);
	
	public static String sendGet(String url){
		StringBuilder bufferRes = new StringBuilder();
		try {
			URL url1 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			conn.connect();
			InputStream in = conn.getInputStream();

			BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));

			String valueString = null;

			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			read.close();
			in.close();
			if (conn != null) {
				conn.disconnect();
			}
		} catch (Exception e) {
			logger.error("get请求异常：{}",e);
		}
		return bufferRes.toString();

	}
	
	/**  
     * 向指定URL发送POST方法的请求  
     * @param url 发送请求的URL  
     * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。  
     * @return URL所代表远程资源的响应  
     */  
    public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        StringBuilder result = new StringBuilder();  
        try {  
            URL realUrl = new URL(url);  
            //打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            //设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            //发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            //获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            //发送请求参数  
            out.print(param);  
            //flush输出流的缓冲  
            out.flush();  
            //定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(  
                new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in .readLine()) != null) { 
            	result.append(line);
            }  
        } catch (Exception e) {  
        	logger.error("post请求异常：{}",e);
        }finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if ( in != null) { in .close();  
                }  
            } catch (IOException ex) {  
            	logger.error("post请求流关闭异常：{}",ex);
            }  
        }  
        return result.toString();  
    }  
}
