package com.meiduimall.service.sms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.exception.SystemException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.constant.SysConstant;
import com.meiduimall.service.sms.entity.SendSmsHistory;
import com.meiduimall.service.sms.entity.TemplateInfo;
import com.meiduimall.service.sms.mapper.SmsSendHistoryMapper;
import com.meiduimall.service.sms.request.CheckCodeRequest;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;
import com.meiduimall.service.sms.service.AliyunService;
import com.meiduimall.service.sms.service.SmsService;
import com.meiduimall.service.sms.service.TemplateInfoService;
import com.meiduimall.service.sms.service.ZucpService;

@Service
public class SmsServiceImpl implements SmsService {

	private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private ZucpService zucpService;

	@Autowired
	private AliyunService aliyunService;

	@Autowired
	private TemplateInfoService templateInfoService;

	@Autowired
	private SmsSendHistoryMapper smsSendHistoryMapper;

	@Override
	public ResBodyData sendSmsMessage(SendMessageRequest model) {

		// redis存取数据时，使用的key
		String redisKey = model.getPhones() + model.getTemplateId() + model.getParams();

		// 检查是否已在超时时间内，给该手机发送了短信
		String tempMsg = RedisUtils.get(redisKey);
		if (StringUtils.isNotEmpty(tempMsg)) {
			throw new ServiceException(SmsApiCode.REPEATING, BaseApiCode.getZhMsg(SmsApiCode.REPEATING));
		}

		// 获取消息模板--这里获取到的是所有的模板信息的json数据
		String templateListJsonStr = templateInfoService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if (StringUtils.isEmpty(templateListJsonStr)) {
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE_LIST,
					SmsApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE_LIST));
		}

		// 根据模板ID获取短信模板
		TemplateInfo ti = getTemplateByKey(model.getTemplateId(), templateListJsonStr);
		if (ti == null || StringUtils.isEmpty(ti.getTemplateKey()) || StringUtils.isEmpty(ti.getTemplateContent())) {
			// 如果没有模板编号，或者模板内容，则抛异常
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE,
					SmsApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE));
		}

		// 替换模板内容参数
		String content = ti.getTemplateContent();
		content = replacesContent(model.getParams(), content);

		// 转换发送给阿里大于的替换模板参数格式，以json格式
		String params = "";
		if (StringUtils.isNotEmpty(model.getParams())) {
			params = aliDaYuParamsToJson(false, model.getParams());
		}

		// 开始发送短信
		String res = "-1000";
		String channelId = "";
		boolean flag = false;
		if ("1".equals(model.getSupplierId())) {
			// 只使用阿里大于发送
			flag = aliyunService.send(model.getPhones(), ti.getExternalTemplateNo(), params);
			logger.info("只使用阿里大于发送--阿里大于发送短信结果flag：" + flag);
			if (!flag) {
				// 发送失败，直接抛出异常
				throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
			}
			channelId = "1";

		} else if ("2".equals(model.getSupplierId())) {
			// 只使用漫道发送
			res = sendMessageByZucp(model, content, res);
			channelId = "2";

		} else {
			// 首先阿里云发送发送短信，如果发送失败则调用漫道发送。 全部失败则返回失败信息。
			flag = aliyunService.send(model.getPhones(), ti.getExternalTemplateNo(), params);
			logger.info("不指定--阿里大于发送短信结果flag：" + flag);
			if (flag) {
				channelId = "1";
			} else {
				res = sendMessageByZucp(model, content, res);
				channelId = "2";
			}
		}

		try {
			// 发送成功，缓存到redis，设置缓存时间
			int expire = 60;
			if(model.getTimeout() != null && model.getTimeout() > 0){
				expire = model.getTimeout();
			}
			RedisUtils.setex(redisKey, expire, content);

			// 发送成功,设置发送历史记录值,数据库保留历史记录
			SendSmsHistory ssh = setHistory(model, channelId);
			ssh.setRequestParams(model.getPhones() + " ; ali_send_external_template_no: " + ti.getExternalTemplateNo()
					+ " ; ali_send_param:" + params + " ; mandao_send_content: " + content);
			ssh.setResultMsg("ali result, flag: " + flag + " ; mandao result, res: " + res);
			smsSendHistoryMapper.insert(ssh);
		} catch (Exception e) {
			logger.info("短信发送成功，缓存到redis，保存到数据库历史记录异常：" + e);
		}

		ResBodyData result = new ResBodyData();
		result.setStatus(SmsApiCode.SUCCESS);
		result.setMsg(SmsApiCode.getZhMsg(SmsApiCode.SMS_SEND_SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	/**
	 * 调用漫道发送普通短信
	 * 
	 * @param model
	 * @param content
	 * @param res
	 * @return
	 */
	private String sendMessageByZucp(SendMessageRequest model, String content, String res) {
		try {
			res = zucpService.send(model.getPhones(), content);
		} catch (SystemException e) {
			logger.info("漫道普通短信发送异常：" + e);
			throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER, BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
		}
		logger.info("漫道普通短信发送结果：" + res);
		try {
			if (Long.parseLong(res) < 0) {
				throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
			}
		} catch (NumberFormatException e) {
			logger.info("漫道发送普通短信结果异常：" + e);
			throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER, BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
		}
		return res;
	}

	@Override
	public ResBodyData sendSmsVerificationCode(SendCodeRequest model) {

		String redisKey = model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId() + model.getType()
				+ model.getSysKey();

		// 检查是否已在超时时间内，给该手机发送了短信
		String tempMsg = RedisUtils.get(redisKey);
		if (StringUtils.isNotEmpty(tempMsg)) {
			throw new ServiceException(SmsApiCode.REPEATING, BaseApiCode.getZhMsg(SmsApiCode.REPEATING));
		}

		// 生成6位随机数
		String randomNumber = String.valueOf((Math.random() * 9 + 1) * 100000).substring(0, 6);
		logger.info("生成6位随机数为：" + randomNumber);

		// 获取消息模板--这里获取到的是所有的模板信息的json数据
		String templateListJsonStr = templateInfoService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if (StringUtils.isEmpty(templateListJsonStr)) {
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE_LIST,
					BaseApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE_LIST));
		}

		// 根据模板ID获取短信模板
		TemplateInfo ti = getTemplateByKey(model.getTemplateId(), templateListJsonStr);
		if (ti == null || StringUtils.isEmpty(ti.getTemplateKey()) || StringUtils.isEmpty(ti.getTemplateContent())) {
			// 如果没有模板编号，或者模板内容，则抛异常
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE,
					BaseApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE));
		}

		// 替换模板内容参数
		String content = ti.getTemplateContent();
		content = content.replace("{VerificationCode}", randomNumber);
		if (StringUtils.isNotEmpty(model.getParams())) {
			content = replacesContent(model.getParams(), content);
		}

		// 转换发送给阿里大于的替换模板参数格式，以json格式
		String params = "";
		if (StringUtils.isNotEmpty(model.getParams())) {
			params = aliDaYuParamsToJson(true, randomNumber + "," + model.getParams());
		} else{
			params = aliDaYuParamsToJson(true, randomNumber);
		}

		// 开始发送短信
		String res = "-1000";
		String channelId = "";
		boolean flag = false;
		if ("1".equals(model.getSupplierId())) {
			// 只使用阿里大于发送
			flag = aliyunService.send(model.getPhones(), ti.getExternalTemplateNo(), params);
			logger.info("只使用阿里大于发送---阿里大于发送验证码短信结果flag：" + flag);
			if (!flag) {
				// 发送失败，直接抛出异常
				throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
			}
			channelId = "1";

		} else if ("2".equals(model.getSupplierId())) {
			// 只使用漫道发送
			res = sendCodeByZucp(model, content, res);
			channelId = "2";

		} else {
			// 首先阿里云发送发送短信，如果发送失败则调用漫道发送。 全部失败则返回失败信息。
			flag = aliyunService.send(model.getPhones(), ti.getExternalTemplateNo(), params);
			logger.info("不指定发送--阿里大于发送验证码短信结果flag：" + flag);
			if (flag) {
				channelId = "1";
			} else {
				res = sendCodeByZucp(model, content, res);
				channelId = "2";
			}
		}

		try {
			// 发送成功，缓存到redis，设置缓存时间
			int expire = 60;
			if(model.getTimeout() != null && model.getTimeout() > 0){
				expire = model.getTimeout();
			}
			RedisUtils.setex(redisKey, expire, randomNumber);
			// 发送成功,设置发送历史记录值,数据库保留历史记录
			SendSmsHistory ssh = setHistory(model, channelId);
			ssh.setRequestParams(model.getPhones() + " ; ali_send_external_template_no: " + ti.getExternalTemplateNo()
					+ " ; ali_send_param:" + params + " ; mandao_send_content: " + content);
			ssh.setResultMsg("ali result, flag: " + flag + " ; mandao result, res: " + res);
			smsSendHistoryMapper.insert(ssh);
		} catch (Exception e) {
			logger.info("验证码发送成功，保存到数据库历史记录异常：" + e);
		}

		// 发送成功，返回结果
		ResBodyData result = new ResBodyData();
		result.setStatus(SmsApiCode.SUCCESS);
		result.setMsg(SmsApiCode.getZhMsg(SmsApiCode.SEND_CODE_SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	/**
	 * 调用漫道发送验证码短信
	 * 
	 * @param model
	 * @param content
	 * @param res
	 * @return
	 */
	private String sendCodeByZucp(SendCodeRequest model, String content, String res) {
		try {
			res = zucpService.send(model.getPhones(), content);
		} catch (SystemException e) {
			logger.info("漫道发送验证码短信异常：" + e);
			throw new ServiceException(SmsApiCode.SEND_CODE_FAILUER,
					BaseApiCode.getZhMsg(SmsApiCode.SEND_CODE_FAILUER));
		}
		logger.info("漫道发送验证码短信结果：" + res);
		try {
			if (Long.parseLong(res) < 0) {
				throw new ServiceException(SmsApiCode.SEND_CODE_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SEND_CODE_FAILUER));
			}
		} catch (NumberFormatException e) {
			logger.info("漫道发送验证码短信结果异常：" + e);
			throw new ServiceException(SmsApiCode.SEND_CODE_FAILUER,
					BaseApiCode.getZhMsg(SmsApiCode.SEND_CODE_FAILUER));
		}
		return res;
	}

	@Override
	public ResBodyData checkSmsVerificationCode(CheckCodeRequest model) {

		String redisKey = model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId() + model.getType()
				+ model.getSysKey();
		String tempVerificationCode = RedisUtils.get(redisKey);

		if (StringUtils.isEmpty(tempVerificationCode)) {
			logger.info(model.getPhones() + "验证码已过期: " + model.getVerificationCode());
			throw new ServiceException(SmsApiCode.SMS_VALID_CODE_EXPIRED,
					BaseApiCode.getZhMsg(SmsApiCode.SMS_VALID_CODE_EXPIRED));
		}
		if (!StringUtils.equalsIgnoreCase(model.getVerificationCode().trim(), tempVerificationCode)) {
			logger.info(model.getPhones() + "验证码不匹配: " + model.getVerificationCode());
			throw new ServiceException(SmsApiCode.SMS_VALID_CODE_UNMATCHED,
					BaseApiCode.getZhMsg(SmsApiCode.SMS_VALID_CODE_UNMATCHED));
		}

		RedisUtils.del(redisKey);
		logger.info("结束校验短信验证码程序:" + model.getPhones());

		ResBodyData result = new ResBodyData();
		result.setStatus(SmsApiCode.SUCCESS);
		result.setMsg(SmsApiCode.getZhMsg(SmsApiCode.CHECK_CODE_SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	/**
	 * 根据模板ID获取短信模板
	 * 
	 * @param templateKey
	 *            模板ID
	 * 
	 * @param templateListJsonStr
	 *            所有的模板信息的json数据
	 * 
	 * @return
	 */
	private TemplateInfo getTemplateByKey(String templateKey, String templateListJsonStr) {
		List<TemplateInfo> list = JsonUtils.jsonToList(templateListJsonStr, TemplateInfo.class);
		if (list != null && !list.isEmpty()) {
			for (TemplateInfo info : list) {
				if (info.getTemplateKey().equals(templateKey)) {
					return info;
				}
			}
		}
		return null;
	}

	/**
	 * 替换模板中的参数
	 *
	 * @param params
	 * @param content
	 * @return
	 */
	private String replacesContent(String params, String content) {
		String result = content;
		if (StringUtils.isNotEmpty(params)) {
			String[] replaces = params.split(",");
			if (replaces != null && replaces.length > 0) {
				int count = StringUtils.countMatches(result, "{");
				if (replaces.length < count) {
					logger.info("替换短信模板内容异常," + "替换内容与替换参数不匹配, replaces=" + replaces + ",count=" + count);
					throw new ServiceException(SmsApiCode.PARAM_ERROR, SmsApiCode.getZhMsg(SmsApiCode.PARAM_ERROR));
				}
				for (int index = 0; index < replaces.length; index++) {
					// 需要检查参数 add by simon
					result = result.replace("{" + index + "}", replaces[index]);
				}
				return result;
			}
		}
		return result;
	}

	/**
	 * 转换发送给阿里大于的替换模板参数格式，以json格式
	 *
	 * @param params
	 * @return
	 */
	private String aliDaYuParamsToJson(boolean isCode, String params) {
		Map<String, String> map = new HashMap<>();
		String[] paramsStr = params.split(",");
		for (int i = 0; i < paramsStr.length; i++) {
			if (!isCode && !StringUtils.isEmpty(paramsStr[i])) {
				map.put("V" + i, paramsStr[i]);
			} else {
				if (i == 0) {
					map.put("VerificationCode", paramsStr[i]);
				} else {
					map.put("V" + i, paramsStr[i]);
				}
			}
		}
		return JsonUtils.beanToJson(map);
	}

	/**
	 * 设置发送短信的历史记录值
	 * 
	 * @param model
	 * @return
	 */
	private SendSmsHistory setHistory(SendMessageRequest model, String channelId) {
		SendSmsHistory ssh = new SendSmsHistory();
		ssh.setId(UUID.randomUUID().toString());
		ssh.setTemplateKey(model.getTemplateId());
		ssh.setCreateDate(new Date());
		ssh.setCreater(model.getPhones());
		ssh.setPhone(model.getPhones());
		ssh.setRemark(model.getParams());
		ssh.setChannelId(channelId);
		ssh.setClientId(model.getSysKey());
		return ssh;
	}

	/**
	 * 设置发送短信的历史记录值
	 * 
	 * @param model
	 * @return
	 */
	private SendSmsHistory setHistory(SendCodeRequest model, String channelId) {
		SendSmsHistory ssh = new SendSmsHistory();
		ssh.setId(UUID.randomUUID().toString());
		ssh.setTemplateKey(model.getTemplateId());
		ssh.setCreateDate(new Date());
		ssh.setCreater(model.getPhones());
		ssh.setPhone(model.getPhones());
		ssh.setRemark(model.getParams());
		ssh.setChannelId(channelId);
		ssh.setClientId(model.getSysKey());
		return ssh;
	}
}
