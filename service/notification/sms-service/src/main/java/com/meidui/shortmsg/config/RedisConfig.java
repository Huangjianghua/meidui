package com.meidui.shortmsg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.meidui.shortmsg.util.StringUtil;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Title : RedisConfig 
 * Description : redis配置
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:46:47 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
@Configuration
@EnableCaching
public class RedisConfig<T> {
	
	@Autowired
	private Environment env;

	@Bean(name = "jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.valueOf(env.getProperty("spring.redis.pool.max-active")));
		poolConfig.setMaxIdle(Integer.valueOf(env.getProperty("spring.redis.pool.max-idle")));
		poolConfig.setMinIdle(Integer.valueOf(env.getProperty("spring.redis.pool.min-idle")));
		poolConfig.setMaxWaitMillis(Integer.valueOf(env.getProperty("spring.redis.pool.max-wait")));
		poolConfig.setTestOnBorrow(Boolean.valueOf(env.getProperty("spring.redis.pool.testOnBorrow")));
		poolConfig.setTestOnReturn(Boolean.valueOf(env.getProperty("spring.redis.pool.testOnReturn")));
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(env.getProperty("spring.redis.host"));
		jedisConnectionFactory.setPort(Integer.valueOf(env.getProperty("spring.redis.port")));
		jedisConnectionFactory.setTimeout(Integer.valueOf(env.getProperty("spring.redis.timeout")));
		if (StringUtil.checkStr(env.getProperty("spring.redis.password"))) {
			jedisConnectionFactory.setPassword(env.getProperty("spring.redis.password"));
		}
		return jedisConnectionFactory;
	}

	@Bean(name = "redisTemplate")
	@ConditionalOnMissingBean(name = {"redisTemplate"})
	public RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, T> template = new RedisTemplate<String, T>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializable());
		return template;
	}
}
