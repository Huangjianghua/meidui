package com.meidui.shortmsg.cache.redis;

import redis.clients.jedis.Client;
import redis.clients.jedis.Transaction;

/**
 * @内容 
 * @FileName RedisTransactionExecute.java
 * @author gjl
 * @创建日期：2014-3-6 上午10:54:05
 *
 */
public abstract class RedisTransactionExecute extends Transaction{
	
	public RedisTransactionExecute(Client client) {
		super(client);
	}

	public RedisTransactionExecute() {
	}

	public abstract Object execute() throws Exception;

	public void setClient(Client client) {
		this.client = client;
	}
}

