package com.meiduimall.service.settlement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.config.MyProps;
import com.meiduimall.service.settlement.model.SmsReqDTO;
import com.meiduimall.service.settlement.service.SmsService;

import net.sf.json.JSONObject;

@Service
public class SmsServiceImpl implements SmsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Autowired
	private MyProps myProps;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public boolean sendMessage(SmsReqDTO smsReqDTO) {
		boolean flag = false;
		String url = myProps.getSmsUrl() + myProps.getSmsSendMessage();
		
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("phones", smsReqDTO.getPhones());
		paramMap.add("templateId", smsReqDTO.getTemplateId());
		paramMap.add("params", smsReqDTO.getParams());
		paramMap.add("sysKey", ShareProfitUtil.SYS_KEY);
		
		JSONObject resultJson = restTemplate.postForObject(url, paramMap, JSONObject.class);

		if (resultJson != null) {
			// 判断返回是否成功,如果不成功则不理会
			if (Integer.valueOf((int) resultJson.get("status")) == 0) {
				flag = true;
			} else {
				logger.error("errcode:" + resultJson.get("status") + ";errmsg:" + resultJson.get("msg"));
			}
		}
		return flag;
	}

}
