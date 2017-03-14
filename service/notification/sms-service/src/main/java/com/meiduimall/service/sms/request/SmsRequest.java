package com.meiduimall.service.sms.request;




import org.hibernate.validator.constraints.NotBlank;

import com.meiduimall.Request;

public class SmsRequest extends Request{
	
	
	/*公共参数*/
	@NotBlank(message="电话号码不能为空")
	private String phones;
	
	@NotBlank(message="模板id不能为空")
	private String templateId; //模板id
	
	@NotBlank(message="渠道编号不能为空")
	private String supplierId;//渠道编号
	
	@NotBlank(message="替换短信中的参数不能为空")
	private String params;//替换短信中的参数
	
	//发动验证码短信，验证码过期时间
	/*验证码过期时间，即timeout缓存保存时长：格式:3h, 2mn, 7s or combination 2d4h10s, 1w2d3h10s */
	private String timeout; 
	
	/*校验短信*/
	private String verificationCode; //验证码，用户输入验证码校验时使用
	
	private String clientID;
	
	
	
	
	
	

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	
	

}
