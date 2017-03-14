package com.meiduimall.service.sms.service;


import com.meiduimall.service.sms.entity.MessageChannel;
import com.meiduimall.service.sms.request.SmsRequest;

public interface ExampleService {
	
	
	/**
	 * 功能描述:  <br>
	 * Author: 陈建宇
	 * Date:   2017年3月10日 下午6:30:45   
	 * return  MessageChannel
	 */
	MessageChannel send(SmsRequest req);

}
