package com.meiduimall.service.sms.service.impl;

import java.io.ByteArrayOutputStream;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.meiduimall.core.exception.ApiException;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.HttpClientUtil;
import com.meiduimall.password.util.MD5;
import com.meiduimall.service.sms.entity.SmsSoap;
import com.meiduimall.service.sms.service.ZucpService;


@Service
public class ZucpServiceImpl implements ZucpService{
	
	private static Logger Logger = LoggerFactory.getLogger(ZucpServiceImpl.class);

	String zucpUrl = "http://sdk.entinfo.cn:8060/webservice.asmx";
	String zucpUser = "SDK-WSS-010-09798";
	String zucpPasswd = "a-9[60-[";

	/**
	 * 生成请求体
	 * @param mobile
	 * @param content
	 * @param ext
	 * @param stime
	 * @param rrid
	 * @return
	 * @throws Exception
	 */
	String buildBody(String mobile, String content, String ext, String stime, String rrid) throws Exception {
		Document requestDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Marshaller marshaller = JAXBContext.newInstance(SmsSoap.class).createMarshaller();
		SmsSoap md=new SmsSoap();
		md.setSn(zucpUser);
		md.setPwd(MD5.md5Digest(zucpUser + zucpPasswd));
		md.setMobile(mobile);
		md.setContent(content);
		md.setExt(ext);
		md.setStime(stime);
		md.setRrid(rrid);
		marshaller.marshal(md,requestDocument);
		SOAPMessage requestSOAPMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage();
		SOAPBody soapBody = requestSOAPMessage.getSOAPBody();
		soapBody.addDocument(requestDocument);
		SOAPEnvelope soapEnvelope = requestSOAPMessage.getSOAPPart().getEnvelope();
		soapEnvelope.removeNamespaceDeclaration("SOAP-ENV");
		soapEnvelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope");
		soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		soapEnvelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
		soapEnvelope.setPrefix("soap");
		soapEnvelope.removeChild(soapEnvelope.getHeader());
		soapBody.setPrefix("soap");
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		requestSOAPMessage.writeTo(os);
		return os.toString();
	}
     
	
	public String Send(String mobile, String content, String ext, String stime, String rrid) throws ApiException {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		headers.put("SOAPAction", "http://tempuri.org/mdSmsSend_u");
		try {
			StringEntity param = new StringEntity(buildBody(mobile, content, ext, stime, rrid),"UTF-8");
			param.setContentEncoding("UTF-8");
			String result = HttpClientUtil.doPost(zucpUrl, param, headers);
			String[] array1 = result.split("<mdSmsSend_uResult>");
			String[] array2 = array1[1].split("</mdSmsSend_uResult>");

			return array2[0];
		} catch (Exception e) {
			Logger.error("漫道短信服務发送短信接口异常:{}", ExceptionUtils.getFullStackTrace(e));
			throw new ApiException(e);
		}
		
	}

	public String Send(String mobile, String content) throws ApiException {
		return this.Send(mobile, content, "", "", "");
	}
}
