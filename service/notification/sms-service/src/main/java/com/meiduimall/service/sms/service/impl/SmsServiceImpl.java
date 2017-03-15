package com.meiduimall.service.sms.service.impl;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.FastJsonUtil;
import com.meiduimall.core.util.StringUtil;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.sms.SysConstant;
import com.meiduimall.service.sms.entity.SendSmsHistory;
import com.meiduimall.service.sms.entity.TemplateInfo;
import com.meiduimall.service.sms.mapper.SendSmsHistoryMapper;
import com.meiduimall.service.sms.request.SmsRequest;
import com.meiduimall.service.sms.service.SmsService;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: SmsServiceImpl.java
 * Author:   Administrator
 * Date:     2017年3月14日 下午3:39:04
 * Description: 短信接口
 */
@Service
public class SmsServiceImpl implements SmsService{
	
	private static Logger Logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private ZucpServiceImpl zucpService;
	@Autowired
	private AliyunServiceImpl aliyunService;

	@Autowired
	private MessageChannelServiceImpl messageChannelService;
	@Autowired
	private SendSmsHistoryMapper sendSmsHistoryMapper;
	
	
	private TemplateInfo getTemplateByKey(String templateId,String templateListJsonStr){
		TemplateInfo ti = new TemplateInfo();
		List<TemplateInfo> templateInfoList = (List<TemplateInfo>)FastJsonUtil.deserialize(templateListJsonStr, TemplateInfo.class);
		for(TemplateInfo info : templateInfoList){
			if(info.getTemplateKey().equals(templateId)){
				try {
					BeanUtils.copyProperties(ti, info);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return ti;
	}

	/**
	 * 替换模板中的参数
	 * @param params
	 * @param content
	 * @return
	 */
	private ResBodyData replacesContent(String params,String content){
		ResBodyData result = new ResBodyData(BaseApiCode.SUCCESS,BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
		if(!StringUtil.isEmptyByString(params)){
			String[] replaces = params.split(",");
			int count = StringUtil.findSubstringCount(content, "{");
			if(replaces.length < count) {
				Logger.info("替换短信模板内容异常：%s", "替换内容与替换参数不匹配，replaces="+replaces+",count="+count);
				result = new ResBodyData(BaseApiCode.SMSTEMPLATE_PRASE_FAIL, "替换内容与替换参数不匹配！");
				return result;
			}
			for(int index=0; index < replaces.length; index++) {
				content = content.replace("{" + index + "}", replaces[index]);
			}
			result.setData(content);
		}
		return result;
	}
	
	
	
	/**
	 * 转换发送给阿里大于的替换模板参数格式，以json格式
	 * @param params
	 * @return
	 */
	private String aliDaYuParamsToJson(boolean isCode,String params){
		Map<String, String> map = new HashMap<>();
		String[] paramsStr = params.split(",");
		for(int i = 0;i < paramsStr.length; i++){
			if(!isCode && !StringUtil.isEmptyByString(paramsStr[i])){
				map.put("V"+i, paramsStr[i]);
			}else{
				if(i == 0){
					map.put("VerificationCode", paramsStr[i]);
				}else{
					map.put("V"+i, paramsStr[i]);
				}
			}
		}
		params =FastJsonUtil.serialize(map);
		return params;
	}
	
	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param content
	 * @param type
	 * @throws Exception
	 */
	public ResBodyData sendSmsMessage(SmsRequest request) throws Exception {
		
		ResBodyData result = new ResBodyData(BaseApiCode.SUCCESS,BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
		String tempMsg =JedisUtil.getJedisInstance().execGetFromCache(request.getPhones() + request.getTemplateId() + request.getParams());
		if(!StringUtil.isEmptyByString(tempMsg)){
			result = new ResBodyData(BaseApiCode.REPEAT_FAIL,BaseApiCode.getZhMsg(BaseApiCode.REPEAT_FAIL));
			return result;
		}
		
		String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if(StringUtil.isEmptyByString(templateListJsonStr)){
			result = new ResBodyData(BaseApiCode.TEMPLATE_FAIL,BaseApiCode.getZhMsg(BaseApiCode.TEMPLATE_FAIL));
			return result;
		}
		
		
		TemplateInfo ti = getTemplateByKey(request.getTemplateId(),templateListJsonStr);
		if(StringUtil.isEmptyByString(ti.getTemplateKey()) || StringUtil.isEmptyByString(ti.getTemplateContent())){
			result = new ResBodyData(BaseApiCode.SMSTEMPLATE_NOT_EXISTS,BaseApiCode.getZhMsg(BaseApiCode.SMSTEMPLATE_NOT_EXISTS));
			return result;
		}
		
		String content = ti.getTemplateContent();
		ResBodyData rb = replacesContent(request.getParams(),content);
		
		if(BaseApiCode.SUCCESS==rb.getStatus() && !StringUtil.isEmptyByString(String.valueOf(rb.getData()))){
			content = String.valueOf(rb.getData());
		}
		
		String params = "";
		if(!StringUtil.isEmptyByString(request.getParams())){
			params = aliDaYuParamsToJson(false,request.getParams());
		}
		//设置发送历史记录值
		SendSmsHistory ssh = setHistory(request);
		try {
			
			if(StringUtil.isEmptyByString(request.getSupplierId())){
				/**
				 * 首先阿里云发送发送短信，如果发送失败则调用漫道发送 </br>
				 * 全部失败则返回失败信息
				 */
				boolean flag = aliyunService.Send(request.getPhones(), ti.getExternalTemplateNo(), params);
				Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
				String res = "";
				if (!flag) {
					res = zucpService.Send(request.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResBodyData(BaseApiCode.SMS_SEND_FAIL, "发送短信失败！");
						return result;
					}
				}
				
				JedisUtil.getJedisInstance().execSetexToCache(request.getPhones() + request.getTemplateId() + request.getParams(), Integer.valueOf(ti.getEffectiveTime()), content);
				ssh.setRequestParams(request.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
				
				return result;
			}else{
				
				ssh.setChannelId(request.getSupplierId());
				boolean flag = false;
				String res = "-1"; 
				if(SysConstant.MESSAGE_TEMPLATE_ALI_KEY.equals(request.getSupplierId())){
					flag = aliyunService.Send(request.getPhones(), ti.getExternalTemplateNo(), params);
					Logger.info("阿里大于发送短信结果（flag）：%s" ,String.valueOf(flag));
					if (!flag) {
						result = new ResBodyData(BaseApiCode.SMS_SEND_FAIL, BaseApiCode.getZhMsg(BaseApiCode.SMS_SEND_FAIL));
						return result;
					}
				}else if(SysConstant.MESSAGE_TEMPLATE_MANDAO_KEY.equals(request.getSupplierId())){
					res = zucpService.Send(request.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResBodyData(BaseApiCode.SMS_SEND_FAIL, BaseApiCode.getZhMsg(BaseApiCode.SMS_SEND_FAIL));
						return result;
					}
				}else{
					result = new ResBodyData(BaseApiCode.SMS_SEND_FAIL, BaseApiCode.getZhMsg(BaseApiCode.SMS_SEND_FAIL));
					return result;
				}
				JedisUtil.getJedisInstance().execSetexToCache(request.getPhones() + request.getTemplateId() + request.getParams(),Integer.valueOf(ti.getEffectiveTime()) ,content);
				
				ssh.setRequestParams(request.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
			
			}
			
		} catch (Exception e) {
			Logger.error("发送普通短信时服务发生异常:{}",ExceptionUtils.getFullStackTrace(e));
			ssh.setRequestParams(request.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
			ssh.setResultMsg("发送普通短信时服务发生异常："+ e.toString());
			sendSmsHistoryMapper.insert(ssh);
		}
		return result;
		
	}

	




	@Override
	public ResBodyData sendSmsVerificationCode(SmsRequest request) throws Exception {
		
		ResBodyData result = new ResBodyData(BaseApiCode.SUCCESS,BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
		Object tempMsg = JedisUtil.getJedisInstance().execGetFromCache(request.getPhones() + SysConstant.MESSAGE_CODE_KEY + request.getTemplateId());
		if(!StringUtil.isEmptyByString(String.valueOf(tempMsg))){
			result = new ResBodyData(BaseApiCode.REPEAT_FAIL,BaseApiCode.getZhMsg(BaseApiCode.REPEAT_FAIL));
			return result;
		}
		//生成6位随机数
		String randomCode = String.valueOf(((Math.random() * 9 + 1) * 100000)).substring(0, 6);
		Logger.info("发送短信生成的验证码为：%s" , randomCode);
		
		String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if(StringUtil.isEmptyByString(templateListJsonStr)){
			result = new ResBodyData(BaseApiCode.TEMPLATE_FAIL,BaseApiCode.getZhMsg(BaseApiCode.TEMPLATE_FAIL));
			return result;
		}
		//确定发送内容
		TemplateInfo ti = getTemplateByKey(request.getTemplateId(),templateListJsonStr);
		
		if(StringUtil.isEmptyByString(ti.getTemplateKey()) || StringUtil.isEmptyByString(ti.getTemplateContent())){
			result = new ResBodyData(BaseApiCode.SMSTEMPLATE_NOT_EXISTS,BaseApiCode.getZhMsg(BaseApiCode.SMSTEMPLATE_NOT_EXISTS));
			return result;
		}
		//设置发送历史记录值
		SendSmsHistory ssh = setHistory(request);
		
		String content = ti.getTemplateContent();
		content = content.replace("{VerificationCode}", randomCode);
		
		if(!StringUtil.isEmptyByString(request.getParams())){
			ResBodyData rb = replacesContent(request.getParams(),content);
			if(BaseApiCode.SUCCESS==rb.getStatus() && !StringUtil.isEmptyByString(String.valueOf(rb.getData()))){
				content = String.valueOf(rb.getData());
			}
			
		}
		
		String params = "";
		if(!StringUtil.isEmptyByString(request.getParams())){
			params = aliDaYuParamsToJson(true,randomCode + "," + request.getParams());
		}
		boolean flag = false;
		String res = "-1";
		
		try {
		 
			if(StringUtil.isEmptyByString(request.getSupplierId())){
				/**
				 * 首先阿里云发送发送短信，如果发送失败则调用漫道发送 </br>
				 * 全部失败则返回失败信息
				 */
				flag = aliyunService.Send(request.getPhones(), ti.getExternalTemplateNo(), params);
				Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
				
				if (!flag) {
					res = zucpService.Send(request.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResBodyData(BaseApiCode.SMS_SEND_FAIL, "发送短信失败！");
						return result;
					}
				}
				
				JedisUtil.getJedisInstance().execSetexToCache(request.getPhones() + SysConstant.MESSAGE_CODE_KEY + request.getTemplateId(),
						request.getTimeout() == null?Integer.valueOf(ti.getEffectiveTime()):Integer.valueOf(request.getTimeout()), randomCode);
				ssh.setRequestParams(request.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
				
				return result;
			}else{
				
				if(SysConstant.MESSAGE_TEMPLATE_ALI_KEY.equals(request.getSupplierId())){
					flag = aliyunService.Send(request.getPhones(), ti.getExternalTemplateNo(), params);
					Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
					if (!flag) {
						result = new ResBodyData(BaseApiCode.SMS_SEND_FAIL, BaseApiCode.getZhMsg(BaseApiCode.SMS_SEND_FAIL));
						return result;
					}
				}else if(SysConstant.MESSAGE_TEMPLATE_MANDAO_KEY.equals(request.getSupplierId())){
					res = zucpService.Send(request.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResBodyData(BaseApiCode.SMS_SEND_FAIL, BaseApiCode.getZhMsg(BaseApiCode.SMS_SEND_FAIL));
						return result;
					}
				}else{
					result = new ResBodyData(BaseApiCode.SMS_CHANNEL_FAIL, BaseApiCode.getZhMsg(BaseApiCode.SMS_CHANNEL_FAIL));
					return result;
				}
				
				JedisUtil.getJedisInstance().execSetexToCache(request.getPhones() + SysConstant.MESSAGE_CODE_KEY + request.getTemplateId(),
						request.getTimeout() == null?Integer.valueOf(ti.getEffectiveTime()):Integer.valueOf(request.getTimeout()), randomCode);
				
				ssh.setRequestParams(request.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
			}
		
		} catch (Exception e) {
			Logger.error("发送短信验证码时服务发生异常:{}",ExceptionUtils.getFullStackTrace(e));
			ssh.setRequestParams(request.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
			ssh.setResultMsg("发送短信验证码时服务发生异常："+ e.toString());
			sendSmsHistoryMapper.insert(ssh);
		}
		return result;
	}


	
	private SendSmsHistory setHistory(SmsRequest request){
		SendSmsHistory ssh = new SendSmsHistory();
		ssh.setId(UUID.randomUUID().toString());
		ssh.setClientId(request.getClientID());
		ssh.setTemplateKey(request.getTemplateId());
		ssh.setCreateDate(new Date());
		ssh.setCreater(request.getPhones());
		ssh.setPhone(request.getPhones());
		ssh.setRemark(request.getParams());
		return ssh;
	}

}
