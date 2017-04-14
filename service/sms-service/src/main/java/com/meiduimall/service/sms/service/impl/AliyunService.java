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
import com.meiduimall.service.sms.SysConstant;
import com.meiduimall.service.sms.service.IAliyunService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meiduimall.service.sms.entity.MessageChannel;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 阿里云短信平台
 * @author pc
 */
@Service
public class AliyunService implements IAliyunService {
	private static Logger logger= LoggerFactory.getLogger(AliyunService.class);

	private static String appKey = "23438487";
	private static String appSecret = "17042e86f2ad304b016d4ed1578f26e7";
	private static String url = "http://gw.api.taobao.com/router/rest";
	private static String sign_name = "美兑壹购物";
	
	@Autowired
	private MessageChannelService messageChannelService;

	
	@Override
	public boolean Send(String mobile, String tid, String context)  {
		if(StringUtils.isEmpty(tid)){
			logger.error("模板ID为空,无法发送短消息");
			return false;
		}
		String channelJsonStr = messageChannelService.getChannelList(SysConstant.MESSAGE_CHANNEL_KEY);
		if(StringUtils.isNotEmpty(channelJsonStr)){
			List<MessageChannel> channelList = JsonUtils.jsonToList(channelJsonStr, MessageChannel.class);
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
		req.setSmsFreeSignName(sign_name);
		req.setSmsParamString(context);
		req.setRecNum(mobile);
		req.setSmsTemplateCode(tid);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (com.taobao.api.ApiException e) {
		    logger.error("阿里云平台短信发送异常:{}",e.getMessage());
		}
		if (rsp.getBody().indexOf("\"success\":true") != -1
				|| rsp.getBody().indexOf("isv.BUSINESS_LIMIT_CONTROL") != -1) {
			return true;
		}
		return false;
	}

}
