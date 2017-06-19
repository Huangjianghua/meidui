package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 设置支付密码请求映射实体
 * @author chencong
 *
 */
public class RequestUpdateMemberBasicInfo implements Serializable {

	private static final long serialVersionUID = 5605581454189394144L;

	/** 会员系统ID **/
	@NotEmpty(message="memId不能为空")
	@Length(min=36,max=36,message="memId参数长度不正确")
	private String memId;

	private String nickName;
	
	private String phone;
	
	private String sex;
	
	private String email;
	
	private String birthday;
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
