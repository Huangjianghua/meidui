package com.meiduimall.application.md1gwaccess.api;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
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

import com.meiduimall.application.md1gwaccess.constant.PayConfig;
import com.meiduimall.application.md1gwaccess.constant.ResponseBodyData;
import com.meiduimall.application.md1gwaccess.constant.SysParaNameConst;
import com.meiduimall.application.md1gwaccess.model.EctoolsPaymentsSucc;
import com.meiduimall.application.md1gwaccess.service.PaymentService;
import com.meiduimall.application.md1gwaccess.util.DateUtil;
import com.meiduimall.application.md1gwaccess.util.Logger;
import com.meiduimall.application.md1gwaccess.util.MD5Util;
import com.meiduimall.application.md1gwaccess.util.XMLUtil;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1")
public class WeiXinController {
 
	
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
	@PostMapping(value = "/getWXPayNotify" )
	public String getPayNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Logger.info("进入微信异步回调");
		BufferedReader reader = null ;
	
		reader = request.getReader() ;
		String line = "" ;
		String xmlString = null ;
		StringBuffer inputString = new StringBuffer() ;
	
		while( (line = reader.readLine()) != null ){
		inputString.append(line) ;
		}
	
		xmlString = inputString.toString() ;
		request.getReader().close(); 
	
	
		Map<String, String> map = new HashMap<String, String>() ;
		 
	
		map = XMLUtil.doXMLParse(xmlString) ;
		String bank_type = "";
		String cash_fee = "";
		String fee_type = "";
		String is_subscribe = "";
		String mch_id = "";
		String nonce_str = "";
		String openid = "";
		String out_trade_no = "";
		String result_code = "";
		String sign = "";
		String time_end = "";
		String total_fee = "";
		String trade_type = "";
		String transaction_id = "";
		
		bank_type = map.get("bank_type");
		cash_fee = map.get("cash_fee");
		fee_type = map.get("fee_type");
		is_subscribe = map.get("is_subscribe");
		result_code = map.get("result_code");
		out_trade_no = map.get("out_trade_no");
		total_fee = map.get("total_fee");
		sign = map.get("sign");
		bank_type = map.get("return_code");
		transaction_id = map.get("transaction_id");
		mch_id = map.get("mch_id");
		time_end = map.get("time_end");  
		
	   
			if( checkSign(xmlString)){ 
			//订单操作
				if( result_code.toLowerCase().equals("success") ){
				//业务处理
					EctoolsPaymentsSucc ectoolsPaymentsSucc = new EctoolsPaymentsSucc();
					ectoolsPaymentsSucc.setPaymentId(out_trade_no);
					ectoolsPaymentsSucc.setTradeNo(transaction_id);
					ectoolsPaymentsSucc.setMoney(new BigDecimal(total_fee).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
					ectoolsPaymentsSucc.setBank(bank_type);
					ectoolsPaymentsSucc.setStatus(SysParaNameConst.SUCC);
					ectoolsPaymentsSucc.setPayName("微信支付");
					ectoolsPaymentsSucc.setPayAccount("微信支付"); 
					ectoolsPaymentsSucc.setAccount(mch_id);
					ectoolsPaymentsSucc.setPayedTime(Integer.valueOf(DateUtil.date2TimeStamp(time_end, "yyyyMMddHHmmss")));
					
					ResponseBodyData payCallBack = paymentService.PayCallBack(ectoolsPaymentsSucc,xmlString);
					Logger.info("微信支付回调处理的结果:%s", payCallBack.toString());
				}
				Logger.info(result_code);
				return returnXML(result_code) ;
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
			e.printStackTrace();
			return false;
		}

		String signFromAPIResponse = map.get("sign").toString();

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
		StringBuffer sb = new StringBuffer();
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

		sb.append("key=" + PayConfig.MDAPP_Key);// 最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();

		return sign;
	}

	
	private String returnXML(String return_code) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	}


 
}