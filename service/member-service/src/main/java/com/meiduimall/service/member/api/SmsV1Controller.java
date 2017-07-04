package com.meiduimall.service.member.api;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.model.request.RequestCheckValidateCode;
import com.meiduimall.service.member.model.request.RequestGetValidateCode;
import com.meiduimall.service.member.service.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 短信相关API
 * @author chencong
 *
 */
@Api(value = "短信相关", description = "短信相关接口")  
@RestController
@RequestMapping("/member/member_service/v1")
public class SmsV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(SmsV1Controller.class);
	
	@Autowired
	private  SmsService  smsService;
	
	/**获取短信验证码*/
	@ApiOperation(value="获取短信验证码", notes="获取短信验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "获取短信验证码实体", required = true, dataType = "RequestGetValidateCode"),
	})
	@GetMapping(value = "/get_validate_code")
	ResBodyData getValidateCode(@Valid RequestGetValidateCode model) throws ClientProtocolException, IOException {
		logger.info("收到获取短信验证码API请求：",model.toString());
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		smsService.getValidateCode(model);
		return resBodyData;
	}
	
	/**校验短信验证码*/
	@ApiOperation(value="校验短信验证码", notes="校验短信验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "校验短信验证码实体", required = true, dataType = "RequestCheckValidateCode"),
	})
	@GetMapping(value = "/check_validate_code")
	ResBodyData checkValidateCode(@Valid RequestCheckValidateCode model) throws ClientProtocolException, IOException {
		logger.info("收到校验短信验证码API请求：",model.toString());
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		smsService.checkValidateCode(model);
		return resBodyData;
	}

}
