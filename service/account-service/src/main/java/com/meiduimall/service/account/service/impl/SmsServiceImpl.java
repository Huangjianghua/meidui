/*package com.meiduimall.service.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.config.ServiceUrlProfileConfig;
import com.meiduimall.service.account.model.request.RequestCheckPhoneValidateCode;
import com.meiduimall.service.account.service.SmsService;


@Service
public class SmsServiceImpl implements SmsService {
	
	private final static Logger logger=LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Autowired  
	private RestTemplate restTemplate;
	
	@Autowired
	private ServiceUrlProfileConfig serviceUrlProfileConfig;

	@Override
	public boolean checkPhoneValidateCode(){
		String smsServiceUrl=serviceUrlProfileConfig.getSmsServiceUrl()+"/v1/new/send_sms_verification_code";
		
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HttpEntity<String> formEntity = new HttpEntity<String>(JsonUtils.beanToJson(model), headers);
        logger.info("请求短信服务 >>获取短信验证码: URL:{}  DATA:{}",smsServiceUrl,model.toString());
        String result = restTemplate.postForObject(smsServiceUrl,formEntity,String.class);
        logger.info("请求短信服务 >>获取短信验证码: RESULT:{}",result);
		return true;
	}

}*/