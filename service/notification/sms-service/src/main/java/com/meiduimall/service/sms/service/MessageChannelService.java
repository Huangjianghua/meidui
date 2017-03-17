package com.meiduimall.service.sms.service;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: MessageChannelService.java
 * Author:   Administrator
 * Date:     2017年3月15日 下午12:18:12
 * Description: //模块目的、功能描述
 */
public interface MessageChannelService {
	/**
	 * 功能描述:  获取短信渠道并转成json字符串
	 * Author: 陈建宇
	 * Date:   2017年3月15日 下午12:18:28   
	 * return  String
	 */
	public String getChannelList(String key);
	/**
	 * 功能描述:  获取短信模板并转成json字符串
	 * Author: 陈建宇
	 * Date:   2017年3月15日 下午12:18:45   
	 * return  String
	 */
	public String getTemplateList(String key);

}
