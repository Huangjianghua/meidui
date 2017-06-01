package com.meiduimall.service.member.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.service.member.service.AccountInfoService;

/**
 * 账户信息操作接口{@link=AccountInfoService}实现类
 * @author chencong
 *
 */
@Service
public class AccountInfoServiceImpl  implements AccountInfoService{
	
	private final static Logger logger=LoggerFactory.getLogger(AccountInfoServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	public String getTotalBalance(String memId)
	{
		
		return null;
	}
	
}
