package com.meiduimall.redis.util;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-common.xml"})
@FixMethodOrder(MethodSorters.DEFAULT)  
public class JedisUtilTest {
	
	
	
   @Before
   public void setUp(){

   }
	
	
	@Test
	public void testExecIncrByToCache(){
		Long value=JedisUtil.getJedisInstance().execIncrByToCache("testExecIncrByToCache", 1);
		Assert.assertNotNull(value);
	}
	
	
	
	@Test
	public void testExecIncrToCache(){
		Long value=JedisUtil.getJedisInstance().execIncrToCache("testExecIncrToCache");
		Assert.assertNotNull(value);
	}
	
	
	@Test
	public void testExecDecrToCache(){
		Long value=JedisUtil.getJedisInstance().execDecrToCache("testExecIncrToCache");
		Assert.assertNotNull(value);
	}
	
	
	@Test
	public void testExecDecrByToCache(){
		Long value=JedisUtil.getJedisInstance().execDecrByToCache("testExecIncrByToCache", 1);
		Assert.assertNotNull(value);
	}
	
	
	
	
	
	@Test
	public void testExecSetToCache(){
		Long value=JedisUtil.getJedisInstance().execSetnxToCache("testExecSetToCache", "testExecSetToCache");
		assertEquals(1,value.intValue());
	}
	
	@Test
	public void testExecDelToCache(){
		boolean res=JedisUtil.getJedisInstance().execDelToCache("testExecSetToCache");
		Assert.assertTrue(res);
	}
	
	
	
	@Test
	public void testExecSetexToCache(){
		JedisUtil.getJedisInstance().execSetexToCache("testExecSetexToCache", 60, "testExecSetexToCache");
	}
	
	@Test
	public void testExecGetFromCache(){
		String value=JedisUtil.getJedisInstance().execGetFromCache("testExecSetexToCache");
		Assert.assertNotNull(value);
	}
	
	@Test
	public void testExecExistsFromCache(){
		boolean res=JedisUtil.getJedisInstance().execExistsFromCache("testExecSetexToCache");
		Assert.assertTrue(res);
	}
	
	



}
