package com.meidui.shortmsg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.meidui.shortmsg.cache.redis.RedisService;
import com.meidui.shortmsg.entity.MessageChannel;
import com.meidui.shortmsg.entity.TemplateInfo;
import com.meidui.shortmsg.mapper.MessageChannelMapper;
import com.meidui.shortmsg.mapper.TemplateInfoMapper;
import com.meidui.shortmsg.util.JsonHelper;
import com.meidui.shortmsg.util.Logger;
import com.meidui.shortmsg.util.StringUtil;

@Service
public class MessageChannelService{

	@Autowired
	private MessageChannelMapper messageChannelMapper;
	
	@Autowired
	private TemplateInfoMapper templateInfoMapper;
	
	RedisTemplate<String, MessageChannel> redisTemplate;
	
	@Autowired
	private RedisService redisService;
	
	public RedisTemplate<String, MessageChannel> getRedisTemplate() {  
	        return redisTemplate;  
	}  
	  
    public void setRedisTemplate(RedisTemplate<String, MessageChannel> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  

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
		String channelListJsonStr = (String) redisService.get(key);
		if(StringUtil.isEmptyByString(channelListJsonStr)){
			try{
				List<MessageChannel> channelList = messageChannelMapper.getChannelList();
				if(null != channelList && channelList.size() > 0){
					channelListJsonStr = JsonHelper.beanToJsonAndFmDate(channelList);
					redisService.set(key, channelListJsonStr, "1mn");
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
		String templateListJsonStr = (String) redisService.get(key);
		if(StringUtil.isEmptyByString(templateListJsonStr)){
			try {
				List<TemplateInfo> templateInfo = templateInfoMapper.getTemplateInfoList();
				if(null != templateInfo && templateInfo.size() > 0){
					templateListJsonStr = JsonHelper.beanToJsonAndFmDate(templateInfo);
					redisService.set(key, templateListJsonStr, "10mn");
				}
				
			} catch (Exception e) {
				Logger.error("获取模板异常：%s", e.toString());
				e.printStackTrace();
			}
		}
		return templateListJsonStr;
		
	}

}
