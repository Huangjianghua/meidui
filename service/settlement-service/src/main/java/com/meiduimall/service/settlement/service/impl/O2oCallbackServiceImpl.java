package com.meiduimall.service.settlement.service.impl;


import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.settlement.common.O2oApiConstants;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.SmsReqDTO;
import com.meiduimall.service.settlement.service.O2oCallbackService;
import com.meiduimall.service.settlement.service.SmsService;
import com.meiduimall.service.settlement.util.ConnectionUrlUtil;
import com.meiduimall.service.settlement.util.DateUtil;

@Service
public class O2oCallbackServiceImpl implements O2oCallbackService{
	
	private static final Logger log=LoggerFactory.getLogger(O2oCallbackServiceImpl.class);
	
	@Autowired
	private SmsService smsService;

	@Override
	public boolean informSettlementStatus(Collection<String> orderSns, Integer statusCode) {
		
		boolean isSuccess=true;
		String statusCodeMsg=ShareProfitUtil.O2O_SETTLEMENT_STATUS_CODE_MAP.get(statusCode)==null?"":ShareProfitUtil.O2O_SETTLEMENT_STATUS_CODE_MAP.get(statusCode);
		try{
			JSONObject resultObj = ConnectionUrlUtil.httpRequest(buildUrl4InformSettlementStatus(orderSns,statusCode), ShareProfitUtil.REQUEST_METHOD_POST, null);
			if(resultObj.getString("status_code").equals("0")){
				log.info("通知订单结算状态给O2O成功!orderSns:{},statusCode:{}",StringUtils.join(orderSns, ","),statusCodeMsg);
			}else{
				isSuccess=false;
				log.error("通知订单结算状态给O2O失败!orderSns:{},statusCode:{}",StringUtils.join(orderSns, ","),statusCodeMsg);
			}

		}catch(Exception e){
			log.error("informSettlementStatus(),通知结算状态给O2O出现异常:orderSn:{},statusCode:{}",StringUtils.join(orderSns, ","),statusCodeMsg);
			isSuccess=false;
		}
		
		//1.发送邮件或短信,修复后需要O2O手动更新订单状态
		if(!isSuccess){
			//注意，params中,逗号是参数分隔符,用于将分割后的参数填充到短信模板。
			String params="通知订单结算状态给O2O失败;status_code:"+statusCodeMsg;
			SmsReqDTO smsReqDTO = new SmsReqDTO(ShareProfitUtil.AUTHORIZED_MAP.get(ShareProfitUtil.SMS_PHONES),
					ShareProfitUtil.TEMPLATE_ID_O2O_1009,params,"");

			try {
				boolean flag = smsService.sendMessage(smsReqDTO);
				if(flag){
					log.info("发送短信通知订单结算状态给O2O成功,status_code:{}",statusCodeMsg);
				}else{
					log.info("发送短信通知订单结算状态给O2O失败,status_code:{}",statusCodeMsg);
				}
			} catch (Exception e1) {
				log.error("发送短信通知订单结算状态给O2O失败,status_code:{}",statusCodeMsg,e1);
			}
		}

		return isSuccess;
	}
	
	
	private String buildUrl4InformSettlementStatus(Collection<String> orderSns, Integer statusCode) {
		
		String apiUrl=ShareProfitUtil.AUTHORIZED_MAP.get(O2oApiConstants.KEY_O2O_API_URL);
		String apiPath=ShareProfitUtil.AUTHORIZED_MAP.get(O2oApiConstants.KEY_O2O_API_SAVE_ORDER_BILL_STATUS);
		String apiKey=ShareProfitUtil.AUTHORIZED_MAP.get(O2oApiConstants.KEY_O2O_API_KEY);
		
		String orderSnsStr=StringUtils.join(orderSns, ",");
		String params = "?orderSns=" + orderSnsStr + "&statusCode=" + statusCode  + "&apiKey="+apiKey;
		
		return apiUrl+apiPath+params;
	}


	@Override
	public String addProxyFee(EcmAgent areaAgent, double amount) throws Exception {
		String payinId = null;
		JSONObject resultObj = ConnectionUrlUtil.httpRequest(buildUrl4AddProxyFee(areaAgent,amount), ShareProfitUtil.REQUEST_METHOD_POST, null);
		if(resultObj.getString("status_code").equals("0")){
			log.info("回调o2o，更新余款，抵扣保证金插入缴费记录成功");
			payinId = resultObj.getString("result");
		}else{
			log.error("回调o2o更新余款、抵扣保证金插入缴费记录失败,agentNo:{}",areaAgent.getAddAgentNo());
			throw new Exception("回调o2o更新余款、抵扣保证金插入缴费记录失败");
		}
		return payinId;
	}
	
	
	/**
	 * 组装传递参数
	 * @param areaAgent
	 * @param amount
	 * @return
	 */
	private String buildUrl4AddProxyFee(EcmAgent areaAgent, double amount) {
		
		String apiUrl = ShareProfitUtil.AUTHORIZED_MAP.get(O2oApiConstants.KEY_O2O_API_URL);
		String apiPath = ShareProfitUtil.AUTHORIZED_MAP.get(O2oApiConstants.KEY_O2O_API_ADD_PROXY_FEE);
		String apiKey = ShareProfitUtil.AUTHORIZED_MAP.get(O2oApiConstants.KEY_O2O_API_KEY);
		
		int addTime = DateUtil.getCurrentTimeSec();
		
		String params = "?agentNo=" + areaAgent.getAddAgentNo() + "&amount=" + amount + "&operateUser=0"
				+ "&verifyStatus=VERIFYED" + "&verifyTime="
				+ addTime + "&verifyUser=0" + "&addTime="
				+ addTime + "&type=1" + "&apiKey=" + apiKey;
		return apiUrl + apiPath + params;
	}
	

}
