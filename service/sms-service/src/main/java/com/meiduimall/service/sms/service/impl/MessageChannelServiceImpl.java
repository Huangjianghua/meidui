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

import com.meiduimall.core.Constants;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
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
		String channelListJsonStr = RedisUtils.get(key);
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
				throw new ServiceException(SmsApiCode.EXCEPTION_ACCESS_CHANNEL,
						SmsApiCode.getZhMsg(SmsApiCode.EXCEPTION_ACCESS_CHANNEL));
			}
		}
		return channelListJsonStr;
	}
}
