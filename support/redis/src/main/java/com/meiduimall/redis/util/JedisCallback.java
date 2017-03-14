package com.meiduimall.redis.util;
import redis.clients.jedis.ShardedJedis;



public interface JedisCallback<T> {
	
	public T invoke(ShardedJedis jedis);

}
