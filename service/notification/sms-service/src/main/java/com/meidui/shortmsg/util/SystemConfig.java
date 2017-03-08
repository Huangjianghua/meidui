package com.meidui.shortmsg.util;

import java.util.Map;

/**
 * 配置文件参数读取类
 * 
 */
public class SystemConfig {

	private static final SystemConfig systemConfig = new SystemConfig();
	public static Map<String, String> configMap;
	@SuppressWarnings("static-access")
	private SystemConfig() {
		if (configMap == null) {
			try {
				this.configMap = ToolsUtil.loadProperty("config.properties");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	public static SystemConfig getInstance() { 
		return systemConfig;
	}
	public static String get(String key) { 
			return systemConfig.configMap.get(key);
	}
}
