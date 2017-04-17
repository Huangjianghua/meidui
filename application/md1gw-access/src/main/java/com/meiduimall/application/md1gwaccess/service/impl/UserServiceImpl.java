package com.meiduimall.application.md1gwaccess.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.meiduimall.application.md1gwaccess.config.MyProps;
import com.meiduimall.application.md1gwaccess.constant.HttpRConst;
import com.meiduimall.application.md1gwaccess.constant.OauthConst;
import com.meiduimall.application.md1gwaccess.dao.BaseMapper;
import com.meiduimall.application.md1gwaccess.model.PaymentTrade;
import com.meiduimall.application.md1gwaccess.model.SysuserAccount;
import com.meiduimall.application.md1gwaccess.model.SysuserUser;
import com.meiduimall.application.md1gwaccess.model.SysuserUserScore;
import com.meiduimall.application.md1gwaccess.model.SysuserWalletPaylog;
import com.meiduimall.application.md1gwaccess.service.UserService;
import com.meiduimall.application.md1gwaccess.util.CommonUtil;
import com.meiduimall.application.md1gwaccess.util.Logger;

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
	public Map<String, Object> getUserInfo(SysuserUser sysuserUser) throws Exception {
		Map<String, Object> selectOne = baseMapper.selectOne(sysuserUser, "SysuserUserMapper.getUserInfo");
		return selectOne;
	}

	@Override
	public SysuserUser getUserMoney(Integer userId) throws Exception {
		return baseMapper.selectOne(userId, "SysuserUserMapper.getUserMoney");
	}

	@Override
	public Integer updateMF(SysuserUser sysuserUser) throws Exception {
		return baseMapper.update(sysuserUser, "SysuserUserMapper.updateMF");

	}

	@Override
	public Integer updateUsersWalletPay(SysuserWalletPaylog sysuserWalletPaylog) throws Exception {
		return baseMapper.update(sysuserWalletPaylog, "SysuserWalletPaylogMapper.updateUsersWalletPay");

	}

	@Override
	public SysuserAccount getSysuserAccount(Integer userId) throws Exception {
		SysuserAccount selectOne = baseMapper.selectOne(userId, "SysuserAccountMapper.getLoginAccount");
		return selectOne;
	}

	@Override
	public Integer insertSysuserUserScore(SysuserUserScore sysuserUserScore) throws Exception {

		return baseMapper.insert(sysuserUserScore, "SysuserUserScoreMapper.insertSysuserUserScore");
	}

	@Override
	public JSONObject getMemberBasicInfo(String token) throws Exception {
		JSONObject postForObject = new JSONObject();
		try {
			String url = myProps.getUserCenterUrl() + "/member/front_user_center/v1/get_member_basic_info";
			url = url + "?token={token}&clientID={clientID}&timestamp={timestamp}&sign={sign}";
			Map<String, String> map = new HashMap<String, String>();
			map.put("token", token);
			Map<String, String> commonMap = CommonUtil.CommonMap(map);
			Logger.info("组装发送数据 :%s", commonMap);
			postForObject = restTemplate.getForObject(url, JSONObject.class, commonMap);
			Logger.info("获取用户信息结果 :%s", postForObject);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取会员信息异常!");
		}
		return postForObject;
	}

	@Override
	public JSONObject validePayPwd(String memId, String payPwd) throws Exception {
		JSONObject postForObject = new JSONObject();
		try {
			Logger.info("解密之后:%s", payPwd);
			String url = myProps.getRouteServiceUrl() + "/member/account_service/v1/valide_pay_pwd";
			url = url + "?memId={memId}&pay_pwd={pay_pwd}&clientID={clientID}&timestamp={timestamp}&sign={sign}";
			Map<String, String> hashMap = new HashMap<String,String>();
			hashMap.put("memId", memId);
			hashMap.put("pay_pwd", payPwd);
			Map<String, String> commonMap = CommonUtil.CommonMap(hashMap);
			Logger.info("验证支付密码组装发送数据 :%s", commonMap);
			postForObject = restTemplate.getForObject(url, JSONObject.class, commonMap);
			Logger.info("验证支付密码请求结果 :%s", postForObject);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("验证密码异常!");
		}
		return postForObject;

	}

	@Override
	public JSONObject tokenTOmemId(String token) throws Exception {
		JSONObject getMemid = new JSONObject();
		try {
			String url = myProps.getUserCenterUrl() + "/member/front_user_center/v1/get_memid_by_token";
			url = url + "?token={token}&clientID={clientID}&timestamp={timestamp}&sign={sign}";
			Map<String, String> map = new HashMap<String, String>();
			map.put(OauthConst.TOKEN, token);
			Map<String, String> commonMap = CommonUtil.CommonMap(map);
			Logger.info("tokenTOmemId组装发送数据:%s", commonMap);
			getMemid = restTemplate.getForObject(url, JSONObject.class, commonMap);
			Logger.info("tokenTOmemId请求结果 :%s", getMemid);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("tokenTOmemId异常!");
		}
		return getMemid;
		
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject freezeUnfreeze(PaymentTrade paymentTrade, Map<String, Object> paymentBill) throws Exception {
		String string = "";
		try {
			String url = myProps.getRouteServiceUrl() + "/member/account_service/v1/freeze_unfreeze";
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
			headers.setContentType(type);
			JSONObject json = new JSONObject();
			json.put("memId", paymentTrade.getMemId());
			json.put("order_source", "1gw");
			if (new BigDecimal(paymentBill.get("curMoney").toString()).compareTo(new BigDecimal(0)) != 1) {
				json.put("pay_type", 2);
				// 没有第三方支付
			} else {
				json.put("pay_type", 3);
			}
			json.put("status", 3);
			json.put("consume_money", new BigDecimal(paymentBill.get("walletPay").toString()));
			json.put("consume_points", Integer.valueOf(paymentBill.get("pointPay").toString()));
			JSONObject commonJSON = CommonUtil.CommonJSON(json);
			Logger.info("freezeUnfreeze组装发送数据:%s", commonJSON);
			HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(commonJSON, headers);
			string = restTemplate.postForObject(url, formEntity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("冻结异常!");
		}
		return new JSONObject().fromObject(string);
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject getMemIdByUserId(Integer userId) throws Exception {
		String forObject = "";
		try {
			String url = myProps.getMeiduimallUrl() + "/openapi/user/getMemIdByUserId?user_id=";
			url = url + userId;
			Logger.info("getMemIdByUserId组装发送数据:%s", url);
			forObject = restTemplate.getForObject(url, String.class);
			Logger.info("getMemIdByUserId数据:%s", forObject);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getMemIdByUserId异常!");
		}
		return new JSONObject().fromObject(forObject);
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject unfreezeDeduct(Map<String, Object> paymentBill, String memId) throws Exception {
		String string = "";
		try {
			String url = myProps.getRouteServiceUrl() + "/member/account_service/v1/unfreeze_deduct";
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
			headers.setContentType(type);
			JSONObject json = new JSONObject();
			json.put("memId", memId);
			json.put("order_source", "1gw");
			if (new BigDecimal(paymentBill.get("curMoney").toString()).compareTo(new BigDecimal(0)) != 1) {
				json.put("pay_type", 2);
				// 没有第三方支付
			} else {
				json.put("pay_type", 3);
			}
			json.put("consume_money", new BigDecimal(paymentBill.get("walletPay").toString()));
			json.put("consume_points", Integer.valueOf(paymentBill.get("pointPay").toString()));
			JSONObject commonJSON = CommonUtil.CommonJSON(json);
			Logger.info("unfreezeDeduct组装发送数据:%s", commonJSON);
			HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(commonJSON, headers);
			string = restTemplate.postForObject(url, formEntity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解冻异常!");
		}
		return new JSONObject().fromObject(string);
	}

	@Override
	public JSONObject sendSmsMessage(String mobile, String tid) throws Exception {
			String url = myProps.getSendSmsUrl() + "/notify/short_msg_service/v1/send_common_sms_message";
			MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
			paramMap.add("templateId", "1GW_1003");
			paramMap.add("params", tid);
			paramMap.add("phones", mobile);
			Logger.info("sendSmsMessage组装发送数据:%s", paramMap);
			JSONObject postForObject = restTemplate.postForObject(url, paramMap, JSONObject.class);
			Logger.info("sendSmsMessage结果:%s", postForObject);
		    return postForObject;
	}

}
