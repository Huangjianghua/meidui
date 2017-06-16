package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.meiduimall.application.mall.catalog.result.APPUserInfoResult;
import com.meiduimall.application.mall.catalog.result.MallInfoResult;
import com.meiduimall.application.mall.catalog.result.MemberInfoResult;
import com.meiduimall.application.mall.catalog.result.OtoInfoResult;
import com.meiduimall.application.mall.catalog.service.UserInfoService;
import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.MallApiCode;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.JsonUtils;
import com.netflix.servo.util.Strings;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

	@Autowired
	private MyProps myProps;

	@Override
	public ResBodyData getUserInfoForApp(String memId, String token) {

		APPUserInfoResult data = new APPUserInfoResult();

		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		Map<String, String> params = new HashMap<String, String>();
		params.put("memId", memId);

		MemberInfoResult memInfo = new MemberInfoResult();
		// 1、请求member-service获取会员基本信息
		String memberUrl = host + MallConstant.MEMBER_SERVCIE + "/get_member_simple_info";
		ResBodyData bodyData1 = HttpGatewayUtils.sendGet(memberUrl, clientID, signKey, params);
		if (bodyData1.getStatus() == 0 && bodyData1.getData() != null) {
			try {
				String json = JsonUtils.beanToJson(bodyData1.getData());
				JsonNode node = JsonUtils.getInstance().readTree(json);
				String loginName = node.get("login_name").textValue();
				memInfo.setLoginName(loginName == null ? "" : loginName);
				String nickName = node.get("nick_name").textValue();
				memInfo.setNickName(nickName == null ? "" : nickName);
				String sex = node.get("sex").textValue();
				memInfo.setSex(sex == null ? "" : sex);
				String picUrl = node.get("pic_url").textValue();
				memInfo.setPicUrl(picUrl == null ? "" : picUrl);
				String phone = node.get("phone").textValue();
				memInfo.setPhone(phone == null ? "" : phone);
				String email = node.get("email").textValue();
				memInfo.setEmail(email == null ? "" : email);
				String birthday = node.get("birthday").textValue();
				memInfo.setBirthday(birthday == null ? "" : birthday);
				String qrcodeName = phone;
				if(!Strings.isNullOrEmpty(qrcodeName)){
					qrcodeName = loginName;
				}
				memInfo.setQrcodeName(qrcodeName);
			} catch (Exception e) {
				logger.error("请求member-service，json数据解析异常：" + e);
			}
		} else {
			// 请求会员系统失败
			logger.error("请求member-service，返回数据异常！");
		}

		// 2、请求account-servcie获取会员积分余额信息
		String accountUrl = host + MallConstant.ACCOUNT_SERVCIE + "/getAccountBalanceForApp";
		ResBodyData bodyData2 = HttpGatewayUtils.sendGet(accountUrl, clientID, signKey, params);
		if (bodyData2.getStatus() == 0 && bodyData2.getData() != null) {
			try {
				String json = JsonUtils.beanToJson(bodyData2.getData());
				JsonNode node = JsonUtils.getInstance().readTree(json);
				String usePoints = node.get("use_points").textValue();
				memInfo.setUsePoints(usePoints == null ? "0" : usePoints);
				String allPoints = node.get("all_points").textValue();
				memInfo.setAllPoints(allPoints == null ? "0" : allPoints);
				String freezePoints = node.get("freeze_points").textValue();
				memInfo.setFreezePoints(freezePoints == null ? "0" : freezePoints);
				String useMoney = node.get("use_money").textValue();
				memInfo.setUseMoney(useMoney == null ? "0" : useMoney);
				String allMoney = node.get("all_money").textValue();
				memInfo.setAllMoney(allMoney == null ? "0" : allMoney);
				String freezeMoney = node.get("freeze_money").textValue();
				memInfo.setFreezeMoney(freezeMoney == null ? "0" : freezeMoney);
			} catch (Exception e) {
				logger.error("请求account-service，json数据解析异常：" + e);
			}
		} else {
			// 请求会员系统失败
			logger.error("请求account-service，返回数据异常！");
		}

		OtoInfoResult otoInfo = new OtoInfoResult();
		// 3、请求O2O数据
		String otoUrl = myProps.getOtoUrl() + "/appapi/index.php?app=member&act=getUserInfo&token=" + token;
		try {
			String otoResult = HttpUtils.get(otoUrl);
			JsonNode node = JsonUtils.getInstance().readTree(otoResult);
			int status = node.get("status").intValue();
			if (status == 1) {
				JsonNode dataNode = node.get("data");
				otoInfo.setOtoUserId(String.valueOf(dataNode.get("user_id").intValue()));
				otoInfo.setIsStore(dataNode.get("is_store").intValue());
				otoInfo.setIsAgent(dataNode.get("is_agent").intValue());
				otoInfo.setWaitSureOrder(dataNode.get("wait_sure_order").intValue());
				otoInfo.setAlreadySureOrder(dataNode.get("already_sure_order").intValue());
				otoInfo.setQrcode(dataNode.get("qrcode").textValue());
			} else {
				// 结果数据异常
				logger.error("请求O2O数据，请求结果状态异常：" + status);
			}
		} catch (Exception e) {
			logger.error("请求O2O数据异常：" + e);
		}

		MallInfoResult mallInfo = new MallInfoResult();
		// 4、请求商城数据
		String catalogUrl = host + MallConstant.SERVICE_CATALOG_BASE_URL + "/order_getEveryOrderTypeCount";
		ResBodyData bodyData4 = HttpGatewayUtils.sendGet(catalogUrl, clientID, signKey, params);
		if (bodyData4.getStatus() == 0 && bodyData4.getData() != null) {
			try {
				String json = JsonUtils.beanToJson(bodyData4.getData());
				JsonNode node = JsonUtils.getInstance().readTree(json);
				mallInfo.setCartNum(node.get("cart_num").intValue());
				mallInfo.setWaitPay(node.get("wait_pay").intValue());
				mallInfo.setWaitDelivery(node.get("wait_delivery").intValue());
				mallInfo.setWaitEnter(node.get("wait_enter").intValue());
				mallInfo.setWaitRate(node.get("wait_rate").intValue());
			} catch (Exception e) {
				logger.error("请求catalog-service，返回数据异常！");
			}
		} else {
			logger.error("请求catalog-service，返回数据异常！");
		}

		data.setMemInfo(memInfo);
		data.setOtoInfo(otoInfo);
		data.setMallInfo(mallInfo);

		ResBodyData result = new ResBodyData();
		result.setData(data);
		result.setStatus(MallApiCode.SUCCESS);
		result.setMsg(MallApiCode.getZhMsg(MallApiCode.SUCCESS));
		return result;
	}

}
