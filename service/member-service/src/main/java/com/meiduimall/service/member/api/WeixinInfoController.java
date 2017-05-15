package com.meiduimall.service.member.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.model.request.RequestBindingWeixin;
import com.meiduimall.service.member.service.WeixinInfoService;

/**
 * 微信资料相关操作
 * 
 * @author yangchang
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class WeixinInfoController {

	@Autowired
	private WeixinInfoService weixinInfoService;

	@RequestMapping("/bindingWeixinOpenID")
	public ResBodyData bindingWeixinOpenID(RequestBindingWeixin model) {
		return weixinInfoService.bindingWeixinOpenID(model);
	}

	@RequestMapping("/getOpenIDByPhone")
	public ResBodyData getOpenIDByPhone(String phone) {
		return weixinInfoService.getOpenIDByPhone(phone);
	}
}
