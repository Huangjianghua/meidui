package com.meiduimall.redis.util.exceptions;


public class RedisClientException extends RuntimeException{

	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = 1027452261936311766L;
	
	 /**
     * 构造异常对象
     * @param msg
     */
    public RedisClientException(String msg) {
        super(msg);
    }

    /**
     * RedisClientException
     * @param exception
     */
    public RedisClientException(Throwable exception) {
        super(exception);
    }

    /**
     * RedisClientException
     * @param mag
     * @param exception
     */
    public RedisClientException(String mag, Exception exception) {
        super(mag, exception);
    }

}
