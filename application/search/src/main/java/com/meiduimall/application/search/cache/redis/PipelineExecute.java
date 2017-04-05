package com.meiduimall.application.search.cache.redis;

import redis.clients.jedis.Client;
import redis.clients.jedis.Pipeline;

/**
 * @内容 
 * @FileName PipelineExecute.java
 * @author gjl
 * @创建日期：2014-3-6 上午11:12:29
 *
 */
public abstract class PipelineExecute extends Pipeline {
	public PipelineExecute() {
	}

	public abstract void execute() throws Exception;

	public void setClient(Client client) {
		super.setClient(client);
	}
}
