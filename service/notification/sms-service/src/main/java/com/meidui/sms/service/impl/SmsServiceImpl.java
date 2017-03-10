package com.meidui.sms.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meidui.sms.entity.SendSmsHistory;
import com.meidui.sms.entity.TemplateInfo;
import com.meidui.sms.mapper.SendSmsHistoryMapper;
import com.meidui.sms.model.message.CommonShortMessageModel;
import com.meidui.sms.service.SmsService;
import com.meiduimall.FastJsonUtil;
import com.meiduimall.ResultBody;
import com.meiduimall.StringUtil;
import com.meiduimall.SysConstant;
import com.meiduimall.redis.util.JedisUtil;

/**
 * 短信接口
 * 
 * @author 
 * @since 2011.01.05
 */
@Service
public class SmsServiceImpl implements SmsService{
	
	private static Logger Logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private ZucpService zucpService;
	@Autowired
	private AliyunServiceImpl aliyunService;

	@Autowired
	private MessageChannelService messageChannelService;
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
	private ResultBody replacesContent(String params,String content){
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		if(!StringUtil.isEmptyByString(params)){
			String[] replaces = params.split(",");
			int count = StringUtil.findSubstringCount(content, "{");
			if(replaces.length < count) {
				Logger.info("替换短信模板内容异常：%s", "替换内容与替换参数不匹配，replaces="+replaces+",count="+count);
				result = new ResultBody(ResultBody.FAILED, "替换内容与替换参数不匹配！");
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
	public ResultBody sendSmsMessage(CommonShortMessageModel model) throws Exception {
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		
		String tempMsg =JedisUtil.getJedisInstance().execGetFromCache(model.getPhones() + model.getTemplateId() + model.getParams()) == null ? "":
			JedisUtil.getJedisInstance().execGetFromCache(model.getPhones() + model.getTemplateId() + model.getParams()).toString();
		if(!StringUtil.isEmptyByString(tempMsg)){
			result = new ResultBody(ResultBody.FAILED, "请勿频繁重复发送短信");
			return result;
		}
		
		String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if(StringUtil.isEmptyByString(templateListJsonStr)){
			result = new ResultBody(ResultBody.FAILED, "获取短信模板列表失败");
			return result;
		}
		
		TemplateInfo ti = getTemplateByKey(model.getTemplateId(),templateListJsonStr);
		
		if(StringUtil.isEmptyByString(ti.getTemplateKey()) || StringUtil.isEmptyByString(ti.getTemplateContent())){
			result = new ResultBody(ResultBody.FAILED, "获取不到模板id对应短信模板记录");
			return result;
		}
		
		String content = ti.getTemplateContent();
		ResultBody rb = replacesContent(model.getParams(),content);
		
		if(ResultBody.SUCCESS.equals(rb.getStatus_code()) && !StringUtil.isEmptyByString(String.valueOf(rb.getData()))){
			content = String.valueOf(rb.getData());
		}
		
		String params = "";
		if(!StringUtil.isEmptyByString(model.getParams())){
			params = aliDaYuParamsToJson(false,model.getParams());
		}
		//设置发送历史记录值
		SendSmsHistory ssh = setHistory(model);
		try {
			
			if(StringUtil.isEmptyByString(model.getSupplierId())){
				/**
				 * 首先阿里云发送发送短信，如果发送失败则调用漫道发送 </br>
				 * 全部失败则返回失败信息
				 */
				boolean flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
				Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
				String res = "";
				if (!flag) {
					res = zucpService.Send(model.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
						return result;
					}
				}
				
				JedisUtil.getJedisInstance().execSetexToCache(model.getPhones() + model.getTemplateId() + model.getParams(), Integer.valueOf(ti.getEffectiveTime()), content);
				ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
				
				return result;
			}else{
				
				ssh.setChannelId(model.getSupplierId());
				boolean flag = false;
				String res = "-1"; 
				if(SysConstant.MESSAGE_TEMPLATE_ALI_KEY.equals(model.getSupplierId())){
					flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
					Logger.info("阿里大于发送短信结果（flag）：%s" ,String.valueOf(flag));
					if (!flag) {
						result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
						return result;
					}
				}else if(SysConstant.MESSAGE_TEMPLATE_MANDAO_KEY.equals(model.getSupplierId())){
					res = zucpService.Send(model.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
						return result;
					}
				}else{
					result = new ResultBody(ResultBody.FAILED, "短信渠道错误！");
					return result;
				}
				JedisUtil.getJedisInstance().execSetexToCache(model.getPhones() + model.getTemplateId() + model.getParams(),Integer.valueOf(ti.getEffectiveTime()) ,content);
				
				ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
			
			}
			
		} catch (Exception e) {
			Logger.error("发送普通短信时服务发生异常", e);
			
			ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
			ssh.setResultMsg("发送普通短信时服务发生异常："+ e.toString());
			sendSmsHistoryMapper.insert(ssh);
		}
		return result;
		
	}

	@Override
	public ResultBody validSmsMessageParams(CommonShortMessageModel model) {
		//CommonShortMessageModel model = JsonHelper.jsonToBean(biz_params, CommonShortMessageModel.class);
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		if(null != model){
			if(StringUtil.isEmptyByString(model.getClientID())){
				result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getPhones())){
				result = new ResultBody(ResultBody.FAILED, "手机号码不能为空");
				return result;
			}
			if(!StringUtil.isPhoneToRegex(model.getPhones())){
				result = new ResultBody(ResultBody.FAILED, "手机号码格式错误");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getTemplateId())){
				result = new ResultBody(ResultBody.FAILED, "短信模板编号不能为空");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getParams())){
				result = new ResultBody(ResultBody.FAILED, "请求替换模板参数不能为空");
				return result;
			}
			result.setData(model);
		}
		return result;
	}

