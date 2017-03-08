package com.meidui.shortmsg.oauth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.meidui.shortmsg.util.Logger;
import com.meidui.shortmsg.util.MD5Util;
import com.meidui.shortmsg.util.StringUtil;

/**
 * Title : OauthValidate 
 * Description : 鉴权
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:47:55 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public class OauthValidate {
	
	/**
	 * Description : 验证入口
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午4:58:22 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validate(HttpServletRequest request) throws Exception{
		Logger.info("request URL:" + request.getRequestURL() + ";Method:" + request.getMethod());
		Logger.info("request IP:" + request.getRemoteHost());
		JSONObject json = new JSONObject();
		boolean tokenFlag = true;
		List<Parameter> parameters = new ArrayList<Parameter>();
		Parameter token = null;
//		Enumeration<String> enumer = request.getParameterNames();
//		while(enumer.hasMoreElements()) {
//			String paramName = enumer.nextElement();
//			parameters.add(new Parameter(paramName, request.getParameter(paramName)));
//			if (OauthConst.TOKEN.equals(paramName))
//				token = new Parameter(paramName, request.getParameter(paramName));
//		}
		if (null != token) {
			JSONObject valToken = OauthToken.validateToken(token.getValue());
			if ((int)valToken.get("errcode") != 0) {
				tokenFlag = false;
				return valToken;
			}
		}
		if (tokenFlag) {
			JSONObject valParam = OauthValidate.validateParamters(parameters);
			if ((int)valParam.get("errcode") == 0) {
				if (StringUtil.checkObj(valParam.get(OauthConst.TIMESATAMP))) {
					JSONObject valTimesatamp = OauthValidate.validateTimesatamp(Long.valueOf(valParam.get(OauthConst.TIMESATAMP).toString()));
					if ((int)valTimesatamp.get("errcode") == 0) {
						if (StringUtil.checkObj(valParam.get(OauthConst.SIGN))) {
							JSONObject valSign = OauthValidate.validateSign(parameters, valParam.get(OauthConst.SIGN).toString());
							if ((int)valSign.get("errcode") == 0) {
								json.put("errcode", 0);
							} else {
								return valSign;
							}
						} else {
							json.put("errcode", -1);
							json.put("errmsg", "invalid parameters!");
							Logger.info("Sign parameter value is null!");
						}
					} else {
						return valTimesatamp;
					}
				} else {
					json.put("errcode", -1);
					json.put("errmsg", "invalid parameters!");
					Logger.info("timesatamp parameter value is null!");
				}
			} else {
				return valParam;
			}
		}
		return json;
	}
	
	/**
	 * Description : 验证参数
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午3:50:17 
	 *  
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validateParamters(Collection<? extends Map.Entry<String, String>> parameters) throws Exception{
		JSONObject json = new JSONObject();
		if (!StringUtil.isEmptyByCollection(parameters)) {
			for (Map.Entry<String, String> p : parameters) {
				if (StringUtil.checkStr(p.getKey()) && StringUtil.checkStr(p.getValue())) {
					if (OauthConst.TIMESATAMP.equals(p.getKey()))
						json.put(p.getKey(), p.getValue());
					if (OauthConst.SIGN.equals(p.getKey()))
						json.put(p.getKey(), p.getValue());
				} else {
					json.put("errcode", -1);
					json.put("errmsg", "invalid parameters!");
					Logger.info("parameter key or value is null!");
					return json;
				}
			}
			json.put("errcode", 0);
			Logger.info("vailidateparamters success!");
		} else {
			json.put("errcode", -1);
			json.put("errmsg", "invalid parameters!");
			Logger.info("parameter conllection is null!");
		}
		return json;
	}
	
	/**
	 * Description : 验证时间戳, 请求到当前时间不超过5分钟
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午5:00:28 
	 * 
	 * @param timesatamp
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validateTimesatamp(Long timesatamp) throws Exception{
		JSONObject json = new JSONObject();
		Long currentTime = new Date().getTime();
		Long beforeTime = currentTime - OauthConst.REQUEST_EFFECTIVE_TIME;
		if (timesatamp >= beforeTime) {
			json.put("errcode", 0);
			Logger.info("validateTimesatamp success!");
		} else {
			json.put("errcode", -1);
			json.put("errmsg", "invalid parameters!");
			Logger.info("request url time out!");
		}
		return json;
	}
	
	/**
	 * Description : 验证签名
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午5:01:21  
	 * 
	 * @param parameters
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validateSign(Collection<? extends Map.Entry<String, String>> parameters, String sign) throws Exception{
		JSONObject json = new JSONObject();
		String[] arr = new String[parameters.size() - 1];
		int i = 0;
		for (Map.Entry<String, String> p : parameters) {
			if (OauthConst.SIGN.equals(p.getKey()))
				continue;
			else
				arr[i] = p.getKey() + "=" + p.getValue();
			i++;
		}
		Arrays.sort(arr);
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < arr.length; k++) {
			buffer.append(arr[k]);
			buffer.append("&");
		}
		buffer.append(OauthConst.SECRETKEY_NAME);
		buffer.append(OauthConst.SECRETKEY_VALUE);
		if (sign.equals(MD5Util.MD5EncryptBy32(buffer.toString()).toUpperCase())) {
			json.put("errcode", 0);
			Logger.info("validateSign success!");
		} else {
			json.put("errcode", -1);
			json.put("errmsg", "invalid parameters!");
			Logger.info("sign invalid!");
		}
		return json;
	}
	
/*	public static void main(String[] args) throws Exception {
		String timestamp = "timesatamp=" + new Date().getTime();
		System.out.println("当前时间:" + timestamp);
		String username = "username=xiaok";
		String password = "password=0928";
		String clientId = "clientID=WEB";
		String token = "token=123543234";
		String[] arr = new String[]{username, password, clientId, timestamp.toString(), token};
		Arrays.sort(arr);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			buffer.append(arr[i]);
			buffer.append("&");
		}
		buffer.append("secretKey=" + OauthConst.SECRETKEY_VALUE);
		String sign = "sign=" + MD5Util.MD5EncryptBy32(buffer.toString()).toUpperCase();
		String requestURI = "?" + username + "&" + password + "&" + clientId + "&" + timestamp + "&" + sign + "&" + token;
		System.out.println(requestURI);
	}*/
}
