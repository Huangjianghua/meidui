package com.meiduimall.service.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.sms.request.WXMsgOnPaySuccessRequest;
import com.meiduimall.service.sms.service.WeixinService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/notify/short_msg_service/v1")
public class WeixinController {

	@Autowired
	private WeixinService weixinService;

	/**
	 * 支付成功，发送微信模板消息 
	 * 
	 * @param model
	 *            请求参数封装对象
	 * @return 发送结果
	 */
	@ApiOperation(value="发送微信模板消息", notes="发送微信模板消息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "微信模板消息实体", required = true, dataType = "WXMsgOnPaySuccessRequest")
	})
	@RequestMapping("/send_weixin_msg_on_pay")
	public ResBodyData sendTemplateMessageOnPaySuccess(@Validated WXMsgOnPaySuccessRequest model) {
		return weixinService.sendTemplateMessageOnPaySuccess(model);
	}
}
