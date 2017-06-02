package com.meiduimall.service.member.api;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.model.request.RequestCheckValidateCode;
import com.meiduimall.service.member.model.request.RequestGetValidateCode;
import com.meiduimall.service.member.service.SmsService;

/**
 * 短信相关API
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
	ResBodyData getValidateCode(@Valid RequestGetValidateCode model) throws ClientProtocolException, IOException {
		logger.info("收到获取短信验证码API请求：",model.toString());
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		smsService.getValidateCode(model);
		return resBodyData;
	}
	
	/**校验短信验证码*/
	@GetMapping(value = "/check_validate_code")
	ResBodyData checkValidateCode(@Valid RequestCheckValidateCode model) throws ClientProtocolException, IOException {
		logger.info("收到校验短信验证码API请求：",model.toString());
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		smsService.checkValidateCode(model);
		return resBodyData;
	}

}
