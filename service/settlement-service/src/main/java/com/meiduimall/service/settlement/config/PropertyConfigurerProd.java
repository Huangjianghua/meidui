package com.meiduimall.service.settlement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.meiduimall.core.util.ToolUtils;
import com.meiduimall.service.settlement.common.ShareProfitUtil;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: PropertyConfigurerProd.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 根据启动的ActiveProfile加载相应的authorized-{profile}.properties等配置文件
 */
@Component
@Profile("pro")
public class PropertyConfigurerProd implements IPropertyConfigurer {

	private static final Logger log = LoggerFactory.getLogger(PropertyConfigurerProd.class);

	@Override
	public void loadProperty() throws Exception {
		ShareProfitUtil.AUTHORIZED_MAP = ToolUtils.loadProperty("config/authorized-pro.properties");
	}

	public PropertyConfigurerProd() {
		super();
		try {
			this.loadProperty();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	

}