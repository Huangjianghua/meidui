package com.meiduimall.service.settlement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.meiduimall.service.settlement.common.ShareProfitUtil;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: PropertyConfigurerTest.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 根据启动的ActiveProfile加载相应的authorized-{profile}.properties等配置文件
 */
@Component
@Profile("test")
public class PropertyConfigurerTest implements IPropertyConfigurer {
	
	private static final Logger log = LoggerFactory.getLogger(PropertyConfigurerTest.class);
	
	@Override
	public void loadProperty()  {
		ShareProfitUtil.AUTHORIZED_MAP = ShareProfitUtil.loadProperty("config/authorized-test.properties");

	}

	public PropertyConfigurerTest() {
		super();
		try {
			this.loadProperty();
		} catch (Exception e) {
			log.error("加载测试环境配置文件异常：{}", e);
		}
	}
	
	

}
