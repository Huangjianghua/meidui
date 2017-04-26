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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.sms.constant.SysConstant;
import com.meiduimall.service.sms.entity.MessageChannel;
import com.meiduimall.service.sms.service.AliyunService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


@Service
public class AliyunServiceImpl implements AliyunService {

	private static Logger logger = LoggerFactory.getLogger(AliyunServiceImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	private MessageChannelServiceImpl messageChannelService;

	@Override
	public boolean send(String mobile, String tid, String context) {

		String url = env.getProperty("aliyun.url");
		String appKey = env.getProperty("aliyun.appKey");
		String appSecret = env.getProperty("aliyun.appSecret");
		String signName = env.getProperty("aliyun.signName");

		if (StringUtils.isEmpty(tid)) {
			logger.error("模板ID为空,无法使用阿里大于发送短短信！");
			return false;
		}

		String channelJsonStr = messageChannelService.getChannelList(SysConstant.MESSAGE_CHANNEL_KEY);
		if (StringUtils.isNotEmpty(channelJsonStr)) {
			List<MessageChannel> channelList = JsonUtils.jsonToList(channelJsonStr, MessageChannel.class);
			for (MessageChannel c : channelList) {
				if (SysConstant.MESSAGE_CHANNEL_ALI_KEY.equals(c.getChannelKey())) {
					url = c.getRequstUrl();
					appKey = c.getUserName();
					appSecret = c.getPassWord();
					break;
				}
			}
		}

		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		req.setSmsParamString(context);
		req.setRecNum(mobile);
		req.setSmsTemplateCode(tid);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (com.taobao.api.ApiException e) {
			logger.error("阿里云平台短信发送异常: " + e);
		}
		return rsp != null && (rsp.getBody().indexOf("\"success\":true") != -1
				|| rsp.getBody().indexOf("isv.BUSINESS_LIMIT_CONTROL") != -1);
	}
}
