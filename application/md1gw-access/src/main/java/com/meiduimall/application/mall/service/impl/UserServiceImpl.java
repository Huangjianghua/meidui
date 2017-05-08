package com.meiduimall.application.mall.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.meiduimall.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.HttpRConst;
import com.meiduimall.application.mall.constant.OauthConst;
import com.meiduimall.application.mall.dao.BaseMapper;
import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.application.mall.model.SysuserAccount;
import com.meiduimall.application.mall.model.SysuserUser;
import com.meiduimall.application.mall.model.SysuserUserScore;
import com.meiduimall.application.mall.model.SysuserWalletPaylog;
import com.meiduimall.application.mall.service.UserService;
import com.meiduimall.application.mall.util.CommonUtil;
import com.meiduimall.application.mall.util.Logger;

import net.sf.json.JSONObject;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MyProps myProps;

	@Override
	public Map<String, Object> getUserInfo(SysuserUser sysuserUser) {
		return baseMapper.selectOne(sysuserUser, "SysuserUserMapper.getUserInfo");
	}

	@Override
	public SysuserUser getUserMoney(Integer userId) {
		return baseMapper.selectOne(userId, "SysuserUserMapper.getUserMoney");
	}

	@Override
	public Integer updateMF(SysuserUser sysuserUser) {
		return baseMapper.update(sysuserUser, "SysuserUserMapper.updateMF");

	}

	@Override
	public Integer updateUsersWalletPay(SysuserWalletPaylog sysuserWalletPaylog) {
		return baseMapper.update(sysuserWalletPaylog, "SysuserWalletPaylogMapper.updateUsersWalletPay");

	}

	@Override
	public SysuserAccount getSysuserAccount(Integer userId) {
		return baseMapper.selectOne(userId, "SysuserAccountMapper.getLoginAccount");
	}

	@Override
	public Integer insertSysuserUserScore(SysuserUserScore sysuserUserScore) {
		return baseMapper.insert(sysuserUserScore, "SysuserUserScoreMapper.insertSysuserUserScore");
	}

	@Override
	public JSONObject getMemberBasicInfo(String token) {
		JSONObject postForObject = new JSONObject();
		try {
			StringBuilder url = new StringBuilder(myProps.getUserCenterUrl());
			url.append("/member/front_user_center/v1/get_member_basic_info");
			url.append("?token={token}&clientID={clientID}&timestamp={timestamp}&sign={sign}");
			Map<String, String> map = new HashMap<>();
			map.put("token", token);
			Map<String, String> commonMap = CommonUtil.commonMap(map);
			Logger.info("组装发送数据 :%s", commonMap);
			postForObject = restTemplate.getForObject(url.toString(), JSONObject.class, commonMap);
			Logger.info("获取用户信息结果 :%s", postForObject);
		} catch (Exception e) {
			Logger.error("获取会员信息错误!: %s", e);
			throw new ServiceException(MallApiCode.GETMEMBER_ERROR, MallApiCode.getZhMsg(MallApiCode.GETMEMBER_ERROR));
		}
		return postForObject;
	}

	@Override
	public JSONObject validePayPwd(String memId, String payPwd) {
		JSONObject postForObject = new JSONObject();
		try {
			Logger.info("解密之后:%s", payPwd);
			StringBuilder url = new StringBuilder(myProps.getRouteServiceUrl());
			url.append("/member/account_service/v1/valide_pay_pwd");
			url.append("?memId={memId}");
			url.append("&pay_pwd=");
			url.append(payPwd);
			url.append("&clientID={clientID}&timestamp={timestamp}&sign={sign}");
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("memId", memId);
			hashMap.put("pay_pwd", payPwd);
			Map<String, String> commonMap = CommonUtil.commonMap(hashMap);
			Logger.info("验证支付密码组装发送数据 :%s", commonMap);
			postForObject = restTemplate.getForObject(url.toString(), JSONObject.class, commonMap);
			Logger.info("验证支付密码请求结果 :%s", postForObject);
		} catch (Exception e) {
			Logger.error("验证密码错误!: %s", e);
			throw new ServiceException(MallApiCode.VALIDEPAYPWD_ERROR,
					MallApiCode.getZhMsg(MallApiCode.VALIDEPAYPWD_ERROR));
		}
		return postForObject;

	}

	@Override
	public JSONObject tokenTOmemId(String token) {
		JSONObject getMemid = new JSONObject();
		try {
			StringBuilder url = new StringBuilder(myProps.getUserCenterUrl());
			url.append("/member/front_user_center/v1/get_memid_by_token");
			url.append("?token={token}&clientID={clientID}&timestamp={timestamp}&sign={sign}");
			Map<String, String> map = new HashMap<>();
			map.put(OauthConst.TOKEN, token);
			Map<String, String> commonMap = CommonUtil.commonMap(map);
			Logger.info("tokenTOmemId组装发送数据:%s", commonMap);
			getMemid = restTemplate.getForObject(url.toString(), JSONObject.class, commonMap);
			Logger.info("tokenTOmemId请求结果 :%s", getMemid);

		} catch (Exception e) {
			Logger.error("tokenTOmemId错误!: %s", e);
			throw new ServiceException(MallApiCode.TOKENTOMEMID_ERROR,
					MallApiCode.getZhMsg(MallApiCode.TOKENTOMEMID_ERROR));
		}
		return getMemid;

	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject freezeUnfreeze(PaymentTrade paymentTrade, Map<String, Object> paymentBill) {
		String string = "";
		try {
			StringBuilder url = new StringBuilder(myProps.getRouteServiceUrl());
			url.append("/member/account_service/v1/freeze_unfreeze");
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
			headers.setContentType(type);
			JSONObject json = new JSONObject();
			json.put("memId", paymentTrade.getMemId());
			json.put("order_source", "1gw");
			if (new BigDecimal(paymentBill.get("curMoney").toString()).compareTo(new BigDecimal(0)) < 1) {
				json.put("pay_type", 2);
				// 没有第三方支付
			} else {
				json.put("pay_type", 3);
			}
			json.put("status", 3);
			json.put("consume_money", new BigDecimal(paymentBill.get("walletPay").toString()));
			json.put("consume_points", Integer.valueOf(paymentBill.get("pointPay").toString()));
			JSONObject commonJSON = CommonUtil.commonJSON(json);
			Logger.info("freezeUnfreeze组装发送数据:%s", commonJSON);
			HttpEntity<JSONObject> formEntity = new HttpEntity<>(commonJSON, headers);
			string = restTemplate.postForObject(url.toString(), formEntity, String.class);
		} catch (Exception e) {
			Logger.error("冻结错误!: %s", e);
			throw new ServiceException(MallApiCode.FREEZEUNFREEZE_ERROR,
					MallApiCode.getZhMsg(MallApiCode.FREEZEUNFREEZE_ERROR));
		}
		return new JSONObject().fromObject(string);
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject getMemIdByUserId(Integer userId) {
		String forObject = "";
		try {
			StringBuilder url = new StringBuilder(myProps.getMeiduimallUrl());
			url.append("/openapi/user/getMemIdByUserId?user_id=");
			url.append(userId);
			Logger.info("getMemIdByUserId组装发送数据:%s", url);
			forObject = restTemplate.getForObject(url.toString(), String.class);
			Logger.info("getMemIdByUserId数据:%s", forObject);
		} catch (Exception e) {
			Logger.error("getMemIdByUserId错误!: %s", e);
			throw new ServiceException(MallApiCode.GETMEMIDBYUSERID_ERROR,
					MallApiCode.getZhMsg(MallApiCode.GETMEMIDBYUSERID_ERROR));
		}
		return new JSONObject().fromObject(forObject);
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject unfreezeDeduct(Map<String, Object> paymentBill, String memId) {
		String string = "";
		try {
			StringBuilder url = new StringBuilder(myProps.getRouteServiceUrl());
			url.append("/member/account_service/v1/unfreeze_deduct");
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
			headers.setContentType(type);
			JSONObject json = new JSONObject();
			json.put("memId", memId);
			json.put("order_source", "1gw");
			if (new BigDecimal(paymentBill.get("curMoney").toString()).compareTo(new BigDecimal(0)) < 1) {
				json.put("pay_type", 2);
				// 没有第三方支付
			} else {
				json.put("pay_type", 3);
			}
			json.put("consume_money", new BigDecimal(paymentBill.get("walletPay").toString()));
			json.put("consume_points", Integer.valueOf(paymentBill.get("pointPay").toString()));
			JSONObject commonJSON = CommonUtil.commonJSON(json);
			Logger.info("unfreezeDeduct组装发送数据:%s", commonJSON);
			HttpEntity<JSONObject> formEntity = new HttpEntity<>(commonJSON, headers);
			string = restTemplate.postForObject(url.toString(), formEntity, String.class);
			Logger.info("unfreezeDeduct结果：%s", string);
		} catch (Exception e) {
			Logger.error("解冻错误!: %s", e);
			throw new ServiceException(MallApiCode.UNFREEZEDEDUCT_ERROR,
					MallApiCode.getZhMsg(MallApiCode.UNFREEZEDEDUCT_ERROR));
		}
		return new JSONObject().fromObject(string);
	}

	@Override
	public JSONObject sendSmsMessage(String mobile, String tid) {
		JSONObject postForObject = null;
		try {
			StringBuilder url = new StringBuilder(myProps.getSendSmsUrl());
			url.append("/notify/short_msg_service/v1/send_common_sms_message");
			MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
			paramMap.add("templateId", "1GW_1003");
			paramMap.add("params", tid);
			paramMap.add("phones", mobile);
			Logger.info("sendSmsMessage组装发送数据:%s", paramMap);
			postForObject = restTemplate.postForObject(url.toString(), paramMap, JSONObject.class);
			Logger.info("sendSmsMessage结果:%s", postForObject);
		} catch (Exception e) {
			Logger.error("sendSmsMessage错误!:%s", e);
			throw new ServiceException(MallApiCode.SENDSMSMESSAGE_ERROR,
					MallApiCode.getZhMsg(MallApiCode.SENDSMSMESSAGE_ERROR));
		}
		return postForObject;
	}

}
