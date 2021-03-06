package com.meiduimall.service.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.member.config.ServiceUrlProfileConfig;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.constant.ConstSmsTemplateID;
import com.meiduimall.service.member.constant.ConstSmsType;
import com.meiduimall.service.member.constant.ConstSysParamsDefination;
import com.meiduimall.service.member.model.request.RequestCheckValidateCode;
import com.meiduimall.service.member.model.request.RequestGetValidateCode;
import com.meiduimall.service.member.model.request.RequestSendSms;
import com.meiduimall.service.member.service.SmsService;
import com.meiduimall.service.member.util.HttpUtils;


@Service
public class SmsServiceImpl implements SmsService 
{
	private final static Logger logger=LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Autowired
	private ServiceUrlProfileConfig serviceUrlProfileConfig;
	
	@Override
	public boolean getValidateCode(RequestGetValidateCode model){
		String smsServiceUrl=serviceUrlProfileConfig.getSmsServiceUrl()+"/v1/new/send_sms_verification_code";
		Map<String,String> mapSmsData=new HashMap<>();
		mapSmsData.put("timeout",String.valueOf(model.getTimeout()));
		mapSmsData.put("phones",model.getPhone());
		mapSmsData.put("templateId",ConstSmsTemplateID.getSmsTemplate(ConstSmsTemplateID.SEND_VALIDATE_CODE));
		mapSmsData.put("type",ConstSmsType.getSmsType(model.getType()));
		mapSmsData.put("sysKey",ConstSysParamsDefination.SMS_SYSKEY);
		logger.info("调用短信服务>>获取短信验证码API>>URL:{},DATA:{}",smsServiceUrl,mapSmsData.toString());
		String result=null;
		try {
			result=HttpUtils.form(smsServiceUrl,mapSmsData);
		} catch (Exception e) {
			logger.error("账号服务>>调用短信服务>>获取短信验证码API>>异常：{}",e.toString());
			throw new ServiceException(ConstApiStatus.GET_VALIDATE_CODE_EXCEPTION);
		} 
		ResBodyData resBodyData=JSON.parseObject(result,ResBodyData.class);
		logger.info("调用短信服务>>获取短信验证码API>>RESULT:{}",resBodyData.toString());
		if(resBodyData.getStatus()==0){
			logger.info("手机号：{}短信验证码获取成功",model.getPhone());
			return true;
		}
		else{
			logger.info("手机号：{}短信验证码获取失败",model.getPhone());
			throw new ServiceException(ConstApiStatus.GET_VALIDATE_CODE_EXCEPTION);
		}
	}
	
	@Override
	public boolean checkValidateCode(RequestCheckValidateCode model){
		String smsServiceUrl=serviceUrlProfileConfig.getSmsServiceUrl()+"/v1/new/check_sms_verification_code";
		Map<String,String> mapSmsData=new HashMap<>();
		mapSmsData.put("verificationCode",model.getValidate_code());
		mapSmsData.put("phones",model.getPhone());
		mapSmsData.put("templateId",ConstSmsTemplateID.getSmsTemplate(ConstSmsTemplateID.SEND_VALIDATE_CODE));
		mapSmsData.put("type",ConstSmsType.getSmsType(model.getType()));
		mapSmsData.put("sysKey",ConstSysParamsDefination.SMS_SYSKEY);
		logger.info("调用短信服务>>校验短信验证码API>>URL:{},DATA:{}",smsServiceUrl,mapSmsData.toString());
		String result=null;
		try {
			result=HttpUtils.form(smsServiceUrl,mapSmsData);
		} catch (Exception e) {
			logger.error("账号服务>>调用短信服务>>校验短信验证码API>>异常：{}",e.toString());
			throw new ServiceException(ConstApiStatus.CHECK_VALIDATE_CODE_NOT_PASS);
		} 
		ResBodyData resBodyData=JSON.parseObject(result,ResBodyData.class);
		logger.info("调用短信服务>>校验短信验证码API>>RESULT:{}",resBodyData.toString());
		if(resBodyData.getStatus()==0){
			logger.info("手机号：{}短信验证码校验通过",model.getPhone());
			return true;
		}
		else {
			logger.info("手机号：{}短信验证码校验不通过",model.getPhone());
			throw new ServiceException(ConstApiStatus.CHECK_VALIDATE_CODE_NOT_PASS);
		}
	}

	@Override
	public void sendSms(RequestSendSms model){
		String smsServiceUrl=serviceUrlProfileConfig.getSmsServiceUrl()+"/v1/new/send_common_sms_message";
		Map<String,String> mapSmsData=new HashMap<>();
		mapSmsData.put("phones",model.getPhone());
		mapSmsData.put("templateId",model.getTemplateId());
		mapSmsData.put("sysKey",ConstSysParamsDefination.SMS_SYSKEY);
		mapSmsData.put("params",model.getParams());
		logger.info("调用短信服务>>发送普通短信API>>URL:{},DATA:{}",smsServiceUrl,mapSmsData.toString());
		String result=null;
		try {
			result=HttpUtils.form(smsServiceUrl,mapSmsData);
		} catch (Exception e) {
			logger.error("账号服务>>调用短信服务>>发送短信API>>异常：{}",e.toString());
		} 
		ResBodyData resBodyData=JSON.parseObject(result,ResBodyData.class);
		logger.info("调用短信服务>>发送普通短信API>>RESULT:{}",resBodyData.toString());
		if(resBodyData.getStatus()==0){
			logger.info("账号服务>>调用短信服务>>发送短信API>>手机号：{}发送短信成功",model.getPhone());
		}
		else {
			logger.info("账号服务>>调用短信服务>>发送短信API>>手机号：{}发送短信失败",model.getPhone());
		}
	}
	
}
