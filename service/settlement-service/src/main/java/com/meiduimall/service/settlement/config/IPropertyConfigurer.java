package com.meiduimall.service.settlement.config;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: IPropertyConfigurer.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 配置文件加载接口,根据启动的ActiveProfile加载相应的authorized-dev.properties/authorized-test.properties/authorized-pro.properties等配置文件
 */
public interface IPropertyConfigurer {
	
	public void loadProperty();

}
