package com.meiduimall.application.mall.catalog.test;

import org.junit.Test;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;

public class JsonTest {

	@Test
	public void testBean1() {
		String json = "{\"status\":0,\"msg\":\"请求成功\",\"data\":{\"url\":\"http://shopdev.meiduimall.com//item.html?item_id=22\",\"item_id\":\"22\"}}";
		ResBodyData data = JsonUtils.jsonToBean(json, ResBodyData.class);
		
		System.out.println(data.getStatus());
		System.out.println(data.getMsg());
		System.out.println(data.getData());
		
		System.out.println("-----------------");
		
		String toJson = JsonUtils.beanToJson(data);
		System.out.println(toJson);
	}
	
	@Test
	public void testBean2() {
		String json = "{\"status\":7030,\"msg\":\"没有这个商品\",\"data\":{}}";
		ResBodyData data = JsonUtils.jsonToBean(json, ResBodyData.class);
		
		System.out.println(data.getStatus());
		System.out.println(data.getMsg());
		System.out.println(data.getData());
		
		System.out.println("-----------------");
		
		String toJson = JsonUtils.beanToJson(data);
		System.out.println(toJson);
	}
}
