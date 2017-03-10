package com.meidui.sms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meidui.sms.request.SmsRequest;
import com.meidui.sms.service.ExampleService;
import com.meiduimall.ResBodyData;
import com.meiduimall.RespPackUtil;




public class ExampleController {
	
	@Autowired
	private ExampleService exampleService;
	
	/**
	 * 发送短信
	 * @param model
	 * @return
	 */
	@RequestMapping("/send_common_sms_message")
	public ResBodyData sendSmsMessage(SmsRequest req) {
		ResBodyData response=new ResBodyData();
		return RespPackUtil.execInvokeService(req, response,()->{
			return exampleService.send(req);
		});
	}

}
