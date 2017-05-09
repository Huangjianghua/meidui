package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 校验短信验证码请求映射实体
 * @author chencong
 *
 */
public class RequestCheckValidateCode  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String memId;
	
	@NotEmpty(message="手机号不能为空")
	@Length(min=11,max=11,message="手机号长度不正确")
	private String phone;
	
	@NotNull(message="短信验证码类型不能为空")
	private Integer type;
	
	
	@NotEmpty(message="验证码不能为空")
	@Length(min=6,max=6,message="验证码长度不正确")
	private String validate_code;

	public String getValidate_code() {
		return validate_code;
	}

	public void setValidate_code(String validate_code) {
		this.validate_code = validate_code;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RequestCheckValidateCode [memId=" + memId + ", phone=" + phone + ", type=" + type + ", validate_code="
				+ validate_code + "]";
	}
	
}
