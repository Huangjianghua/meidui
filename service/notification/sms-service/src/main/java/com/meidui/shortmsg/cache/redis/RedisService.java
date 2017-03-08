package com.meidui.shortmsg.cache.redis;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.meidui.shortmsg.exception.CacheException;
import com.meidui.shortmsg.util.Time;


/**
 * Redis 缓存实现 
 * @author pc
 *
 */
@Component
public class RedisService implements Cache {

	@Autowired
	private StringRedisTemplate template;
	
	@Override
	public void increment(String key, long delta)  throws CacheException {
		try {
			ValueOperations<String, String> operations = template.opsForValue();
			operations.increment(key, delta);
		} catch (Exception e) {
			throw new CacheException(String.format("Increment K=%s,V=%s failed.", key, delta));
		}
	}
	
	@Override
	public void set(String key, String value) throws CacheException {
		try {
			ValueOperations<String, String> operations = template.opsForValue();
			operations.set(key, value);
		} catch (Exception e) {
			throw new CacheException(String.format("Set K=%s,V=%s failed.", key, value));
		}
	}

	/**
	 * @param  key 缓存的key  value key对应的值 
	 * @param expire缓存保存时长：格式:3h, 2mn, 7s or combination 2d4h10s, 1w2d3h10s
	 */
	@Override
	public void set(String key, String value, String expire) throws CacheException {
		this.set(key, value);
		template.expire(key, Time.parseDuration(expire), TimeUnit.SECONDS);
	}

	@Override
	public Object get(String key) throws CacheException {
		try {
			ValueOperations<String, String> operations = template.opsForValue();
			return operations.get(key);
		} catch (Exception e) {
			throw new CacheException(String.format("Can't get data for key %s", key));
		}
	}

	@Override
	public void del(String key) throws CacheException {
		try {
			template.delete(key);
		} catch (Exception e) {
			throw new CacheException(String.format("Can't delete data for key %s", key));
		}
	}

	@Override
	public void del(Collection<String> keys) throws CacheException {
		try {
			template.delete(keys);
		} catch (Exception e) {
			throw new CacheException(String.format("Can't delete data for keys [%s]", keys));
		}
	}

}
