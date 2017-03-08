package com.meidui.shortmsg.service.impl;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meidui.shortmsg.entity.MessageChannel;
import com.meidui.shortmsg.exception.ApiException;
import com.meidui.shortmsg.service.IAliyunService;
import com.meidui.shortmsg.util.JsonHelper;
import com.meidui.shortmsg.util.Logger;
import com.meidui.shortmsg.util.StringUtil;
import com.meidui.shortmsg.util.SysConstant;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 阿里云短信平台
 * @author pc
 * 
 *
 */
@Service
public class AliyunService implements IAliyunService{

	private static String appKey = "23438487";
	private static String appSecret = "17042e86f2ad304b016d4ed1578f26e7";
	private static String url = "http://gw.api.taobao.com/router/rest";
	
	@Autowired
	private MessageChannelService messageChannelService;

	public boolean Send(String mobile, String tid, String context) throws ApiException {
		Logger.info("----------------- send ali dayu message start ----------------");
		if(StringUtil.isEmptyByString(tid)){
			return false;
		}
		String channelJsonStr = messageChannelService.getChannelList(SysConstant.MESSAGE_CHANNEL_KEY);
		if(!StringUtil.isEmptyByString(channelJsonStr)){
			List<MessageChannel> channelList = (List<MessageChannel>) JsonHelper.getObjectList(channelJsonStr, MessageChannel.class);
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
			e.printStackTrace();
		}
		Logger.info("----------------- send ali dayu message end ----------------");
        
		Logger.info(rsp.getBody());
		Logger.info("大于返回："+ToStringBuilder.reflectionToString(rsp));
		if (rsp.getBody().indexOf("\"success\":true") != -1
				|| rsp.getBody().indexOf("isv.BUSINESS_LIMIT_CONTROL") != -1) {
			return true;
		}
		return false;
	}

}
