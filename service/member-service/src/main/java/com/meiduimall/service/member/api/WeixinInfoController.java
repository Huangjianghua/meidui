package com.meiduimall.service.member.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.member.constant.ApiStatusConst;
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

	/**
	 * 绑定会员与会员的微信openID
	 * 
	 * @param model
	 *            请求参数封装对象
	 * @return 绑定结果
	 */
	@RequestMapping("/bindingWeixinOpenID")
	public ResBodyData bindingWeixinOpenID(@Validated RequestBindingWeixin model) {
		return weixinInfoService.bindingWeixinOpenID(model);
	}

	/**
	 * 根据会员手机号获取会员的微信openID和会员的部分信息
	 * 
	 * @param phone
	 *            会员手机号
	 * @return 获取结果
	 */
	@RequestMapping("/getOpenIDByPhone")
	public ResBodyData getOpenIDByPhone(String phone) {
		if (StringUtils.isBlank(phone)) {
			throw new ApiException(ApiStatusConst.REQUIRED_PARAM_EMPTY);
		}
		return weixinInfoService.getOpenIDByPhone(phone);
	}
}
