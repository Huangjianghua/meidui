package com.meidui.shortmsg.cache;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ManagerMemSession {
public static HashMap<String, Object> sessions = new HashMap<String, Object>();
	
	private static final Log log = LogFactory.getLog(ManagerMemSession.class);

	public static void put(String key,Object value) {
		try {
			sessions.put(key, value);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static HashMap<String, Object> getAll() {
		return sessions;
	}

	public static Object get(String key) {
		try {
			return  sessions.get(key);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public static void remove(String key) {
		sessions.remove(key);
	}

	public static boolean isRemove(String key) {
		if (sessions.get(key) == null) {
			return true;
		} else {
			return false;
		}
	}
}
