package com.meiduimall.redis.util;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.meiduimall.core.Constants;
import com.meiduimall.exception.RedisClientException;
import com.meiduimall.redis.util.spring.AppContextLauncher;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisTemplate {

	private static int RETRY_NUM = 5;

	private static RedisTemplate redisTemplate = new RedisTemplate();

	private ShardedJedisPool sharedJedisPool = null;

	private RedisTemplate() {
		sharedJedisPool = AppContextLauncher.getBean("shardedJedisPool", ShardedJedisPool.class);
	}

	public static RedisTemplate getJedisInstance() {
		return redisTemplate;
	}

	/**
	 * 功能描述: 获取ShardedJedis池资源
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:36:45
	 * @return ShardedJedis   
	 * @throws
	 */
	public ShardedJedis getJedis() {
		return sharedJedisPool.getResource();
	}

	/**
	 * 
	 * 功能描述: 返回ShardedJedis池资源
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:37:06
	 * @param  shardedJedis    
	 * @throws
	 */
	public void returnJedisResource(ShardedJedis shardedJedis) {
		if (shardedJedis == null) {
			return;
		}
		sharedJedisPool.returnResource(shardedJedis);
	}

	public void returnJedisBrokenResource(ShardedJedis shardedJedis) {
		if (shardedJedis == null) {
			return;
		}
		sharedJedisPool.returnBrokenResource(shardedJedis);
	}

	/**
	 * 功能描述: jedis管道批量操作 支持异常情况下的5次补偿操作
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:37:21
	 * @param  callback    
	 * @throws
	 */
	public void execJedisPipelineOperate(PipelineCallback callback) {
		for (int index = 1; index <= RETRY_NUM; index++) {
			try {
				invokePipeline(callback);
			} catch (RedisClientException ex) {
				if (index < RETRY_NUM) {
					continue;
				}
				throw ex;
			}
			break;
		}
	}

	/**
	 * 
	 * 功能描述: jedis操作 支持异常情况下的5次补偿操作
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:37:41
	 * @param  callback
	 * @throws
	 */
	public <T> T execJedisOperate(RedisCallback<T> callback) {
		T result = null;
		for (int index = 1; index <= RETRY_NUM; index++) {
			try {
				result = invokeJedis(callback);
			} catch (RedisClientException ex) {
				if (index < RETRY_NUM) {
					continue;
				}
				throw ex;
			}
			break;
		}
		return result;
	}

	
	/**
	 * 功能描述:  jedis管道操作核心方法
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:38:02
	 * @param  callback    
	 * @throws
	 */
	private void invokePipeline(PipelineCallback callback) {
		boolean isReturn = true;
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			ShardedJedisPipeline pipeline = shardedJedis.pipelined();
			callback.invokePipeline(pipeline);
			pipeline.sync();
		} catch (Exception ex) {
			if (ex instanceof JedisConnectionException) {
				isReturn = false;
				returnJedisBrokenResource(shardedJedis);
			}
			throw new RedisClientException(ex);
		} finally {
			if (isReturn) {
				returnJedisResource(shardedJedis);
			}
		}
	}

	
	
	/**
	 * 
	 * 功能描述: jedis操作核心方法
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:38:20
	 * @param  callback
	 * @return T   
	 * @throws
	 */
	private <T> T invokeJedis(RedisCallback<T> callback) {
		boolean isReturn = true;
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return callback.invoke(shardedJedis);
		} catch (Exception ex) {
			if (ex instanceof JedisConnectionException) {
				isReturn = false;
				returnJedisBrokenResource(shardedJedis);
			}
			throw new RedisClientException(ex);
		} finally {
			if (isReturn) {
				returnJedisResource(shardedJedis);
			}
		}
	}

	
	
	/**
	 * 功能描述: 得到从缓冲中自增num的值
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:38:45
	 * @param  cacheKey
	 * @param  num
	 * @return Long   
	 * @throws
	 */
	public Long execIncrByToCache(final String cacheKey, final int num) {
		Long pkVal = execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.incrBy(cacheKey, (long) num);
			}
		});
		return pkVal;
	}

	
	
	/**
	 * 功能描述: 得到从缓冲中自增1的值
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:38:58
	 * @param  cacheKey
	 * @return Long   
	 * @throws
	 */
	public Long execIncrToCache(final String cacheKey) {
		Long pkVal = execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.incr(cacheKey);
			}
		});
		return pkVal;
	}
	
	
	/**
	 * 功能描述:  得到从缓冲中自减1的值
	 * Author: 陈建宇
	 * Date:   2016年12月29日 下午2:36:16   
	 * return  Long
	 */
	public Long execDecrToCache(final String cacheKey) {
		Long pkVal = execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.decr(cacheKey);
			}
		});
		return pkVal;
	}

	/**
	 * 功能描述:  得到从缓冲中自减num的值
	 * Author: 陈建宇
	 * Date:   2016年12月29日 下午2:37:04   
	 * return  Long
	 */
	public Long execDecrByToCache(final String cacheKey, final int num) {
		Long pkVal = execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.decrBy(cacheKey, (long) num);
			}
		});
		return pkVal;
	}

	
	
	/**
	 * 功能描述: 删除缓存 
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:39:23
	 * @param  cacheKey
	 * @return boolean   
	 * @throws
	 */
	public boolean execDelToCache(final String cacheKey) {
		return execJedisOperate(new RedisCallback<Boolean>() {
			@Override
			public Boolean invoke(ShardedJedis jedis) {
				return jedis.del(cacheKey) == Constants.CONSTANT_ZERO_INT ? false : true;
			}
		});
	}

	/**
	 * 功能描述: 存入缓存值 LRU
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:39:38
	 * @param  cacheKey
	 * @param  value    
	 * @throws
	 */
	public void execSetToCache(final String cacheKey, final String value) {
		execJedisOperate(new RedisCallback<Void>() {
			@Override
			public Void invoke(ShardedJedis jedis) {
				jedis.set(cacheKey, value);
				return null;
			}
		});
	}

	/**
	 * 功能描述: 设置key value  seconds
	 * Author: 陈建宇
	 * Date:   2016年12月29日 下午2:34:46   
	 * return  void
	 */
	public void execSetexToCache(final String cacheKey, final int seconds, final String value) {
		execJedisOperate(new RedisCallback<Void>() {
			@Override
			public Void invoke(ShardedJedis jedis) {
				jedis.setex(cacheKey, seconds, value);
				return null;
			}
		});
	}

	/**
	 * 功能描述:  通过key获取value
	 * Author: 陈建宇
	 * Date:   2016年12月29日 下午2:34:19   
	 * return  String
	 */
	public String execGetFromCache(final String cacheKey) {
		return execJedisOperate(new RedisCallback<String>() {
			@Override
			public String invoke(ShardedJedis jedis) {
				return jedis.get(cacheKey);
			}
		});
	}

	/**
	 * 功能描述: 是否已经缓存
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:40:16
	 * @param  cacheKey
	 * @return Boolean   
	 * @throws
	 */
	public Boolean execExistsFromCache(final String cacheKey) {
		return execJedisOperate(new RedisCallback<Boolean>() {
			@Override
			public Boolean invoke(ShardedJedis jedis) {
				return jedis.exists(cacheKey);
			}
		});
	}

	/**
	 * 功能描述: 设置过期时间
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:40:29
	 * @param  cacheKey
	 * @param  seconds
	 * @return Long   
	 * @throws
	 */
	public Long execExpireToCache(final String cacheKey, final int seconds) {
		return execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.expire(cacheKey, seconds);
			}
		});
	}

	/**
	 * 功能描述:  SETNX actually means "SET if Not eXists"
	 * Author: 陈建宇
	 * Date:   2016年12月29日 下午2:37:49   
	 * return  Long
	 */
	public Long execSetnxToCache(final String cacheKey, final String value) {
		return execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.setnx(cacheKey, value);
			}
		});
	}
	
	

	

	/**
	 * 功能描述: 获取keys
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:40:45
	 * @param  cacheKey
	 * @return Set<String>   
	 * @throws
	 */
	public Set<String> execKeysFromCache(final String cacheKey) {
		return execJedisOperate(new RedisCallback<Set<String>>() {
			@Override
			public Set<String> invoke(ShardedJedis jedis) {
				return jedis.getShard(Constants.CONSTANT_ONE_STR).keys(cacheKey);
			}
		});
	}

	/**
	 * 功能描述:  获取key的过期时间
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:40:57
	 * @param  cacheKey
	 * @return Long   
	 * @throws
	 */
	public Long execTtlFormCache(final String cacheKey) {
		return execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.ttl(cacheKey);
			}
		});
	}

	/**
	 * 功能描述: hash操作 存放
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:41:12
	 * @param  cacheKey
	 * @param  hash
	 * @return String   
	 * @throws
	 */
	public String execHmsetToCache(final String cacheKey, final Map<String, String> hash) {
		return execJedisOperate(new RedisCallback<String>() {
			@Override
			public String invoke(ShardedJedis jedis) {
				return jedis.hmset(cacheKey, hash);
			}
		});
	}

	/**
	 * 功能描述: hash操作 读取
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:41:26
	 * @param  cacheKey
	 * @param  hashKey
	 * @return List<String>   
	 * @throws
	 */
	public List<String> execHmgetToCache(final String cacheKey, final String hashKey) {
		return execJedisOperate(new RedisCallback<List<String>>() {
			@Override		
			public List<String> invoke(ShardedJedis jedis) {
				return jedis.hmget(cacheKey, hashKey);
			}
		});
	}

	/**
	 * 功能描述: 返回哈希表 key 中给定域 field 的值。
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:41:40
	 * @param  cacheKey
	 * @param  field
	 * @return String   
	 * @throws
	 */
	public String execHgetToCache(final String cacheKey, final String field) {
		return execJedisOperate(new RedisCallback<String>() {
			@Override
			public String invoke(ShardedJedis jedis) {
				return jedis.hget(cacheKey, field);
			}
		});
	}

	/**
	 * 功能描述: 将哈希表 key中的域 field的值设为value
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:42:07
	 * @param  cacheKey
	 * @param  field
	 * @param  value
	 * @return Long   
	 * @throws
	 */
	public Long execHsetToCache(final String cacheKey, final String field, final String value) {
		return execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.hset(cacheKey, field, value);
			}
		});
	}

	/**
	 * 功能描述: 返回哈希表 key中,所有的域和值。
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:42:22
	 * @param  cacheKey
	 * @return Map<String,String>   
	 * @throws
	 */
	public Map<String, String> execHgetAllToCache(final String cacheKey) {
		return execJedisOperate(new RedisCallback<Map<String, String>>() {
			@Override
			public Map<String, String> invoke(ShardedJedis jedis) {
				return jedis.hgetAll(cacheKey);
			}
		});
	}

	/**
	 * 功能描述: 设置到什么时候过期
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:42:35
	 * @param  cacheKey
	 * @param  unixTime
	 * @return Long   
	 * @throws
	 */
	public Long execExpireAtTimeToCache(final String cacheKey, final Long unixTime) {
		return execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.expireAt(cacheKey, unixTime);
			}
		});
	}

	/**
	 * 功能描述: 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:42:48
	 * @param  cacheKey
	 * @param  score
	 * @param  member
	 * @return Long   
	 * @throws
	 */
	public Long execZaddToCache(final String cacheKey, final Double score, final String member) {
		return execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.zadd(cacheKey, score, member);
			}
		});
	}

	
	
	/**
	 * 功能描述: 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:43:02
	 * @param  cacheKey
	 * @param  members
	 * @return Long   
	 * @throws
	 */
	public Long execZremToCache(final String cacheKey, final String... members) {
		return execJedisOperate(new RedisCallback<Long>() {
			@Override
			public Long invoke(ShardedJedis jedis) {
				return jedis.zrem(cacheKey, members);
			}
		});
	}

	/**
	 * 功能描述: 返回有序集合key中,所有介于min<=score<=max中的成员,有序集的成员按照score值递增(从小到大)次序排列
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:43:18
	 * @param  cacheKey
	 * @param  min
	 * @param  max
	 * @return Set<String>   
	 * @throws
	 */
	public Set<String> execZrangeByScoreToCache(final String cacheKey, final double min, final double max) {
		return execJedisOperate(new RedisCallback<Set<String>>() {
			@Override
			public Set<String> invoke(ShardedJedis jedis) {
				return jedis.zrangeByScore(cacheKey, min, max);
			}
		});
	}

}
