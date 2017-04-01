package com.meiduimall.test;

import org.junit.Test;

import com.meiduimall.util.HttpTooUtils;
import com.meiduimall.util.Logger;

public class DownloadStatisticsTest {
	
	String host = "http://127.0.0.1:10099";

	@Test
	public void testInsert1() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/insert";
		String params = "portal=3";
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}

	@Test
	public void testInsert2() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/insert";
		String params = "";
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}

	@Test
	public void testInsert3() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/insert";
		String params = "portal=aa";
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}

	@Test
	public void testQuery1() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/query";
		String beginDate = "2017-03-03";
		String endDate = "2017-03-09";
		String params = "beginDate=" + beginDate + "&endDate=" + endDate;
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}

	@Test
	public void testQuery2() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/query";
		String beginDate = "";
		String endDate = "";
		String params = "beginDate=" + beginDate + "&endDate=" + endDate;
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}

	@Test
	public void testQuery3() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/query";
		String beginDate = "";
		String endDate = "2017-03-09";
		String params = "beginDate=" + beginDate + "&endDate=" + endDate;
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}

	@Test
	public void testQuery4() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/query";
		String beginDate = "2017-03-03";
		String endDate = "";
		String params = "beginDate=" + beginDate + "&endDate=" + endDate;
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}
	
	@Test
	public void testQuery5() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/query";
		String beginDate = "2017-03-03aa";
		String endDate = "2017-03-09aa";
		String params = "beginDate=" + beginDate + "&endDate=" + endDate;
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}
	
	@Test
	public void testQuery6() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/query";
		String beginDate = "adfsaf";
		String endDate = "gadfasfd";
		String params = "beginDate=" + beginDate + "&endDate=" + endDate;
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}
	
	@Test
	public void testQuery7() throws Exception {
		String url = "/financial/financial-system-service/v1/statistics/query";
		String beginDate = "2017-03-22";
		String endDate = "2017-03-1";
		String params = "beginDate=" + beginDate + "&endDate=" + endDate;
		String result = HttpTooUtils.sendGet(host + url + "?" + params);
		Logger.info("请求结果地址：%s", host + url + "?" + params);
		Logger.info("请求结果：%s", result);
	}
}
