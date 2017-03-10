package com.meidui.sms.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meidui.sms.entity.MessageChannel;
import com.meidui.sms.entity.TemplateInfo;
import com.meidui.sms.mapper.MessageChannelMapper;
import com.meidui.sms.mapper.TemplateInfoMapper;
import com.meiduimall.Constants;
import com.meiduimall.FastJsonUtil;
import com.meiduimall.StringUtil;
import com.meiduimall.redis.util.JedisUtil;

@Service
public class MessageChannelService{
	
	private static Logger Logger = LoggerFactory.getLogger(MessageChannelService.class);

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
		
		String channelListJsonStr =JedisUtil.getJedisInstance().execGetFromCache(key);
		if(StringUtil.isEmptyByString(channelListJsonStr)){
			try{
				List<MessageChannel> channelList = messageChannelMapper.getChannelList();
				if(null != channelList && channelList.size() > 0){
					channelListJsonStr =FastJsonUtil.serialize(channelList);
					JedisUtil.getJedisInstance().execSetexToCache(key, Constants.REDIS_NINETY, channelListJsonStr);
				}
				
			} catch (Exception e) {
				Logger.error("获取渠道异常：%s", e.toString());
				e.printStackTrace();
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
		String templateListJsonStr = JedisUtil.getJedisInstance().execGetFromCache(key);
		if(StringUtil.isEmptyByString(templateListJsonStr)){
			try {
				List<TemplateInfo> templateInfo = templateInfoMapper.getTemplateInfoList();
				if(null != templateInfo && templateInfo.size() > 0){
					templateListJsonStr =FastJsonUtil.serialize(templateInfo);
					JedisUtil.getJedisInstance().execSetexToCache(key, Constants.REDIS_TENMINUTE, templateListJsonStr);
				}
				
			} catch (Exception e) {
				Logger.error("获取模板异常：%s", e.toString());
				e.printStackTrace();
			}
		}
		return templateListJsonStr;
		
	}

}
