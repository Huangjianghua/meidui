package com.meiduimall.service.member.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.model.request.RequestBindingWeixin;

public interface WeixinInfoService {

	/**
	 * 绑定会员微信openID
	 * 
	 * @param model
	 *            用户手机号和微信openID封装对象
	 * @return 绑定结果
	 */
	ResBodyData bindingWeixinOpenID(RequestBindingWeixin model);

	/**
	 * 根据会员手机号获取会员信息与openID
	 * 
	 * @param phone
	 *            会员手机号
	 * @return 会员信息
	 */
	ResBodyData getOpenIDByPhone(String phone);
}
