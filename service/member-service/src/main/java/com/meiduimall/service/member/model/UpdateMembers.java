package com.meiduimall.service.member.model;

import java.io.Serializable;
import java.util.Date;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.util.DESC;

/**
 * 
 * 会员基础表(加密)
 * 
 **/
public class UpdateMembers implements Serializable {

	private static final long serialVersionUID = 3350810538461054635L;

	/** 会员系统ID **/
	private String memId;
	
	/**用户姓名*/
	private String memName;

	/** 会员登录名 **/
	private String memLoginName;
	
	/** 会员旧邮箱 **/
	private String memOldEmail;
	
	/** 会员邮箱 **/
	private String memEmail;

	/** 会员上一次绑定手机号 **/
	private String memOldPhone;

	/** 会员最新手机号 **/
	private String memPhone;

	/** 会员昵称 **/
	private String memNickName;
	
	/** 会员性别 **/
	private String memSex;
	
	/** 会员生日 **/
	private java.util.Date memBirthday;
	
	/** 会员自定义头像,主要给APP或PC应用 **/
	private String memPic;

	/** 会员更新时间 **/
	private java.util.Date memUpdatedDate;
	
	/** 修改手机号时间 **/
	private Date changePhoneDate;

	public Date changePhoneDate() {
		return changePhoneDate;
	}
	
	public java.util.Date getChangePhoneDate() {
		return changePhoneDate;
	}

	public void setChangePhoneDate(java.util.Date changePhoneDate) {
		this.changePhoneDate = changePhoneDate;
	}

	public String getMemName() throws MdSysException {
		return DESC.deyption(this.memName);
	}

	public void setMemName(String memName) throws MdSysException {
		this.memName = DESC.encryption(memName);
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMemLoginName(String memLoginName)  throws MdSysException {
		this.memLoginName = DESC.encryption(memLoginName);
	}

	public String getMemLoginName() {
		return this.memLoginName;
	}

	public void setMemOldPhone(String memOldPhone) throws MdSysException {
		this.memOldPhone = DESC.encryption(memOldPhone);
	}

	public String getMemOldPhone() {
		return this.memOldPhone;
	}

	public void setMemPhone(String memPhone) throws MdSysException {
		this.memPhone = DESC.encryption(memPhone);
	}

	public String getMemPhone() {
		return this.memPhone;
	}

	public void setMemNickName(String memNickName) throws MdSysException {
		this.memNickName = DESC.encryption(memNickName);
	}

	public String getMemNickName() {
		return this.memNickName;
	}

	public void setMemUpdatedDate(java.util.Date memUpdatedDate) {
		this.memUpdatedDate = memUpdatedDate;
	}

	public java.util.Date getMemUpdatedDate() {
		return this.memUpdatedDate;
	}

	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}

	public String getMemSex() {
		return this.memSex;
	}

	public void setMemBirthday(java.util.Date memBirthday) {
		this.memBirthday = memBirthday;
	}

	public java.util.Date getMemBirthday() {
		return this.memBirthday;
	}

	public void setMemPic(String memPic) throws MdSysException {
		this.memPic = DESC.encryption(memPic,this.memId);
	}

	public String getMemPic() {
		return this.memPic;
	}

	public String getMemOldEmail() {
		return memOldEmail;
	}

	public void setMemOldEmail(String memOldEmail) throws MdSysException {
		this.memOldEmail = DESC.encryption(memOldEmail);
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) throws MdSysException {
		this.memEmail = DESC.encryption(memEmail);
	}
}