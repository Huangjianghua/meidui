package com.meiduimall.application.search.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtils {
	private static Properties p = new Properties();
	
	private static Logger log = LoggerFactory.getLogger(ConfigUtils.class);
	
	public void init() throws Exception {
		System.out.println("load config.properties info ......");
        // 读取config配置文件
        p.load(this.getClass().getClassLoader().getResourceAsStream(
                "config.properties"));
    }
	
	public static String getString(String key) {
		return p.getProperty(key).trim();
	}
	
	
	/**
	 * 根据配置文件名和配置键名查询配置值
	 * @param key
	 * @param propertiesFileName
	 * @return
	 * @author Liujun
	 * @date   2015-6-5
	 */
	public static String getValue(String key, String propertiesFileName) {
		try {
			p.load(new InputStreamReader(ConfigUtils.class.getClassLoader().getResourceAsStream(propertiesFileName), "UTF-8"));
		} catch (IOException e) {
			log.error("文件没有找到", e);
		}
		return p.getProperty(key).trim();
	}
}
