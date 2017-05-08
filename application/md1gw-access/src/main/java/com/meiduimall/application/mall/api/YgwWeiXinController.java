package com.meiduimall.application.mall.api;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.constant.PayConfig;
import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.constant.SysParaNameConst;
import com.meiduimall.application.mall.model.EctoolsPaymentsSucc;
import com.meiduimall.application.mall.service.PaymentService;
import com.meiduimall.application.mall.util.DateUtil;
import com.meiduimall.application.mall.util.Logger;
import com.meiduimall.application.mall.util.MD5Util;
import com.meiduimall.application.mall.util.XMLUtil;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1")
public class YgwWeiXinController {
 
	
//	{transaction_id=1009530574201503240036299496,   //交易号
//			nonce_str=1002477130, 
//			bank_type=CCB_CREDIT,     //银行类型
//			openid=o-HREuJzRr3moMvv990VdfnQ8x4k,   //openid
//			sign=1269E03E43F2B8C388A414EDAE185CEE, 
//			fee_type=CNY,    //金钱交易币种
//			mch_id=1228442802,    //商户id
//			cash_fee=1,   //现金
//			out_trade_no=1000000000051249,  //支付单号
//			appid=wxb4dc385f953b356e, 
//			total_fee=1,   //订单金额
//			trade_type=JSAPI,  //
//			result_code=SUCCESS, 
//			time_end=20150324100405, 
//			is_subscribe=Y, 
//			return_code=SUCCESS}  //交易状态
	
	@Autowired
	private PaymentService paymentService;
	
	@SuppressWarnings({ "unused", "unchecked" })
	@PostMapping(value = "/getYgwWXPayNotify" )
	public String getPayNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Logger.info("进入微信异步回调");
		BufferedReader reader = null ;
	
		reader = request.getReader() ;
		String line = "" ;
		String xmlString = null ;
		StringBuilder inputString = new StringBuilder() ;
	
		while( (line = reader.readLine()) != null ){
		inputString.append(line) ;
		}
	
		xmlString = inputString.toString() ;
		request.getReader().close(); 
	
	
		Map<String, String> map = null;
		 
	
		map = XMLUtil.doXMLParse(xmlString) ;
		String bankType = "";
		String cashFee = "";
		String feeType = "";
		String isSubscribe = "";
		String mchId = "";
		String nonceStr = "";
		String openid = "";
		String outTradeNo = "";
		String resultCode = "";
		String sign = "";
		String timeEnd = "";
		String totalFee = "";
		String tradeType = "";
		String transactionId = "";
		
		resultCode = map.get("result_code");
		outTradeNo = map.get("out_trade_no");
		totalFee = map.get("total_fee");
		bankType = map.get("return_code");
		transactionId = map.get("transaction_id");
		mchId = map.get("mch_id");
		timeEnd = map.get("time_end");  
		
	   
			if( checkSign(xmlString)){ 
			//订单操作
				if( "success".equalsIgnoreCase(resultCode) ){
				//业务处理
					EctoolsPaymentsSucc ectoolsPaymentsSucc = new EctoolsPaymentsSucc();
					ectoolsPaymentsSucc.setPaymentId(outTradeNo);
					ectoolsPaymentsSucc.setTradeNo(transactionId);
					ectoolsPaymentsSucc.setMoney(new BigDecimal(totalFee).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
					ectoolsPaymentsSucc.setBank(bankType);
					ectoolsPaymentsSucc.setStatus(SysParaNameConst.SUCC);
					ectoolsPaymentsSucc.setPayName("微信支付");
					ectoolsPaymentsSucc.setPayAccount("微信支付"); 
					ectoolsPaymentsSucc.setAccount(mchId);
					ectoolsPaymentsSucc.setPayedTime(Integer.valueOf(DateUtil.date2TimeStamp(timeEnd, "yyyyMMddHHmmss")));
					
					ResponseBodyData payCallBack = paymentService.PayCallBack(ectoolsPaymentsSucc,xmlString);
					Logger.info("微信支付回调处理的结果:%s", payCallBack.toString());
				}
				Logger.info(resultCode);
				return returnXML(resultCode) ;
			}else{
				Logger.info("FAIL");
				return returnXML("FAIL") ;
			}
	}
	
	
	
	@SuppressWarnings("unchecked")
	private boolean checkSign(String xmlString) {
		Map<String, String> map = null;

		try {
			map = XMLUtil.doXMLParse(xmlString);
		} catch (Exception e) {
			Logger.error("system error: %s",e);
			return false;
		}

		String signFromAPIResponse = map.get("sign");

		if (StringUtils.isEmpty(signFromAPIResponse)) {
			return false;
		}

		map.put("sign", "");

		String signForAPIResponse = getSign(map);

		if (!signForAPIResponse.equals(signFromAPIResponse)) {
			Logger.info("SIGN FAIL");
			return false;
		}

		return true;
	}

	private String getSign(Map<String, String> map) {
		SortedMap<String, String> signParams = new TreeMap<String, String>();

		for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
			signParams.put(stringStringEntry.getKey(), stringStringEntry.getValue());
		}

		signParams.remove("sign");
		String sign = createSign("UTF-8", signParams);

		return sign;
	}

	@SuppressWarnings("rawtypes")
	private static String createSign(String characterEncoding, SortedMap<String, String> parameters) {
		StringBuilder sb = new StringBuilder();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}

		sb.append("key=" + PayConfig.YGWAPP_KEY);// 最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();

		return sign;
	}

	
	private String returnXML(String returnCode) {
		return "<xml><return_code><![CDATA[" + returnCode
				+ "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	}


 
}