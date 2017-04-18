package com.meiduimall.payment.api.payclient;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meiduimall.exception.ApiException;
import com.meiduimall.password.util.RsaEncrypt;
import com.meiduimall.payment.api.model.alipay.AlipayRequestModel;

/**
 * 支付寶客戶端
 * 
 * @author Nico.Jiang
 * @since 2016.12.22
 */
@Component
public class AlipayClient {
	
	private static Logger log = LoggerFactory.getLogger(AlipayClient.class);
	
	@Value("${pay.alipay.appid}")
	private String APP_ID;
	@Value("${pay.alipay.key.private}")
	private String APP_PRIVATE_KEY;
	@Value("${pay.alipay.key.public}")
	private String ALIPAY_PUBLIC_KEY;
	@Value("${pay.alipay.gateway}")
	private String ALIPAY_URL;

	@Value("${pay.alipay.partner}")
	private String partner;

	private String CHARSET = "UTF-8";

	/**
	 * 組裝簽名需要的參數
	 * 
	 * @param alipayAppModel
	 * @throws ApiException
	 */
	protected void buildBody(AlipayRequestModel model, String service) throws ApiException {
		model.setPartner(partner);
		model.setSeller_id(APP_ID);
		model.setService(service);
		model.setPayment_type("1");
		model.set_input_charset(CHARSET);
	}

	/**
	 * 构建请求参数
	 * 
	 * @param param
	 * @return
	 */
	protected String buildParam(Map<String, String> param) {
		Set<String> keys = param.keySet();
		StringBuffer result = new StringBuffer();

		for (String key : keys) {
			result.append(key + "=" + param.get(key) + "&");
		}

		return result.toString();
	}

	/**
	 * 签名
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	protected String buildSign(Object obj, Map<String, String> output) throws Exception {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		Map<String, String> map = new LinkedHashMap<String, String>();

		for (Field f : fields) {
			f.setAccessible(true);
			if (f.get(obj) != null && f.get(obj) != "") {
				map.put(f.getName(), f.get(obj).toString());
				list.add(f.getName() + "=" + f.get(obj) + "&");
			}
		}

		if (output != null) {
			output.putAll(map);
			log.info("Sign output: \n%s", output);
		}

		StringBuffer sb = new StringBuffer();

		for (String str : list) {
			sb.append(str);
		}

		String result = sb.toString().substring(0, sb.length() - 1);
		log.info("Sign result: \n%s", result);
		String sign = RsaEncrypt.encrypt(result, APP_PRIVATE_KEY, CHARSET);
		return sign;
	}

	/**
	 * APP 支付
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String alipayTradeApp(AlipayRequestModel model) throws Exception {
		buildBody(model, "mobile.securitypay.pay");

		Map<String, String> param = new LinkedHashMap<String, String>();
		String sign = buildSign(model, param);
		StringBuffer result = new StringBuffer(buildParam(param));
		result.append("sign=" + URLEncoder.encode(sign, CHARSET));
		result.append("&sign_type=RSA");
		log.info("AlipayTradeWap: \n%s", result.toString());

		return result.toString();
	}

	/**
	 * 支付宝H5支付
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String alipayTradeWap(AlipayRequestModel model) throws Exception {
		buildBody(model, "alipay.wap.create.direct.pay.by.user");

		Map<String, String> param = new HashMap<String, String>();
		String sign = buildSign(model, param);
		param.put("sign", sign);
		param.put("sign_type", "RSA");

		Set<String> keys = param.keySet();
		StringBuffer result = new StringBuffer();

		result.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_URL + "?_input_charset="
				+ CHARSET + "\" method=\"POST\">");

		for (String key : keys) {
			result.append("<input type=\"hidden\" name=\"" + key + "\" value=\"" + param.get(key) + "\"/>");
		}

		result.append("<input type=\"submit\" value=\"提交\" style=\"display:none;\"></form>");
		result.append("<script>document.forms['alipaysubmit'].submit();</script>");
		log.info("AlipayTradeWap: \n%s", result.toString());

		return result.toString();
	}

}
