/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 发送普通短信参数模板
 * 
 * @author pc
 *
 */
public class CheckCodeRequest implements Serializable {

	private static final long serialVersionUID = 7996920400702100275L;

	/* 公共参数 */
	@NotNull
	private String phones;

	@NotNull
	private String templateId; // 模板id

	@NotNull
	private String verificationCode; // 验证码，用户输入验证码校验时使用

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

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
