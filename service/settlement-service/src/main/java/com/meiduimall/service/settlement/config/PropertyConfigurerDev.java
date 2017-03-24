package com.meiduimall.service.settlement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.meiduimall.core.util.ToolUtils;
import com.meiduimall.service.settlement.common.ShareProfitUtil;

@Component
@Profile("dev")
public class PropertyConfigurerDev implements IPropertyConfigurer {
	
	private static final Logger log = LoggerFactory.getLogger(PropertyConfigurerDev.class);

	@Override
	public void loadProperty() throws Exception {
		ShareProfitUtil.AUTHORIZED_MAP = ToolUtils.loadProperty("config/authorized-dev.properties");

	}

	public PropertyConfigurerDev() {
		super();
		try {
			this.loadProperty();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	

}
