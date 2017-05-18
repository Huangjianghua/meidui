package com.meiduimall.application.mall.catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.meiduimall.application.mall.catalog.constant.MallConstant;

/**
 * 获取当前环境其他service的地址
 * 
 * @author yangchangfu
 *
 */
@Configuration
public class ProfileConfig {

	@Autowired
	private Environment env;

	public String getClientId() {
		return env.getProperty(MallConstant.KEY_SIGN_CLIENT_ID);
	}

	public String getSingKey() {
		return env.getProperty(MallConstant.KEY_SIGN_KEY);
	}

	public String getHost() {
		return env.getProperty(MallConstant.KEY_CATALOG_SERVICE_HOST);
	}
}
