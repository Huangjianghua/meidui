package com.meidui.sms.service;


import com.meidui.sms.entity.MessageChannel;
import com.meidui.sms.request.SmsRequest;

public interface ExampleService {
	
	MessageChannel send(SmsRequest req);

}
