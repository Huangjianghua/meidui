package com.meiduimall.service.sms.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.service.AliyunService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service
public class AliyunServiceImpl implements AliyunService {

	private static Logger logger = LoggerFactory.getLogger(AliyunServiceImpl.class);

	@Autowired
	private Environment env;

	@Override
	public boolean send(String phones, String externalTemplateNo, String params) {

		String url = env.getProperty("aliyun.url");
		String appKey = env.getProperty("aliyun.appKey");
		String appSecret = env.getProperty("aliyun.appSecret");
		String signName = env.getProperty("aliyun.signName");

		if (StringUtils.isEmpty(externalTemplateNo)) {
			logger.error("模板external_template_no字段为空,无法使用阿里大于发送短短信！");
			return false;
		}

		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		req.setSmsParamString(params);
		req.setRecNum(phones);
		req.setSmsTemplateCode(externalTemplateNo);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (Exception e) {
			logger.error("短信发送，阿里云平台短信发送异常: " + e);
			return false;
		}
		if (rsp != null) {
			String resultBody = rsp.getBody();
			logger.error("短信发送，阿里云平台返回: " + resultBody);

			if (!StringUtils.isEmpty(resultBody) && resultBody.indexOf("\"success\":true") != -1) {

				return true;
			} else if (!StringUtils.isEmpty(resultBody) && resultBody.indexOf("isv.BUSINESS_LIMIT_CONTROL") != -1) {
				// 短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。
				// 短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）。
				logger.error("短信发送，阿里云平台返回错误码: isv.BUSINESS_LIMIT_CONTROL");

				throw new ServiceException(SmsApiCode.REPEATING);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
