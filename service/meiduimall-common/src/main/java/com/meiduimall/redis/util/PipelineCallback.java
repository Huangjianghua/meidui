package com.meiduimall.redis.util;

import redis.clients.jedis.ShardedJedisPipeline;

public interface PipelineCallback {
	/**
	 * 功能描述:  管道操作
	 * Author: 陈建宇
	 * Date:   2016年12月14日 上午9:51:25
	 * @param pipeline    
	 * @throws
	 */
	public void invokePipeline(ShardedJedisPipeline pipeline);

}
