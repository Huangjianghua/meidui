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

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.sms.entity.MessageChannel;
import com.meiduimall.service.sms.mapper.MessageChannelMapper;
import com.meiduimall.service.sms.mapper.TemplateInfoMapper;
import com.meiduimall.service.sms.util.Time;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.sms.entity.TemplateInfo;

@Service
public class MessageChannelService{

	private static Logger logger = LoggerFactory.getLogger(MessageChannelService.class);

	@Autowired
	private MessageChannelMapper messageChannelMapper;
	
	@Autowired
	private TemplateInfoMapper templateInfoMapper;
	
//	RedisTemplate<String, MessageChannel> redisTemplate;
	
//	@Autowired
//	private RedisService redisService;
//
//	public RedisTemplate<String, MessageChannel> getRedisTemplate() {
//	        return redisTemplate;
//	}
	  
//    public void setRedisTemplate(RedisTemplate<String, MessageChannel> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

/*	public void put(MessageChannel channel) {
		redisTemplate.opsForHash().put(channel.getObjectKey(), channel.getKey(), channel);  
		
	}

	public void delete(MessageChannel channel) {
		 redisTemplate.opsForHash().delete(channel.getObjectKey(), channel.getKey());  
	}

	public MessageChannel get(MessageChannel channel) {
		return (MessageChannel) redisTemplate.opsForHash().get(channel.getObjectKey(), channel.getKey());  
	}  */
	
	/**
	 * 获取短信渠道并转成json字符串
	 * @param key
	 * @return
	 */
	public String getChannelList(String key){
		String channelListJsonStr = JedisUtil.getJedisInstance().execGetFromCache(key);
		if(StringUtils.isEmpty(channelListJsonStr)){
			try{
				List<MessageChannel> channelList = messageChannelMapper.getChannelList();
				if(null != channelList && channelList.size() > 0){
					channelListJsonStr = JsonUtils.beanToJsonAndFmDate(channelList);
					JedisUtil.getJedisInstance().execSetexToCache(key, Time.parseDuration("1mn"),channelListJsonStr);
				}
				
			} catch (Exception e) {
				logger.error("获取渠道异常：%s", e.toString());
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
		if(StringUtils.isEmpty(templateListJsonStr)){
			try {
				List<TemplateInfo> templateInfo = templateInfoMapper.getTemplateInfoList();
				if(null != templateInfo && templateInfo.size() > 0){
					templateListJsonStr = JsonUtils.beanToJsonAndFmDate(templateInfo);
					JedisUtil.getJedisInstance().execSetexToCache(key, Time.parseDuration("10mn"),templateListJsonStr);
				}
				
			} catch (Exception e) {
				logger.error("获取模板异常：%s", e.toString());
				e.printStackTrace();
			}
		}
		return templateListJsonStr;
		
	}

}
