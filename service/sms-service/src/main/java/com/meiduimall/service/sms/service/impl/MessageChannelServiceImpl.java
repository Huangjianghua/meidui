package com.meiduimall.service.sms.service.impl;

import java.util.List;

import com.meiduimall.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.Constants;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.entity.MessageChannel;
import com.meiduimall.service.sms.mapper.SmsMessageChannelMapper;
import com.meiduimall.service.sms.service.MessageChannelService;

@Service
public class MessageChannelServiceImpl implements MessageChannelService {

	private static Logger logger = LoggerFactory.getLogger(MessageChannelServiceImpl.class);

	@Autowired
	private SmsMessageChannelMapper smsMessageChannelMapper;

	@Override
	public String getChannelList(String key) {
		// 先从redis缓存中取出所有的第三方短信发送渠道列表，取不到再从数据库取
		String channelListJsonStr = null;
		// 这里需要对缓存剩余时间做判断，有时候会出现剩余时间为-1的情况，需要删除这条数据
		Long ttl = RedisUtils.ttl(key);
		if (ttl > 0) {
			channelListJsonStr = RedisUtils.get(key);
		} else {
			RedisUtils.del(key);
		}
		if (StringUtils.isEmpty(channelListJsonStr)) {
			try {
				// 从数据库查询所有的第三方短信发送渠道列表
				List<MessageChannel> list = smsMessageChannelMapper.getChannelList();
				if (null != list && !list.isEmpty()) {
					channelListJsonStr = JsonUtils.beanToJsonAndFmDate(list);
					RedisUtils.setex(key, Constants.REDIS_NINETY, channelListJsonStr);
				}
			} catch (Exception e) {
				logger.error("获取第三方短信发送渠道列表异常： " + e);
				throw new ServiceException(SmsApiCode.EXCEPTION_ACCESS_CHANNEL);
			}
		}
		return channelListJsonStr;
	}
}
