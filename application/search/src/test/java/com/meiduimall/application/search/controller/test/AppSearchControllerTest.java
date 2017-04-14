package com.meiduimall.application.search.controller.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.meiduimall.application.search.controller.AppSearchController;
import com.meiduimall.application.search.pojo.AppSearchParam;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: AppSearchControllerTest.java
 * @Author: jianhua.huang
 * @Date: 2017年4月14日 下午4:36:53
 * @Description: searchController 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppSearchControllerTest {

	@Autowired
	private AppSearchController appSearchController;

	private AppSearchParam appSearchParam = new AppSearchParam();

	/**
	 * @Description: 測試search方法
	 * @Author: jianhua.huang
	 * @Date: 2017年4月14日 下午4:46:38
	 * 
	 */
	@Test
	public void testSearch() throws Exception {
		appSearchParam.setKeyword("服装");
		ResBodyData resBodyData = appSearchController.search(appSearchParam);
		Assert.assertEquals(BaseApiCode.SUCCESS, resBodyData.getStatus());
	}
}
