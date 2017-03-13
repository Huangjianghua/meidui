package com.meidui.sms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meidui.sms.request.SmsRequest;
import com.meidui.sms.service.ExampleService;
import com.meiduimall.ResBodyData;
import com.meiduimall.RespPackUtil;



/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ExampleController.java
 * Author:   陈建宇
 * Date:     2017年3月13日 上午9:42:26
 * Description: //模块目的、功能描述
 */
@RestController
@RequestMapping("notify/short_msg_service/v1")
public class ExampleController {
	
	@Autowired
	private ExampleService exampleService;
	
	/**
	 * 功能描述:  <br>
	 * Author: 陈建宇
	 * Date:   2017年3月10日 下午6:30:06   
	 * return  ResBodyData
	 */
	@RequestMapping("/send_common_sms_message")
	public ResBodyData sendSmsMessage(SmsRequest req) {
		ResBodyData response=new ResBodyData();
		return RespPackUtil.execInvokeService(req, response,()->{
			return exampleService.send(req);
		});
	}

}
