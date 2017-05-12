package com.meiduimall.service.member.model.response;
import java.io.Serializable;
import java.util.Date;
public class ExportMemberDTO implements Serializable{
	private static final long serialVersionUID = -2544011644209319175L;
	
	private String memId;
	private String memLoginName;
	private String memPhone;
	private String currentPoint;
	private String currentMoney;
	private Date memCreatedDate;
	private Date memLoginTime;
	
	private String area;
	
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemLoginName() {
		return memLoginName;
	}
	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getCurrentPoint() {
		return currentPoint;
	}
	public void setCurrentPoint(String currentPoint) {
		this.currentPoint = currentPoint;
	}
	public String getCurrentMoney() {
		return currentMoney;
	}
	public void setCurrentMoney(String currentMoney) {
		this.currentMoney = currentMoney;
	}
	public Date getMemCreatedDate() {
		return memCreatedDate;
	}
	public void setMemCreatedDate(Date memCreatedDate) {
		this.memCreatedDate = memCreatedDate;
	}
	public Date getMemLoginTime() {
		return memLoginTime;
	}
	public void setMemLoginTime(Date memLoginTime) {
		this.memLoginTime = memLoginTime;
	}
	@Override
	public String toString() {
		return "ExportMemberDTO [memId=" + memId + ", memLoginName=" + memLoginName + ", memPhone=" + memPhone
				+ ", currentPoint=" + currentPoint + ", currentMoney=" + currentMoney + ", memCreatedDate="
				+ memCreatedDate + ", memLoginTime=" + memLoginTime + ", area=" + area + "]";
	}
	 
	
	
	
	
}