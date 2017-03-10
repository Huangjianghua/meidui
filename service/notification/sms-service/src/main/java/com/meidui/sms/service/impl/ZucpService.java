package com.meidui.sms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meiduimall.HttpClientUtil;
import com.meiduimall.exception.ApiException;
import com.meiduimall.password.util.MD5;

/**
 * 漫道短信服務
 * @author 
 * @since 2011.01.05
 */
@Service
public class ZucpService {
	
	private static Logger Logger = LoggerFactory.getLogger(ZucpService.class);

	String zucpUrl = "http://sdk.entinfo.cn:8060/webservice.asmx";
	String zucpUser = "SDK-WSS-010-09798";
	String zucpPasswd = "a-9[60-[";

	/**
	 * 生成请求体
	 * 
	 * @param mobile
	 * @param content
	 * @param ext
	 * @param stime
	 * @param rrid
	 * @return
	 * @throws Exception
	 */
	String buildBody(String mobile, String content, String ext, String stime, String rrid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		sb.append("<soap:Body>");
		sb.append("<mdSmsSend_u xmlns=\"http://tempuri.org/\">");
		sb.append("<sn>");
		sb.append(zucpUser);
		sb.append("</sn>");
		sb.append("<pwd>");
		sb.append(MD5.md5Digest(zucpUser + zucpPasswd));
		sb.append("</pwd>");
		sb.append("<mobile>");
		sb.append(mobile);
		sb.append("</mobile>");
		sb.append("<content>");
		sb.append(content);
		sb.append("</content>");
		sb.append("<ext>");
		sb.append(ext);
		sb.append("</ext>");
		sb.append("<stime>");
		sb.append(stime);
		sb.append("</stime>");
		sb.append("<rrid>");
		sb.append(rrid);
		sb.append("</rrid>");
		sb.append("</mdSmsSend_u>");
		sb.append("</soap:Body>");
		sb.append("</soap:Envelope>");
		return sb.toString();
	}

	/**
	 * 發送短信
	 * 
	 * @param mobile
	 * @param content
	 * @param ext
	 * @param stime
	 * @param rrid
	 * @throws PushException
	 */
	@Deprecated
	public String Send(String mobile, String content, String ext, String stime, String rrid) throws ApiException {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		headers.put("SOAPAction", "http://tempuri.org/mdSmsSend_u");

		try {
			StringEntity param = new StringEntity(buildBody(mobile, content, ext, stime, rrid),"UTF-8");
			param.setContentEncoding("UTF-8");

			String result = HttpClientUtil.doPost(zucpUrl, param, headers);
			Logger.info("Zucp::Result -> %s", result);

			String[] array1 = result.split("<mdSmsSend_uResult>");
			String[] array2 = array1[1].split("</mdSmsSend_uResult>");

			return array2[0];
		} catch (Exception e) {
			Logger.error("ZucpService error.", e);
			throw new ApiException(e);
		}
		
	}

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param content
	 * @return
	 * @throws PushException
	 */
	public String Send(String mobile, String content) throws ApiException {
		return this.Send(mobile, content, "", "", "");
	}
}
