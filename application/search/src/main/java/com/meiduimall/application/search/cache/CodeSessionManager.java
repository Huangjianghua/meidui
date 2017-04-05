package com.meiduimall.application.search.cache;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeSessionManager {
	public static HashMap<String, Object> sessionCode = new HashMap<String, Object>();
	private static final Log log = LogFactory.getLog(CodeSessionManager.class);

	public static void put(String key,Object value) {
		try {
			sessionCode.put(key, value);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static HashMap<String, Object> getAll() {
		return sessionCode;
	}

	public static Object get(String key) {
		try {
			return  sessionCode.get(key);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public static void remove(String key) {
		sessionCode.remove(key);
	}

	public static boolean isRemove(String key) {
		if (sessionCode.get(key) == null) {
			return true;
		} else {
			return false;
		}
	}
}
