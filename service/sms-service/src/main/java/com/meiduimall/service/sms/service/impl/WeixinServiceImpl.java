package com.meiduimall.service.sms.service.impl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.config.ProfileConfig;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.constant.SysConstant;
import com.meiduimall.service.sms.entity.WXAccessToken;
import com.meiduimall.service.sms.service.WeixinService;

@Service
public class WeixinServiceImpl implements WeixinService {

	private static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

	@Autowired
	private ProfileConfig profileConfig;

	@Override
	public String getAccessToken() {
		// 先从redis缓存取
		String cache = RedisUtils.get(SysConstant.WEIXIN_ACCESS_TOKEN_KEY);
		if (!StringUtils.isBlank(cache)) {
			return cache;
		}

		// 缓存取不到再请求微信，并存入缓存
		String appId = profileConfig.getWeixinAppId();
		String appSecret = profileConfig.getWeixinAppSecret();
		String url = profileConfig.getWeixinUrl() + "/cgi-bin/token?grant_type=client_credential&appid=" + appId
				+ "&secret=" + appSecret;
		try {
			String result = HttpUtils.get(url);
			WXAccessToken bean = null;
			try {
				bean = JsonUtils.jsonToBean(result, WXAccessToken.class);
			} catch (Exception e) {
				logger.error("请求微信access_token数据解析异常: " + e);
				throw new ServiceException(SmsApiCode.REQUEST_ACCESS_TOKEN_EXCEPTION);
			}

			String accessToken = bean.getAccess_token();
			if (!StringUtils.isBlank(accessToken)) {
				RedisUtils.setex(SysConstant.WEIXIN_ACCESS_TOKEN_KEY, 7000, accessToken);
				return accessToken;
			} else {
				logger.error("请求微信access_token为空");
				throw new ServiceException(SmsApiCode.REQUEST_ACCESS_TOKEN_EXCEPTION);
			}
		} catch (IOException e) {
			logger.error("请求微信access_token异常: " + e);
			throw new ServiceException(SmsApiCode.REQUEST_ACCESS_TOKEN_EXCEPTION);
		}
	}

	@Override
	public String sendTemplateMessageOnPaySuccess() {

		String url = profileConfig.getWeixinUrl() + "/cgi-bin/message/template/send?access_token=" + getAccessToken();

		return null;
	}

	private String getPaySuccessTemplateJson(String openID) {

		String templateId1 = profileConfig.getWeixinTemplateId1();
		String downloadUrl = profileConfig.getAppDownloadUrl();

		ObjectNode rootNode = JsonUtils.getInstance().createObjectNode();
		rootNode.set("touser", new TextNode(openID));
		rootNode.set("template_id", new TextNode(templateId1));
		rootNode.set("url", new TextNode(downloadUrl));

		/*
		 * ObjectNode miniprogram = JsonUtils.getInstance().createObjectNode();
		 * miniprogram.set("appid", new TextNode(appId));
		 * miniprogram.set("pagepath", new TextNode("index?foo=bar"));
		 * rootNode.set("miniprogram", miniprogram);
		 */

		ObjectNode data = JsonUtils.getInstance().createObjectNode();

		String firstValue = "您在XX消费，为您获得XX个美兑积分和XX元的美兑商城优惠券";// 美兑积分个数，优惠券金额
		ObjectNode first = JsonUtils.getInstance().createObjectNode();
		first.set("value", new TextNode(firstValue));
		first.set("color", new TextNode("#000459"));
		data.set("first.DATA", first);

		String userName = "";
		ObjectNode keyword1 = JsonUtils.getInstance().createObjectNode();
		keyword1.set("value", new TextNode(userName));
		keyword1.set("color", new TextNode("#000459"));
		data.set("keyword1.DATA", keyword1);

		ObjectNode keyword2 = JsonUtils.getInstance().createObjectNode();
		keyword2.set("value", new TextNode("巧克力"));
		keyword2.set("color", new TextNode("#000459"));
		data.set("keyword2.DATA", keyword2);

		ObjectNode keyword3 = JsonUtils.getInstance().createObjectNode();
		keyword3.set("value", new TextNode("巧克力"));
		keyword3.set("color", new TextNode("#000459"));
		data.set("keyword3.DATA", keyword3);

		ObjectNode keyword4 = JsonUtils.getInstance().createObjectNode();
		keyword4.set("value", new TextNode("巧克力"));
		keyword4.set("color", new TextNode("#000459"));
		data.set("keyword4.DATA", keyword4);

		ObjectNode keyword5 = JsonUtils.getInstance().createObjectNode();
		keyword5.set("value", new TextNode("巧克力"));
		keyword5.set("color", new TextNode("#000459"));
		data.set("keyword5.DATA", keyword5);

		ObjectNode remark = JsonUtils.getInstance().createObjectNode();
		remark.set("value", new TextNode("巧克力"));
		remark.set("color", new TextNode("#FF0000"));
		data.set("remark.DATA", remark);

		rootNode.set("data", data);

		return rootNode.toString();
	}
}
