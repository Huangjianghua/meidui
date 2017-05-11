package com.meiduimall.service.member.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.model.request.RequestGetValidateCode;
import com.meiduimall.service.member.service.SmsService;

/**
 * 短信相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class SmsV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(SmsV1Controller.class);
	
	@Autowired
	private  SmsService  smsService;
	
	/**获取短信验证码*/
	@GetMapping(value = "/get_validate_code")
	ResBodyData getValidateCode(@RequestBody @Valid RequestGetValidateCode model) {
		logger.info("收到获取短信验证码API请求：",model.toString());
		ResBodyData resBodyData=new ResBodyData();
		if(smsService.getValidateCode(model)){
			
		}
		else{
			
		}
		return resBodyData;
	}

}
