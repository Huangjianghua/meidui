package com.meiduimall.service.member.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.model.request.BindingWeixin;

public interface WeixinInfoService {

	/**
	 * 绑定会员微信openID
	 * 
	 * @param model
	 *            用户手机号和微信openID封装对象
	 * @return 绑定结果
	 */
	ResBodyData insertWeixinInfo(BindingWeixin model);
}
