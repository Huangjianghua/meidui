package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.application.usercenter.interceptor.ValInterceptor;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.ExternalRechargeService;
import com.meiduimall.core.ResBodyData;
/**
 * 外部会员充值接口
 * @author liuhailang
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class ExternalRechargeController {
	
	private static Logger logger = LoggerFactory.getLogger(ExternalRechargeController.class);
	@Autowired
	private ExternalRechargeService externalRechargeService;
	
	/**外部充值*/
	@RequestMapping(value = "/externalRecharge")
	public ResBodyData externalMemberRecharge(){
		ResBodyData resBodyData=null;
		JSONObject reqJson = ValRequest.apiReqData.get();
		resBodyData = ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0){
			return resBodyData;
		}
		logger.info("收到外部充值API请求：{}",this.changeJson(reqJson).toString());
		resBodyData=externalRechargeService.externalMemberRecharge(this.changeJson(reqJson));
		logger.info("外部充值API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	/**
	 * 修改JSON的KEY
	 * @param reqJson
	 * @return
	 */
	private JSONObject changeJson (JSONObject reqJson){
		JSONObject chjson = new JSONObject();
		if(reqJson.containsKey("md_user")){
			chjson.put("phone", reqJson.get("md_user"));
		}
		if(reqJson.containsKey(SysParamsConst.CLIENT_ID)){
			chjson.put("extCompanyCode", reqJson.get(SysParamsConst.CLIENT_ID));
		}
		if(reqJson.containsKey("biz_id")){
			chjson.put("extorderId", reqJson.get("biz_id"));
		}
		if(reqJson.containsKey("recharge_amout")){
			chjson.put("rechargeAmout", reqJson.get("recharge_amout"));
		}
		if(reqJson.containsKey("recharge_type")){
			chjson.put("accountType", reqJson.get("recharge_type"));
		}
		if(reqJson.containsKey("req_time")){
			chjson.put("timestamp", reqJson.get("req_time"));
		}
		if(reqJson.containsKey("sign")){
			chjson.put("sign", reqJson.get("sign"));
		}
		if(reqJson.containsKey("callback_url")){
			chjson.put("callbackUrl", reqJson.get("callback_url"));
		}
		return chjson;
	}

}
