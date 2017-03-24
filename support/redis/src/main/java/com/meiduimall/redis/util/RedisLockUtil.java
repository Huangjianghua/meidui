package com.meiduimall.redis.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.meiduimall.core.Constants;
import com.meiduimall.exception.SystemException;




/**
 * 利用redis单进程单线程特性实现分布式锁
 * @author 陈建宇
 */
public class RedisLockUtil {
	
	/**加锁标志 **/
    public static final String LOCKED = "TRUE"; 
    /**锁的超时时间（秒），过期删除 **/
    public static final int EXPIRE = 60; 
    /**1毫秒对应的纳秒时间**/
	public static final long ONE_MILLI_NANOS = 1000000L; 
	/**默认超时时间（毫秒） **/
    public static final long DEFAULT_TIME_OUT = 2000; 
    /**随机数**/
    public static final Random random = new Random(); 
    
    public static final String SUCCESS = "ok"; 
    
    /**
     * 加锁 保证原子性
     * @param operateLogic 处理逻辑 
     * @param lockCacheKey 锁的cache key
     * @param timeOut 毫秒数    	 获取锁的超时时间 
     * @return 
     */
	public static <T> T executeSynchOperate(MainOperator<T> operator,
				String lockCacheKey,long milliTimeout){
		Boolean locked = false;
		long startNaros = System.nanoTime();
		long nanoTimeOut = milliTimeout*1000000L;
		T resultObj = null;
		try{
			while(System.nanoTime()-startNaros < nanoTimeOut){
				if(JedisUtil.getJedisInstance().execSetnxToCache(lockCacheKey, LOCKED) == 1){
					JedisUtil.getJedisInstance().execExpireToCache(lockCacheKey, EXPIRE);
					locked = true;
					break;
				}
				Thread.sleep(30, random.nextInt(500));
			}
			resultObj = operator.executeInvokeLogic(locked);
		}catch(InterruptedException ex){
			throw new SystemException(ex);
		}finally{
			if(locked){//获取锁后的释放
				releaseRedisLock(lockCacheKey);
			}else{//检查是否存在死锁
				checkIsDeadlock(lockCacheKey);
			}
		}
		return resultObj;
	}
	
	
	
	
	/**
	 * 操作本身实现的逻辑
	 * @param <T>
	 */
	public abstract interface MainOperator<T>{
		public abstract T executeInvokeLogic(boolean result);
	}
	
	/**
	 * 释放锁
	 * @param cacheKey
	 */
	private static void releaseRedisLock(final String cacheKey){
		JedisUtil.getJedisInstance().execDelToCache(cacheKey);
	}
	
	private static void checkIsDeadlock(String lockCacheKey){
		if(JedisUtil.getJedisInstance().execTtlFormCache(lockCacheKey).intValue() 
				== Constants.CONSTANT_INVALID_INT){
			JedisUtil.getJedisInstance().execExpireToCache(lockCacheKey, EXPIRE);
		}
	}
	
	/**
	 * 多参数锁定 原子性操作
	 * @param operator
	 * @param keyArray
	 * @return
	 */
	public static <T> T executeSyncOperate(MainOperator<T> operator,String[] keyArray){
		Arrays.sort(keyArray);//排序  防止死锁
		List<String> lockKeyList = new ArrayList<String>(keyArray.length);
		Boolean locked = true;
		T resultObj = null;
		try{
			for(String key : keyArray){
				if(SUCCESS.equalsIgnoreCase(JedisUtil.getJedisInstance().execSetnxToCache2(key,LOCKED,new Long(EXPIRE)))){
					lockKeyList.add(key);
				}else{
					locked = false;
					break;
				}
			}
			resultObj = operator.executeInvokeLogic(locked);
		}finally{
			for(String key : lockKeyList){
				JedisUtil.getJedisInstance().execDelToCache(key);
			}
		}
		return resultObj;
	}
}
