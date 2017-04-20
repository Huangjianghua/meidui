/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.password.exception.Md5Exception;
import com.meiduimall.password.util.MD5;
import com.meiduimall.service.sms.SmsApiCode;


/**
 * 漫道短信服務
 * @author pc
 * @since 2017.01.05
 */
@Service
public class ZucpService {

	private static Logger logger = LoggerFactory.getLogger(ZucpService.class);
	
	@Value("${zucpUrl}")
	private String zucpUrl; 
	@Value("${zucpUser}")
	private String zucpUser; 
	@Value("${zucpPasswd}")
	private String zucpPasswd; 

	/**
	 * 生成请求体
	 * 
	 * @param mobile
	 * @param content
	 * @param ext
	 * @param stime
	 * @param rrid
	 * @return
	 * @throws Md5Exception 
	 * @throws Exception
	 */
	String buildBody(String mobile, String content, String ext, String stime, String rrid) throws Md5Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		sb.append("<soap:Body>");
		sb.append("<mdSmsSend_u xmlns=\"http://tempuri.org/\">");
		sb.append("<sn>");
		sb.append(zucpUser);
		sb.append("</sn>");
		sb.append("<pwd>");
		sb.append(MD5.getMD5EncodeUTF8(zucpUser + zucpPasswd));
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
	 * @throws ApiException
	 */
	public String send(String mobile, String content, String ext, String stime, String rrid)  {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		headers.put("SOAPAction", "http://tempuri.org/mdSmsSend_u");

		try {

			String result = HttpUtils.post(zucpUrl, buildBody(mobile, content, ext, stime, rrid), headers,"UTF-8","UTF-8");
			logger.info("Zucp::Result -> %s", result);

			String[] array1 = result.split("<mdSmsSend_uResult>");
			String[] array2 = array1[1].split("</mdSmsSend_uResult>");

			return array2[0];
		} catch (Exception e) {
			logger.error("ZucpService error.", e);
			throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER,SmsApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
		}
		
	}

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param content
	 * @return
	 * @throws ApiException
	 */
	public String send(String mobile, String content)  {
		return this.send(mobile, content, "", "", "");
	}
}
