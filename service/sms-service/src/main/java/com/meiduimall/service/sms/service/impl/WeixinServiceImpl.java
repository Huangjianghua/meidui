package com.meiduimall.service.sms.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.config.ProfileConfig;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.constant.SysConstant;
import com.meiduimall.service.sms.entity.SendWeixinMsgHistory;
import com.meiduimall.service.sms.entity.WXAccessToken;
import com.meiduimall.service.sms.mapper.SendWeixinMsgHistoryMapper;
import com.meiduimall.service.sms.model.MemberOpenID;
import com.meiduimall.service.sms.model.WXTemplateMsgResult;
import com.meiduimall.service.sms.request.WXMsgOnPaySuccessRequest;
import com.meiduimall.service.sms.service.WeixinService;

@Service
public class WeixinServiceImpl implements WeixinService {

	private static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

	@Autowired
	private ProfileConfig profileConfig;

	@Autowired
	private SendWeixinMsgHistoryMapper sendWeixinMsgHistoryMapper;

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
	public ResBodyData sendTemplateMessageOnPaySuccess(WXMsgOnPaySuccessRequest model) {
		// 获取会员信息和openID
		getMemberInfo(model);

		// 组拼微信模板消息
		String json = getPaySuccessTemplateJson(model);

		String weixinUrl = profileConfig.getWeixinUrl() + "/cgi-bin/message/template/send?access_token="
				+ getAccessToken();
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=utf-8");
		WXTemplateMsgResult bean = null;
		try {
			String sendResult = HttpUtils.post(weixinUrl, json, headers);
			logger.info("发送微信模板消息结果： " + sendResult);
			bean = JsonUtils.jsonToBean(sendResult, WXTemplateMsgResult.class);
		} catch (Exception e) {
			logger.error("发送微信模板消息失败： " + e);
			throw new ServiceException(SmsApiCode.SEND_WEIXIN_TEMPLATE_MSG_FAIL);
		}
		if (bean == null || bean.getErrcode() != 0) {
			logger.error("发送微信模板消息失败！");
			throw new ServiceException(SmsApiCode.SEND_WEIXIN_TEMPLATE_MSG_FAIL);
		}

		// 保存发送记录
		SendWeixinMsgHistory record = new SendWeixinMsgHistory();
		record.setMemId(model.getMemId());
		record.setMemPhone(model.getPhone());
		record.setSendContent(json);
		record.setUserName(model.getUserName());
		record.setWxOpenId(model.getOpenID());
		sendWeixinMsgHistoryMapper.insertSelective(record);

		// 发送成功返回
		ResBodyData result = new ResBodyData();
		result.setStatus(SmsApiCode.SUCCESS);
		result.setMsg(SmsApiCode.getZhMsg(SmsApiCode.SMS_SEND_SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	/**
	 * 请求member-service会员信息和openID
	 * 
	 * @param model
	 *            封装参数
	 */
	private void getMemberInfo(WXMsgOnPaySuccessRequest model) {
		String memSerUrl = profileConfig.getMemberServiceHost() + "/member/member_service/v1/getOpenIDByPhone?phone="
				+ model.getPhone();
		String result = null;
		MemberOpenID bean = null;
		try {
			result = HttpUtils.get(memSerUrl);
			logger.info("根据手机号请求member-service会员信息: " + result);
		} catch (IOException e) {
			logger.error("根据手机号请求member-service会员信息失败: " + e);
			throw new ServiceException(SmsApiCode.CAN_NOT_REQUEST_MEMBER_INFO);
		}
		try {
			bean = JsonUtils.jsonToBean(result, MemberOpenID.class);
		} catch (Exception e) {
			logger.error("根据手机号请求member-service会员信息，json解析失败: " + e);
			throw new ServiceException(SmsApiCode.MEMBER_INFO_EXCEPTION);
		}
		if (bean == null || bean.getStatus() != 0 || bean.getData() == null) {
			throw new ServiceException(SmsApiCode.MEMBER_INFO_EXCEPTION, bean.getMsg());
		}
		model.setOpenID(bean.getData().getWxOpenId());
		model.setTotalPoint(bean.getData().getMemPoint());
		String userName = bean.getData().getMemNickName();
		if (StringUtils.isBlank(userName)) {
			userName = bean.getData().getMemLoginName();
		}
		model.setUserName(userName);
		model.setMemId(bean.getData().getMemId());
	}

	/**
	 * 组拼微信模板消息
	 * 
	 * @param model
	 *            封装参数
	 * @return 组拼后json数据
	 */
	private String getPaySuccessTemplateJson(WXMsgOnPaySuccessRequest model) {

		String templateId1 = profileConfig.getWeixinTemplateId1();
		String downloadUrl = profileConfig.getAppDownloadUrl();

		ObjectNode rootNode = JsonUtils.getInstance().createObjectNode();
		rootNode.set("touser", new TextNode(model.getOpenID()));
		rootNode.set("template_id", new TextNode(templateId1));
		rootNode.set("url", new TextNode(downloadUrl));

		/*
		 * ObjectNode miniprogram = JsonUtils.getInstance().createObjectNode();
		 * miniprogram.set("appid", new TextNode(appId));
		 * miniprogram.set("pagepath", new TextNode("index?foo=bar"));
		 * rootNode.set("miniprogram", miniprogram);
		 */

		ObjectNode data = JsonUtils.getInstance().createObjectNode();

		String msgContent = "您在" + model.getStoreName() + "消费，为您获得" + model.getAddPoint() + "个美兑积分";// 消费地点，美兑积分个数
		try {
			if (!StringUtils.isBlank(model.getCoupon()) || Double.parseDouble(model.getCoupon()) > 0) {
				msgContent = "您在" + model.getStoreName() + "消费，为您获得" + model.getAddPoint() + "个美兑积分和"
						+ model.getCoupon() + "元的美兑商城优惠券";// 消费地点，美兑积分个数，优惠券金额
			}
		} catch (NumberFormatException e) {
			logger.error("优惠券金额错误: " + e);
		}
		ObjectNode first = JsonUtils.getInstance().createObjectNode();
		first.set("value", new TextNode(msgContent));
		first.set("color", new TextNode("#000459"));
		data.set("first.DATA", first);

		// 用户昵称：先取mem_nick_name，取不到再取mem_login_name
		ObjectNode keyword1 = JsonUtils.getInstance().createObjectNode();
		keyword1.set("value", new TextNode(model.getUserName()));
		keyword1.set("color", new TextNode("#000459"));
		data.set("keyword1.DATA", keyword1);

		// 订单付款时间（精确到秒）
		String orderTime = DateFormatUtils.format(model.getOrderTime() * 1000, "yyyy-MM-dd HH:mm:ss");
		ObjectNode keyword2 = JsonUtils.getInstance().createObjectNode();
		keyword2.set("value", new TextNode(orderTime));
		keyword2.set("color", new TextNode("#000459"));
		data.set("keyword2.DATA", keyword2);

		// 积分变动“+XX积分
		String addPoint = "0";
		try {
			if (!StringUtils.isBlank(model.getAddPoint()) && Double.parseDouble(model.getAddPoint()) > 0) {
				addPoint = "+" + model.getAddPoint();
			}
		} catch (NumberFormatException e) {
			logger.error("积分错误: " + e);
		}
		ObjectNode keyword3 = JsonUtils.getInstance().createObjectNode();
		keyword3.set("value", new TextNode(addPoint + "积分"));
		keyword3.set("color", new TextNode("#000459"));
		data.set("keyword3.DATA", keyword3);

		// 积分余额
		ObjectNode keyword4 = JsonUtils.getInstance().createObjectNode();
		keyword4.set("value", new TextNode(model.getTotalPoint()));
		keyword4.set("color", new TextNode("#000459"));
		data.set("keyword4.DATA", keyword4);

		// 变动原因为“美兑商城附近消费奖励” --写死的
		ObjectNode keyword5 = JsonUtils.getInstance().createObjectNode();
		keyword5.set("value", new TextNode("美兑商城附近消费奖励"));
		keyword5.set("color", new TextNode("#000459"));
		data.set("keyword5.DATA", keyword5);

		// 备注：赠送优惠券XX元已经到达您的钱包，点击下载美兑商城APP
		// 备注：赠送的积分已经到达您的钱包，点击下载美兑商城APP
		String remarkContent = "赠送的积分已经到达您的钱包，点击下载美兑商城APP";
		try {
			if (!StringUtils.isBlank(model.getCoupon()) && Double.parseDouble(model.getCoupon()) > 0) {
				remarkContent = "赠送优惠券" + model.getCoupon() + "元已经到达您的钱包，点击下载美兑商城APP";
			}
		} catch (NumberFormatException e) {
			logger.error("优惠券金额错误: " + e);
		}
		ObjectNode remark = JsonUtils.getInstance().createObjectNode();
		remark.set("value", new TextNode(remarkContent));
		remark.set("color", new TextNode("#FF0000"));
		data.set("remark.DATA", remark);

		rootNode.set("data", data);

		return rootNode.toString();
	}
}
