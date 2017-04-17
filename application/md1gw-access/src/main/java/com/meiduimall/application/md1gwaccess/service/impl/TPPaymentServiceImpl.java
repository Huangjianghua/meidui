package com.meiduimall.application.md1gwaccess.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.application.md1gwaccess.config.MyProps;
import com.meiduimall.application.md1gwaccess.constant.HttpRConst;
import com.meiduimall.application.md1gwaccess.constant.OauthConst;
import com.meiduimall.application.md1gwaccess.constant.PayConfig;
import com.meiduimall.application.md1gwaccess.constant.ResponseBodyData;
import com.meiduimall.application.md1gwaccess.model.PaymentTrade;
import com.meiduimall.application.md1gwaccess.model.SystradePTrade;
import com.meiduimall.application.md1gwaccess.service.TPPaymentService;
import com.meiduimall.application.md1gwaccess.util.DateUtil;
import com.meiduimall.application.md1gwaccess.util.GatewaySignUtil;
import com.meiduimall.application.md1gwaccess.util.Logger;

import net.sf.json.JSONObject;

@Component
public class TPPaymentServiceImpl implements TPPaymentService {

 

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MyProps myProps; 
	
	/**
	 * 混合支付
	 */
	@Override
	public ResponseBodyData Payment(PaymentTrade paymentTrade, SystradePTrade obj_p_trade_info) throws Exception {
		try {
			JSONObject bigJson = new JSONObject();
			String url = myProps.getRouteServiceUrl()+"/pay/payment-service/v1/payment";

			bigJson.put("memId", paymentTrade.getMemId());
			// app提交 wechatpay:微信  其他:支付宝
			// 支付服务的 0:微信 1: 支付宝
			if (paymentTrade.getPay_type().equals("wechatpay")) {
				bigJson.put("payType", "0");
				bigJson.put("merchantId", PayConfig.MDAPP_Mchid); // 商家标识

				int TotalMoney = obj_p_trade_info.getTotalMoney().multiply(new BigDecimal(100)).intValue();
				bigJson.put("orderAmount", String.valueOf(TotalMoney)); // 订单金额
				int Money = new BigDecimal(paymentTrade.getMoney()).multiply(new BigDecimal(100)).intValue();
				bigJson.put("payAmount", String.valueOf(Money));// 支付金额
				int Use_money = new BigDecimal(paymentTrade.getUse_money()).multiply(new BigDecimal(100)).intValue();
				bigJson.put("cashAmount", String.valueOf(Use_money)); // 余额
				
				bigJson.put("notifyUrl", myProps.getPayUrl()+"/md1gwmall/md1gw_access/v1/getWXPayNotify");// 回调地址
				if(null != paymentTrade.getWechat_pay_account_type()){
					if(paymentTrade.getWechat_pay_account_type().equals("1")){
						bigJson.put("merchantId", PayConfig.YGWAPP_Mchid); // 商家标识
						bigJson.put("notifyUrl", myProps.getPayUrl()+"/md1gwmall/md1gw_access/v1/getYgwWXPayNotify");// 回调地址
						bigJson.put("accountType", "1");// 1gw微信标识
					}
				}

			} else {
				bigJson.put("payType", "1");
				bigJson.put("body", obj_p_trade_info.getPlatformId().toString());
				bigJson.put("subject", obj_p_trade_info.getPlatformId().toString());
				bigJson.put("merchantId", PayConfig.alipay_mer_id); // 商家标识
				bigJson.put("orderAmount", String.valueOf(obj_p_trade_info.getTotalMoney())); // 订单金额
				bigJson.put("payAmount", paymentTrade.getMoney());// 支付金额
				bigJson.put("cashAmount", String.valueOf(paymentTrade.getUse_money())); // 余额
				bigJson.put("notifyUrl", myProps.getPayUrl()+"/md1gwmall/md1gw_access/v1/getAliPayNotify");// 回调地址

			}

			bigJson.put("orderNo", obj_p_trade_info.getPlatformId().toString()); // 订单号
			bigJson.put("orderTime", DateUtil.orderTime(obj_p_trade_info.getCreatedTime().toString())); // 订单时间
			bigJson.put("tradeNo", paymentTrade.getPayment_id()); // 交易号
			bigJson.put("integral", paymentTrade.getUse_point().toString()); // 积分

			bigJson.put(OauthConst.CLIENT_ID, OauthConst.CLIENT_ID_VALUE);
			bigJson.put(OauthConst.TIMESATAMP, String.valueOf(System.currentTimeMillis()));
			Logger.info("签名之前:%s", bigJson);
			bigJson.put(OauthConst.SIGN, GatewaySignUtil.buildsign(OauthConst.SECRETKEY_VALUE, bigJson));
			Logger.info("签名之后:%s", bigJson);
			
			
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
			headers.setContentType(type);
		 
			Logger.info("组装发送数据:%s", bigJson);
			
			HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(bigJson, headers);
			JSONObject postForObject = restTemplate.postForObject(url, formEntity, JSONObject.class);
			
			Logger.info("请求第三方支付结果:%s", postForObject);

			if (postForObject.getInt("status") == 0) {
				return new ResponseBodyData(postForObject.get("data"));
			} else {
				return new ResponseBodyData(11, postForObject.getString("msg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		    Logger.info("第三方支付系统错误:%s", e.getMessage());
		    throw new RuntimeException("第三方支付系统错误!"); 
		}

	}

}
