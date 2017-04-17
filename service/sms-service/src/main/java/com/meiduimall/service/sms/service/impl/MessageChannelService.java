/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.service.impl;

import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.util.DateUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.SmsApiCode;
import com.meiduimall.service.sms.entity.MessageChannel;
import com.meiduimall.service.sms.entity.TemplateInfo;
import com.meiduimall.service.sms.mapper.MessageChannelMapper;
import com.meiduimall.service.sms.mapper.TemplateInfoMapper;

@Service
public class MessageChannelService{

	private static Logger logger = LoggerFactory.getLogger(MessageChannelService.class);

	@Autowired
	private MessageChannelMapper messageChannelMapper;
	
	@Autowired
	private TemplateInfoMapper templateInfoMapper;
	
	/**
	 * 获取短信渠道并转成json字符串
	 * @param key
	 * @return
	 */
	public String getChannelList(String key){
		String channelListJsonStr = RedisUtils.get(key);
		if(StringUtils.isEmpty(channelListJsonStr)){
			try{
				List<MessageChannel> channelList = messageChannelMapper.getChannelList();
				if(null != channelList && (!channelList.isEmpty())){
					channelListJsonStr = JsonUtils.beanToJsonAndFmDate(channelList);
					RedisUtils.setex(key, DateUtils.parseDuration("1mn"),channelListJsonStr);
				}
			} catch (Exception e) {
				logger.error("获取渠道异常：{}", e);
				throw new ServiceException(SmsApiCode.EXCEPTION_ACCESS_CHANNEL,SmsApiCode.getZhMsg(SmsApiCode.EXCEPTION_ACCESS_CHANNEL));
			}
		}
		return channelListJsonStr;
	}
	
	
	/**
	 * 获取短信模板并转成json字符串
	 * @param key
	 * @return
	 */
	public String getTemplateList(String key){
		String templateListJsonStr = RedisUtils.get(key);
		if(StringUtils.isEmpty(templateListJsonStr)){
			try {
				List<TemplateInfo> templateInfo = templateInfoMapper.getTemplateInfoList();
				if(null != templateInfo && (!templateInfo.isEmpty())){
					templateListJsonStr = JsonUtils.beanToJsonAndFmDate(templateInfo);
					RedisUtils.setex(key, DateUtils.parseDuration("10mn"),templateListJsonStr);
				}
			} catch (Exception e) {
				logger.error("获取模板异常：{}", e);
				throw new ServiceException(SmsApiCode.EXCEPTION_ACCESS_TEMPLATE,SmsApiCode.getZhMsg(SmsApiCode.EXCEPTION_ACCESS_TEMPLATE));
			}
		}
		return templateListJsonStr;
		
	}

}
