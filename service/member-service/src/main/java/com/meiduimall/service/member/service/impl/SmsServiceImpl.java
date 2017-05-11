package com.meiduimall.service.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.request.RequestGetValidateCode;
import com.meiduimall.service.member.service.SecurityService;
import com.meiduimall.service.member.service.SmsService;
import com.meiduimall.service.member.util.DESC;


@Service
public class SmsServiceImpl implements SmsService 
{
	private final static Logger logger=LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Override
	public boolean getValidateCode(RequestGetValidateCode model){
		
		return true;
	}

}
