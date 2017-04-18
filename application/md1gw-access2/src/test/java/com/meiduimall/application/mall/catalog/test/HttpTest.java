package com.meiduimall.application.mall.catalog.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.meiduimall.core.GateWayBuilder;
import com.meiduimall.core.GateWayRequest;

public class HttpTest {

	@Test
	public void testGet() throws Exception{
		String url = "http://192.168.4.240:9050/mall/catalog-service/v1/goodsRecommend/getFirstRecommend?type=1&clientID=mall_java&key=Test123";
		GateWayBuilder.newBuilder(new GateWayRequest("mall_java", "Test123")).build().doGet(url);
	}
	
	@Test
	public void testPost() throws Exception{
		String url = "http://192.168.4.240:9050/mall/catalog-service/v1/goodsRecommend/getFirstRecommend";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		String sendData = "type=1";
		GateWayBuilder.newBuilder(new GateWayRequest("mall_java", "Test123")).build().doPost(url, headers, sendData);
	}
}
