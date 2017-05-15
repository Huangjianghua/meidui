package com.meiduimall.service.member.model.response;

public class MemberOpenIdDTO {

	private String memId;
	private String memPhone;
	private String wxOpenId;
	private String memLoginName;
	private String memNickName;
	private String memPoint;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	public String getMemLoginName() {
		return memLoginName;
	}
	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}
	public String getMemNickName() {
		return memNickName;
	}
	public void setMemNickName(String memNickName) {
		this.memNickName = memNickName;
	}
	public String getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(String memPoint) {
		this.memPoint = memPoint;
	}
}
