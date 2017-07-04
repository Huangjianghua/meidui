package com.meiduimall.service.member.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.model.request.RequestBindingWeixin;
import com.meiduimall.service.member.service.WeixinInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**

 * 微信资料相关API
 * @author yangchang
 *
 */
@Api(value = "微信资料相关", description = "微信资料相关接口")
@RestController
@RequestMapping("/member/member_service/v1")
public class WeixinInfoController {

	@Autowired
	private WeixinInfoService weixinInfoService;

	/**
	 * 将微信OpenId与会员进行绑定
	 * @param model 会员手机号和openID
	 * @return 数据对象
	 */
	@ApiOperation(value="将微信OpenId与会员进行绑定", notes="将微信OpenId与会员进行绑定")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "将微信OpenId与会员进行绑定实体", required = true, dataType = "RequestBindingWeixin"),
	})
	@PostMapping("/bindingWeixinOpenID")
	public ResBodyData bindingWeixinOpenID(@Validated RequestBindingWeixin model) {
		return weixinInfoService.bindingWeixinOpenID(model);
	}

	/**
	 * 根据会员手机号，获取会员openId相关信息
	 * @param phone 会员手机号
	 * @return 数据对象
	 */
	@ApiOperation(value="根据会员手机号，获取会员openId相关信息", notes="根据会员手机号，获取会员openId相关信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "phone", value = "根据会员手机号，获取会员openId相关信息实体", required = true, dataType = "String"),
	})
	@PostMapping("/getOpenIDByPhone")
	public ResBodyData getOpenIDByPhone(String phone) {
		if (StringUtils.isBlank(phone)) {
			throw new ApiException(ConstApiStatus.REQUIRED_PARAM_EMPTY);
		}
		return weixinInfoService.getOpenIDByPhone(phone);
	}
}
