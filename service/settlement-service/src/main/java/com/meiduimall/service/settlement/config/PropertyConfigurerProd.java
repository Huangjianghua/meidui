package com.meiduimall.service.settlement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.util.ToolUtils;

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
