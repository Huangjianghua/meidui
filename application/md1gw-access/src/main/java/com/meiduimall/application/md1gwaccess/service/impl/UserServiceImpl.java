package com.meiduimall.application.md1gwaccess.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
import com.meiduimall.application.md1gwaccess.util.SystemConfig;

import net.sf.json.JSONObject;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Map<String,Object> getUserInfo(SysuserUser sysuserUser) throws Exception {
		Map<String,Object> selectOne = baseMapper.selectOne(sysuserUser, "SysuserUserMapper.getUserInfo");
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

	@SuppressWarnings("static-access")
	@Override
	public JSONObject getMemberBasicInfo(String memId) throws Exception {
		String url = SystemConfig.getInstance().configMap.get("get_member_basic_info");
		HashMap<String, String> memberMap = new HashMap<String,String>();
		memberMap.put("memId", memId);
		ResponseEntity<String> member = restTemplate.getForEntity(url, String.class, CommonUtil.CommonMap(memberMap));
		Logger.info("获取用户信息结果 :%s", member.getBody());
		JSONObject memberObj = JSONObject.fromObject(member.getBody());
		return memberObj;
	}
	
	
	
	@SuppressWarnings("static-access")
	@Override
	public JSONObject validePayPwd(String memId,String payPwd)throws Exception{
		String valide_pay_pwd_url = SystemConfig.getInstance().configMap.get("valide_pay_pwd_url");
		HashMap<String, String> pwdMap = new HashMap<String,String>();
		pwdMap.put("memId", memId);
		pwdMap.put("pay_pwd", payPwd);
		ResponseEntity<String> validepwd = restTemplate.postForEntity(
				valide_pay_pwd_url,
				CommonUtil.CommonMap(pwdMap), String.class);
		Logger.info("验证支付密码请求结果 :%s", validepwd.getBody());
		JSONObject validepwdObj = JSONObject.fromObject(validepwd.getBody());
		return validepwdObj;
		
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject tokenTOmemId(String token) throws Exception {
		String url = SystemConfig.getInstance().configMap.get("get_memid_by_token_url");
		HashMap<String, String> hashMap = new HashMap<String,String>();
		hashMap.put(OauthConst.TOKEN, token);
		ResponseEntity<String> getMemid = restTemplate.postForEntity(url, CommonUtil.CommonMap(hashMap), String.class);
		Logger.info("tokenTOmemId请求结果 :%s", getMemid.getBody());
		JSONObject getMemidObj = JSONObject.fromObject(getMemid.getBody());
		return getMemidObj;
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject freezeUnfreeze(PaymentTrade paymentTrade, Map<String, Object> paymentBill) throws Exception {
		String url = SystemConfig.getInstance().configMap.get("freeze_unfreeze");
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
		headers.setContentType(type);
		JSONObject json = new JSONObject();
		json.put("memId", paymentTrade.getMemId());
		json.put("order_source", "1gw");
		if (new BigDecimal(paymentBill.get("curMoney").toString()).compareTo(new BigDecimal(0)) != 1) {
			json.put("pay_type", 2);
			//没有第三方支付
		}else{
			json.put("pay_type", 3);
		}
		json.put("status", 3);
		json.put("consume_money", new BigDecimal(paymentBill.get("walletPay").toString()));
		json.put("consume_points", Integer.valueOf(paymentBill.get("pointPay").toString()));
		HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(CommonUtil.CommonJSON(json), headers);
		String string = restTemplate.postForObject(url, formEntity, String.class);
		return new JSONObject().fromObject(string);
	}

	
	@SuppressWarnings("static-access")
	@Override
	public JSONObject getMemIdByUserId(Integer userId)  throws Exception{
		String url = SystemConfig.getInstance().configMap.get("getMemIdByUserId");
		url = url + userId;
		String forObject = restTemplate.getForObject(url, String.class);
		
		return new JSONObject().fromObject(forObject);
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject unfreezeDeduct(Map<String, Object> paymentBill, String memId) throws Exception {
		String url = SystemConfig.getInstance().configMap.get("unfreeze_deduct");
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
		headers.setContentType(type);
		JSONObject json = new JSONObject();
		json.put("memId", memId);
		json.put("order_source", "1gw");
		if (new BigDecimal(paymentBill.get("curMoney").toString()).compareTo(new BigDecimal(0)) != 1) {
			json.put("pay_type", 2);
			//没有第三方支付
		}else{
			json.put("pay_type", 3);
		}
		json.put("status", 3);
		json.put("consume_money", new BigDecimal(paymentBill.get("walletPay").toString()));
		json.put("consume_points", Integer.valueOf(paymentBill.get("pointPay").toString()));
		HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(CommonUtil.CommonJSON(json), headers);
		String string = restTemplate.postForObject(url, formEntity, String.class);
		return new JSONObject().fromObject(string);
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject sendSmsMessage(String mobile, String tid) throws Exception {
		String url = SystemConfig.getInstance().configMap.get("send_common_sms_message");
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_KEYVALUE);
		headers.setContentType(type);
		JSONObject json = new JSONObject();
		json.put("templateId", "1GW_1003");
		json.put("params", tid);
		json.put("phones", mobile);
		HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(json, headers);
		JSONObject postForObject = restTemplate.postForObject(url, formEntity, JSONObject.class);
		return postForObject;
	}

}
