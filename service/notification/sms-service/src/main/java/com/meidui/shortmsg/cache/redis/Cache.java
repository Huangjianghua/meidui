package com.meidui.shortmsg.cache.redis;

import java.util.Collection;

import com.meidui.shortmsg.exception.CacheException;

/**
 * 缓存接口
 * @author Nico.Jiang
 * @since 2016.11.24
 */
public interface Cache {
	
	/**
	 * 自增
	 * @param key
	 * @param delta
	 * @throws CacheException
	 */
	public void increment(String key, long delta)  throws CacheException;
	
	/**
	 * 写入缓存默认超时
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	void set(String key, String value) throws CacheException;
	
	/**
	 * 写入缓存自定超时
	 * @param key
	 * @param value
	 * @param expire
	 * @throws CacheException
	 */
	void set(String key, String value, String expire) throws CacheException;
	
	/**
	 * 获取缓存数据
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	Object get(String key) throws CacheException;
	
	/**
	 * 删除缓存数据
	 * @param key
	 * @throws CacheException
	 */
	void del(String key) throws CacheException;
	
	/**
	 * 批量删除缓存数据
	 * @param keys
	 * @throws CacheException
	 */
	void del(Collection<String> keys) throws CacheException;

}