	@Override
	public ResultBody validVerificationCodeParams(CommonShortMessageModel model) {
		//CommonShortMessageModel model = JsonHelper.jsonToBean(biz_params, CommonShortMessageModel.class);
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		if(null != model){
			if(StringUtil.isEmptyByString(model.getClientID())){
				result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getPhones())){
				result = new ResultBody(ResultBody.FAILED, "手机号码不能为空");
				return result;
			}
			if(!StringUtil.isPhoneToRegex(model.getPhones())){
				result = new ResultBody(ResultBody.FAILED, "手机号码格式错误");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getTemplateId())){
				result = new ResultBody(ResultBody.FAILED, "短信模板编号不能为空");
				return result;
			}
			result.setData(model);
		}
		return result;
	}

	@Override
	public ResultBody validCheckVerificationCodeParams(CommonShortMessageModel model) {
		//CommonShortMessageModel model = JsonHelper.jsonToBean(biz_params, CommonShortMessageModel.class);
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		if(null != model){
			if(StringUtil.isEmptyByString(model.getClientID())){
				result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getPhones())){
				result = new ResultBody(ResultBody.FAILED, "手机号码不能为空");
				return result;
			}
			if(!StringUtil.isPhoneToRegex(model.getPhones())){
				result = new ResultBody(ResultBody.FAILED, "手机号码格式错误");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getTemplateId())){
				result = new ResultBody(ResultBody.FAILED, "短信模板编号不能为空");
				return result;
			}
			if(StringUtil.isEmptyByString(model.getVerificationCode())){
				result = new ResultBody(ResultBody.FAILED, "短信验证码不能为空");
				return result;
			}
			if(!StringUtil.isNumeric(model.getVerificationCode())){
				result = new ResultBody(ResultBody.FAILED, "短信验证码必须是纯数字");
				return result;
			}
			result.setData(model);
		}
		return result;
	}

	@Override
	public ResultBody sendSmsVerificationCode(CommonShortMessageModel model) throws Exception {
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		Object tempMsg = JedisUtil.getJedisInstance().execGetFromCache(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId());
		if(!StringUtil.isEmptyByString(String.valueOf(tempMsg))){
			result = new ResultBody(ResultBody.FAILED, "请勿频繁重复发送验证码");
			return result;
		}
		//生成6位随机数
		String randomCode = String.valueOf(((Math.random() * 9 + 1) * 100000)).substring(0, 6);
		Logger.info("发送短信生成的验证码为：%s" , randomCode);
		
		String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if(StringUtil.isEmptyByString(templateListJsonStr)){
			result = new ResultBody(ResultBody.FAILED, "获取短信模板列表失败");
			return result;
		}
		//确定发送内容
		TemplateInfo ti = getTemplateByKey(model.getTemplateId(),templateListJsonStr);
		
		if(StringUtil.isEmptyByString(ti.getTemplateKey()) || StringUtil.isEmptyByString(ti.getTemplateContent())){
			result = new ResultBody(ResultBody.FAILED, "获取不到模板id对应短信模板记录");
			return result;
		}
		//设置发送历史记录值
		SendSmsHistory ssh = setHistory(model);
		
		String content = ti.getTemplateContent();
		content = content.replace("{VerificationCode}", randomCode);
		
		if(!StringUtil.isEmptyByString(model.getParams())){
			ResultBody rb = replacesContent(model.getParams(),content);
			if(ResultBody.SUCCESS.equals(rb.getStatus_code()) && !StringUtil.isEmptyByString(String.valueOf(rb.getData()))){
				content = String.valueOf(rb.getData());
			}
		}
		
		String params = "";
		if(!StringUtil.isEmptyByString(model.getParams())){
			params = aliDaYuParamsToJson(true,randomCode + "," + model.getParams());
		}
		boolean flag = false;
		String res = "-1";
		
		try {
		 
			if(StringUtil.isEmptyByString(model.getSupplierId())){
				/**
				 * 首先阿里云发送发送短信，如果发送失败则调用漫道发送 </br>
				 * 全部失败则返回失败信息
				 */
				flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
				Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
				
				if (!flag) {
					res = zucpService.Send(model.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
						return result;
					}
				}
				
				JedisUtil.getJedisInstance().execSetexToCache(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId(),
						model.getTimeout() == null?Integer.valueOf(ti.getEffectiveTime()):Integer.valueOf(model.getTimeout()), randomCode);
				ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
				
				return result;
			}else{
				
				if(SysConstant.MESSAGE_TEMPLATE_ALI_KEY.equals(model.getSupplierId())){
					flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
					Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
					if (!flag) {
						result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
						return result;
					}
				}else if(SysConstant.MESSAGE_TEMPLATE_MANDAO_KEY.equals(model.getSupplierId())){
					res = zucpService.Send(model.getPhones(), content);
					Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
					if (Long.parseLong(res) < 0) {
						result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
						return result;
					}
				}else{
					result = new ResultBody(ResultBody.FAILED, "短信渠道错误！");
					return result;
				}
				
				JedisUtil.getJedisInstance().execSetexToCache(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId(),
						model.getTimeout() == null?Integer.valueOf(ti.getEffectiveTime()):Integer.valueOf(model.getTimeout()), randomCode);
				
				ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
				ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
				sendSmsHistoryMapper.insert(ssh);
			}
		
		} catch (Exception e) {
			Logger.error("发送短信验证码时服务发生异常", e);
			
			ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
			ssh.setResultMsg("发送短信验证码时服务发生异常："+ e.toString());
			sendSmsHistoryMapper.insert(ssh);
		}
		return result;
	}

	@Override
	public ResultBody checkSmsVerificationCode(CommonShortMessageModel model) throws Exception {
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		
		
		Object tempVerificationCode = JedisUtil.getJedisInstance().execGetFromCache(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId());
		
		//设置发送历史记录值
		SendSmsHistory ssh = setHistory(model);
		
		ssh.setRequestParams("用户校验验证码发送参数：" +ToStringBuilder.reflectionToString(model));
		
		if(StringUtil.isEmptyByString(String.valueOf(tempVerificationCode))){
			result = new ResultBody(ResultBody.FAILED, "验证码已过期");
			ssh.setResultMsg("验证返回结果：" + ToStringBuilder.reflectionToString(result));
			sendSmsHistoryMapper.insert(ssh);
			return result;
		}
		
		if(!String.valueOf(tempVerificationCode).equals(model.getVerificationCode().trim())){
			result = new ResultBody(ResultBody.FAILED, "验证码输入错误");
			ssh.setResultMsg("验证返回结果：" + ToStringBuilder.reflectionToString(result));
			sendSmsHistoryMapper.insert(ssh);
			return result;
		}
		ssh.setResultMsg("验证返回结果：" + ToStringBuilder.reflectionToString(result));
		sendSmsHistoryMapper.insert(ssh);
		JedisUtil.getJedisInstance().execDecrToCache(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId());
		return result;
	}
	
	private SendSmsHistory setHistory(CommonShortMessageModel model){
		SendSmsHistory ssh = new SendSmsHistory();
		//ssh.setChannelId(model.getSupplierId() == null?"":model.getSupplierId());
		ssh.setId(UUID.randomUUID().toString());
		ssh.setClientId(model.getClientID());
		ssh.setTemplateKey(model.getTemplateId());
		ssh.setCreateDate(new Date());
		ssh.setCreater(model.getPhones());
		ssh.setPhone(model.getPhones());
		ssh.setRemark(model.getParams());
		
		return ssh;
	}

}
