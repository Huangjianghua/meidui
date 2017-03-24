package com.meiduimall.service.sms.service.impl;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.meiduimall.exception.ApiException;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.JacksonUtil;
import com.meiduimall.service.sms.SysConstant;
import com.meiduimall.service.sms.entity.MessageChannel;
import com.meiduimall.service.sms.service.AliyunService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


@Service
public class AliyunServiceImpl implements AliyunService{
	
	private static Logger Logger = LoggerFactory.getLogger(AliyunServiceImpl.class);

	private static String appKey = "23438487";
	private static String appSecret = "17042e86f2ad304b016d4ed1578f26e7";
	private static String url = "http://gw.api.taobao.com/router/rest";
	
	@Autowired
	private MessageChannelServiceImpl messageChannelService;

	public boolean Send(String mobile, String tid, String context) throws ApiException {
		
		if(Strings.isNullOrEmpty(tid)){
			return false;
		}
		String channelJsonStr = messageChannelService.getChannelList(SysConstant.MESSAGE_CHANNEL_KEY);
		if(!Strings.isNullOrEmpty(channelJsonStr)){
			List<MessageChannel> channelList = JacksonUtil.jsonToList(channelJsonStr, MessageChannel.class);
			for(MessageChannel c : channelList){
				if(SysConstant.MESSAGE_TEMPLATE_ALI_KEY.equals(c.getChannelKey())){
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
		req.setSmsFreeSignName("美兑");
		req.setSmsParamString(context);
		req.setRecNum(mobile);
		req.setSmsTemplateCode(tid);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (com.taobao.api.ApiException e) {
			Logger.error("阿里短信发送接口异常信息:{}",ExceptionUtils.getFullStackTrace(e));
		}
		if (rsp.getResult().getSuccess()) {
			return true;
		}
		return false;
	}

}
