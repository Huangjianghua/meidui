package com.meiduimall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.constant.HttpRequstConst;
import com.meiduimall.service.ShortMessageService;

/**
 * 短信服务接口实现类
 * @author chencong
 *
 */
@Component
public class ShortMessageServiceImpl implements ShortMessageService{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public String getValidateCode(String url,String json) {
	String result=null;
       try {
    	   HttpHeaders headers = new HttpHeaders();
           MediaType type = MediaType.parseMediaType(HttpRequstConst.MEDIATYPE_JSON_FOR_APP);
           headers.setContentType(type);
           headers.add("Accept", MediaType.APPLICATION_JSON.toString());        
           HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
           result = restTemplate.postForObject(url, formEntity, String.class);
	} catch (Exception e) {
		throw e;
	}
		return result;
	}

	
}
