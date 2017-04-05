package com.meiduimall.application.search.cache;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * IP请求太过频繁缓存队列
 * 30秒
 * @author 刘庆
 *
 * @param <K>
 * @param <V>
 */
public class CacheIPRequestMoreManager <K, V> extends AbstractMap<K, V>{
	private static final long DEFAULT_TIMEOUT = 10000;//10秒
	private static CacheIPRequestMoreManager<Object, Object> defaultInstance;
	private static final Log log = LogFactory.getLog(CacheIPRequestMoreManager.class);

	public static synchronized final CacheIPRequestMoreManager<Object, Object> getDefault() {
		if (defaultInstance == null) {
			defaultInstance = new CacheIPRequestMoreManager<Object, Object>(DEFAULT_TIMEOUT);
		}
		return defaultInstance;
	}

	private class CacheEntry implements Entry<K, V> {
		long time;
		V value;
		K key;

		CacheEntry(K key, V value) {
			super();
			this.value = value;
			this.key = key;
			this.time = System.currentTimeMillis();
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			return this.value = value;
		}
	}

	private class ClearThread extends Thread {
		ClearThread() {
			setName("clear cache thread");
		}

		public void run() {
			while (true) {
				try {
					long now = System.currentTimeMillis();
					Object[] keys = map.keySet().toArray();
					for (Object key : keys) {
						CacheEntry entry = map.get(key);
						if (now - entry.time >= cacheTimeout) {
							synchronized (map) {
								map.remove(key);
								log.info("定时清理清理被封IPkey=" + key + "---time:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
							}
						}
					}
					Thread.sleep(cacheTimeout);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private long cacheTimeout;
	private Map<K, CacheEntry> map = new HashMap<K, CacheEntry>();

	public CacheIPRequestMoreManager(long timeout) {
		this.cacheTimeout = timeout;
		new ClearThread().start();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> entrySet = new HashSet<Map.Entry<K, V>>();
		Set<Entry<K, CacheEntry>> wrapEntrySet = map.entrySet();
		for (Entry<K, CacheEntry> entry : wrapEntrySet) {
			entrySet.add(entry.getValue());
		}
		return entrySet;
	}

	@Override
	public V get(Object key) {
		CacheEntry entry = map.get(key);
		return entry == null ? null : entry.value;
	}

	@Override
	public V put(K key, V value) {
		CacheEntry entry = new CacheEntry(key, value);
		synchronized (map) {
			map.put(key, entry);
		}
		return value;
	}

	public V remove(Object keyss) {
		// Object[] keys = map.keySet().toArray();
		// for (Object key : keys) {
		// if (keyss.equals(key)) {
		// synchronized (map) {
		map.remove(keyss);
		log.info("手动清理清理key=" + keyss + "---time:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
		// }
		// }
		// }
		return null;
	}
}
