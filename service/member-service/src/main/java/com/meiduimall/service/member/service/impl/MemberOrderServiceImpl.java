package com.meiduimall.service.member.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.member.config.ServiceUrlProfileConfig;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMemberAddresses;
import com.meiduimall.service.member.model.MSMemberMobileArea;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.MSMembersSet;
import com.meiduimall.service.member.model.MemberAddressesSet;
import com.meiduimall.service.member.model.MobileNumberInfo;
import com.meiduimall.service.member.model.request.MemberConsumeMessageDTO;
import com.meiduimall.service.member.model.request.RequestMobile;
import com.meiduimall.service.member.model.response.MemberMobileAreaDTO;
import com.meiduimall.service.member.model.response.ResponseMemberBasicInfo;
import com.meiduimall.service.member.service.MemberOrderService;
import com.meiduimall.service.member.service.PointsService;
import com.meiduimall.service.member.service.UserInfoService;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.DateUtil;
import com.meiduimall.service.member.util.DoubleCalculate;
import com.meiduimall.service.member.util.StringUtil;

/**
 * 会员订单接口实现类
 * @author wujun
 *
 */
@Service
public class MemberOrderServiceImpl implements MemberOrderService {
	
	private final static Logger logger=LoggerFactory.getLogger(MemberOrderServiceImpl.class);
	
	@Autowired
	BaseDao baseDao;
	
	@Autowired
	PointsService pointsService;
	
	@Autowired
	ServiceUrlProfileConfig serviceUrlProfileConfig;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	@Transactional
	public ResBodyData updateMemberOrder(MemberConsumeMessageDTO mmt, Double xfc) {
		
		return null;
	}

	 
	

 

	
}
