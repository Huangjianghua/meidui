package com.meiduimall.core.util;



import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A Http Request Tool Build-On HttpClient(4.x)
 * @author Yuanpeng H
 *
 */
public class HttpClientUtil {
	
	private static Logger Logger = LoggerFactory.getLogger(HttpClientUtil.class);
	/**
	 * Get few of Response Result By HttpPost 
	 * @param client
	 * @param request
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getFewResultByPost(CloseableHttpClient client,HttpPost request,String params) throws ClientProtocolException, IOException{
		if (params != null) {
			StringEntity entity = new StringEntity(params, "UTF-8");
			request.setEntity(entity);
		}
		CloseableHttpResponse response = null;
		try {
			response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if(code == 200 && response != null){
				HttpEntity entity = response.getEntity();
				if(entity != null){
					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
					String line;
					StringBuffer content = new StringBuffer(reader.readLine());
					while((line=reader.readLine())!=null){
						content.append(line);
					}
					return content.toString();
				}			
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return null;
	}
	
	/**
	 * Get few of Response Result By HttpPost 
	 * @param client
	 * @param request
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getFewResultByPost(CloseableHttpClient client,HttpPost request,Map<String, String> params) throws ClientProtocolException, IOException{
		if (params != null) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				parameters.add(new BasicNameValuePair(key, params.get(key)));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
			request.setEntity(formEntity);
		}
		CloseableHttpResponse response = null;
		try {
			response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if(code == 200 && response != null){
				HttpEntity entity = response.getEntity();
				if(entity != null){
					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
					String line;
					StringBuffer content = new StringBuffer(reader.readLine());
					while((line=reader.readLine())!=null){
						content.append(line);
					}
					return content.toString();
				}			
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return null;
	}
	


	/**
	 * 执行POST请求
	 * 
	 * @param url
	 * @param data
	 * @param headers
	 * @return
	 */
	public static String doPost(String url, StringEntity param, Map<String, String> headers) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		if (headers != null && headers.size() > 0) {
			for (String key : headers.keySet()) {
				httpPost.setHeader(key, headers.get(key));
			}
		}

		try {
			httpPost.setEntity(param);
			
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				return EntityUtils.toString(entity, "UTF-8");
			}

		} catch (Exception e) {
			Logger.error("Http::doPost error.");
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 执行Get请求
	 * 
	 * @param url
	 * @param headers
	 * @return
	 */
	public static String doGet(String url, HashMap<String, String> headers) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		if (headers != null && headers.size() > 0) {
			for (String key : headers.keySet()) {
				httpGet.setHeader(key, headers.get(key));
			}
		}
		try {
			CloseableHttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			Logger.error("Http::doPost error.");
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
