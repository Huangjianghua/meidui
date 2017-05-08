package com.meiduimall.application.mall.service.impl;

import java.math.BigDecimal;

import com.meiduimall.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.HttpRConst;
import com.meiduimall.application.mall.constant.OauthConst;
import com.meiduimall.application.mall.constant.PayConfig;
import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.application.mall.model.SystradePTrade;
import com.meiduimall.application.mall.service.TPPaymentService;
import com.meiduimall.application.mall.util.DateUtil;
import com.meiduimall.application.mall.util.GatewaySignUtil;
import com.meiduimall.application.mall.util.Logger;

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
	public ResponseBodyData payment(PaymentTrade paymentTrade, SystradePTrade objPTradeInfo){
		try {
			JSONObject bigJson = new JSONObject();
			String url = myProps.getRouteServiceUrl()+"/pay/payment-service/v1/payment";

			bigJson.put("memId", paymentTrade.getMemId());
			// app提交 wechatpay:微信  其他:支付宝
			// 支付服务的 0:微信 1: 支付宝
			if ("wechatpay".equals(paymentTrade.getPay_type())) {
				bigJson.put("payType", "0");
				bigJson.put("merchantId", PayConfig.MDAPP_MCHID); // 商家标识

				int totalMoney = objPTradeInfo.getTotalMoney().multiply(new BigDecimal(100)).intValue();
				bigJson.put("orderAmount", String.valueOf(totalMoney)); // 订单金额
				int money = new BigDecimal(paymentTrade.getMoney()).multiply(new BigDecimal(100)).intValue();
				bigJson.put("payAmount", String.valueOf(money));// 支付金额
				int useMoney = new BigDecimal(paymentTrade.getUse_money()).multiply(new BigDecimal(100)).intValue();
				bigJson.put("cashAmount", String.valueOf(useMoney)); // 余额
				
				bigJson.put("notifyUrl", myProps.getPayUrl()+"/md1gwmall/md1gw_access/v1/getWXPayNotify");// 回调地址
				if(null != paymentTrade.getWechat_pay_account_type()){
					if("1".equals(paymentTrade.getWechat_pay_account_type())){
						bigJson.put("merchantId", PayConfig.YGWAPP_MCHID); // 商家标识
						bigJson.put("notifyUrl", myProps.getPayUrl()+"/md1gwmall/md1gw_access/v1/getYgwWXPayNotify");// 回调地址
						bigJson.put("accountType", "1");// 1gw微信标识
					}
				}

			} else {
				bigJson.put("payType", "1");
				bigJson.put("body", objPTradeInfo.getPlatformId().toString());
				bigJson.put("subject", objPTradeInfo.getPlatformId().toString());
				bigJson.put("merchantId", PayConfig.ALIPAY_MER_ID); // 商家标识
				bigJson.put("orderAmount", String.valueOf(objPTradeInfo.getTotalMoney())); // 订单金额
				bigJson.put("payAmount", paymentTrade.getMoney());// 支付金额
				bigJson.put("cashAmount", String.valueOf(paymentTrade.getUse_money())); // 余额
				bigJson.put("notifyUrl", myProps.getPayUrl()+"/md1gwmall/md1gw_access/v1/getAliPayNotify");// 回调地址

			}

			bigJson.put("orderNo", objPTradeInfo.getPlatformId().toString()); // 订单号
			bigJson.put("orderTime", DateUtil.orderTime(objPTradeInfo.getCreatedTime().toString())); // 订单时间
			bigJson.put("tradeNo", paymentTrade.getPayment_id()); // 交易号
			bigJson.put("integral", paymentTrade.getUse_point()); // 积分

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
		    Logger.error("第三方支付系统错误:%s", e);
		    throw new ServiceException(MallApiCode.PAYMENT_ERROR, MallApiCode.getZhMsg(MallApiCode.PAYMENT_ERROR));
		}

	}

}
