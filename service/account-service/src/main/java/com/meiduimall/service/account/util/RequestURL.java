package com.meiduimall.service.account.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONObject;
public class RequestURL {

	private static class MyX509TrustManager implements X509TrustManager {

		//检查客户端证书
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		//检查服务器端证书
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		//返回受信任的X509证书组
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return new X509Certificate[]{};  
		}

	}
	
	private static class MyHostnameVerifier implements HostnameVerifier{

		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			// TODO Auto-generated method stub
			return true;
		}

	}
	
	/**
	 * 方法名: httpsRequest<br>
	 * 描述:  https请求远程调用<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-11-4
	 * @param requestUrl
	 * @param param
	 * @return
	 */
	public static String getHttpsServer(String requestUrl, JSONObject param) {
		HttpsURLConnection httpConn = null;
		InputStream inputStream = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			URL url = new URL(requestUrl);
			//打开Http链接
			httpConn = (HttpsURLConnection)url.openConnection();
			//模拟SSL证书
			SSLContext sc = SSLContext.getInstance("SSL");  
			sc.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());  
			httpConn.setSSLSocketFactory(sc.getSocketFactory());  
			httpConn.setHostnameVerifier(new MyHostnameVerifier()); 
			
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false); //不使用缓存
			httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpConn.setRequestMethod("POST");//设置请求方式
			
			if (null != param) {
				httpConn.getOutputStream().write(param.toString().getBytes());
				httpConn.getOutputStream().flush();
				httpConn.getOutputStream().close();
			}
			
			int rec = httpConn.getResponseCode();
			if (rec == 200) {
				inputStream = httpConn.getInputStream();
				int aReadbytes = 100;
				byte[] data = new byte[aReadbytes];
				baos = new ByteArrayOutputStream();
				while (true) {
					int length = inputStream.read(data, 0, data.length);
					if (length == -1) {
						break;
					}
					baos.write(data, 0, length);
				}
				inputStream.close();
				httpConn.disconnect();
				byte[] responseData = baos.toByteArray();
				// 返回的结果字符串
				result = new String(responseData, "UTF-8");
			}
			
		} catch (Exception e) {
			System.out.println("http请求异常!");
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != baos) {
					baos.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * @param url
	 *            String 远程地址
	 * @param param
	 *            JSONObject json参数
	 * @return String
	 * */
	public static String getDataFromServer(String url, JSONObject param) {
		InputStream inputStream = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			URL reqUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) reqUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			if (null != param) {
				connection.getOutputStream().write(param.toString().getBytes());
				connection.getOutputStream().flush();
				connection.getOutputStream().close();
			}

			int rec = connection.getResponseCode();
			if (rec == 200) {
				inputStream = connection.getInputStream();
				int aReadbytes = 100;
				byte[] data = new byte[aReadbytes];
				baos = new ByteArrayOutputStream();
				while (true) {
					int length = inputStream.read(data, 0, data.length);
					if (length == -1) {
						break;
					}
					baos.write(data, 0, length);
				}
				inputStream.close();
				connection.disconnect();

				byte[] responseData = baos.toByteArray();

				// 返回的结果字符串
				result = new String(responseData, "UTF-8");
			}
			connection.disconnect();
		} catch (ConnectException connectEx) {
			connectEx.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != baos) {
					baos.close();
				}

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @param url
	 *            String 远程地址
	 * @param jsonParams
	 *            jsonParams 字符串的json格式参数
	 * @return String
	 * */
	public static String getDataFromServer(String url, String jsonParams) {
		InputStream inputStream = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			URL reqUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) reqUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			if (null != jsonParams && jsonParams.length() != 0) {
				connection.getOutputStream().write(jsonParams.getBytes());
				connection.getOutputStream().flush();
				connection.getOutputStream().close();
			}

			int rec = connection.getResponseCode();
			if (rec == 200) {
				inputStream = connection.getInputStream();
				int aReadbytes = 100;
				byte[] data = new byte[aReadbytes];
				baos = new ByteArrayOutputStream();
				while (true) {
					int length = inputStream.read(data, 0, data.length);
					if (length == -1) {
						break;
					}
					baos.write(data, 0, length);
				}
				inputStream.close();
				connection.disconnect();

				byte[] responseData = baos.toByteArray();

				// 返回的结果字符串
				result = new String(responseData, "UTF-8");
			}
			connection.disconnect();
		} catch (ConnectException connectEx) {
			connectEx.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != baos) {
					baos.close();
				}

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return result;
	}

	/* 用于请求百度坐标转换 */
	public static String reqUrl(String url) {
		StringBuffer sb = new StringBuffer();
		try {
			URL requestUrl = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					requestUrl.openStream()));
			String str = "";
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String send(String method, String url) throws Exception {
		String result = null;
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			result = new String(readInStream(conn.getInputStream()), "utf-8");
		} catch (SocketTimeoutException e) {
			throw new SocketTimeoutException();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			conn.disconnect();
		}
		return result;
	}

	public static byte[] readInStream(InputStream in) throws Exception {
		ByteArrayOutputStream out = null;
		byte[] data;
		try {
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			data = out.toByteArray();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				throw new IOException(e);
			}
		}
		return data;
	}

}
