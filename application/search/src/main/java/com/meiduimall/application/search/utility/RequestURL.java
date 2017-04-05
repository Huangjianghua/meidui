package com.meiduimall.application.search.utility;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import net.sf.json.JSONObject;

public class RequestURL {

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
	 * @param param
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
