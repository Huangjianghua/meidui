package com.meiduimall.application.usercenter.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.service.UserInfoService;
import com.meiduimall.application.usercenter.util.HttpGatewayUtils;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 会员账号信息业务逻辑接口{@link=UserInfoService}实现类
 * 
 * @author chencong
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData getmemberbasicinfo(JSONObject reqJson) throws MdSysException {
		String url = profile.getServiceMemberUrl() + "v1/get_member_basic_info";
		MD5Utils.updateSign(reqJson, profile.getRouteClientID(), profile.getRouteKey());
		logger.info("调用账号服务>>获取会员基本信息API>>URL:{}  Data:{}", url, reqJson.toString());
		try {
			String result = HttpUtils.get(url, reqJson);
			logger.info("调用账号服务>>获取会员基本信息API>>结果：{}", result);
			return JSON.parseObject(result, ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账号服务>>获取会员基本信息API>>异常:{}", e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
	}

	@Override
	public ResBodyData updateMemberBasicInfo(JSONObject reqJson) throws MdSysException {
		String url = profile.getServiceMemberUrl() + "v1/update_member_basic_info";
		try {
			Map<String, String> hearder = new HashMap<>();
			hearder.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

			Map<String, String> params = new HashMap<>();
			for (String key : reqJson.keySet()) {
				String value = reqJson.getString(key);
				params.put(key, value);
			}
			return HttpGatewayUtils.sendPost(url, profile.getRouteClientID(), profile.getRouteKey(), params, hearder);
		} catch (Exception e) {
			logger.error("调用member-service>>获取会员基本信息API>>异常:{}", e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
	}

}
