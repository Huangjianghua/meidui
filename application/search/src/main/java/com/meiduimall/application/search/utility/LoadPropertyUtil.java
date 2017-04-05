package com.meiduimall.application.search.utility;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class LoadPropertyUtil {

	/**
	 * Properties 读取公共方法
	 * 
	 * @param fileName
	 *            properties文件名称
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public static Map<String, String> getPropertyValues(String config)
			throws Exception {
		InputStream is = LoadPropertyUtil.class.getClassLoader().getResourceAsStream(config);  
		Map<String, String> map = new HashMap<String, String>();
		try {
			Properties pro = new Properties();
			pro.load(is);
			Iterator<Object> localIterator = pro.keySet().iterator();
			while (localIterator.hasNext()) {
				Object key = localIterator.next();
				map.put(key.toString(), pro.get(key).toString());
			}
		} catch (Exception ex) {
			throw new Exception("读取系统配置参数文件" + config + "错误", ex);
		} finally {
			if (is != null)
				is.close();
		}
		return map;
	}

	public static String getProperty(String config,String key) throws Exception{
		try {
			return getPropertyValues(config).get(key);
		} catch (Exception ex) {
			throw new Exception("读取系统配置参数文件" + config + "错误", ex);
		}
	}
}