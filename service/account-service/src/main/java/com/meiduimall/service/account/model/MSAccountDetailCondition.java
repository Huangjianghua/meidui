package com.meiduimall.service.account.model;

import java.io.Serializable;

import com.meiduimall.service.account.util.PageHelp;

public class MSAccountDetailCondition extends PageHelp implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String memLoginName; //用户账户
	
	private String memPhone; //手机号
	
	
	private  String tradeType; //业务类型
	
	private String beginDate;  
	
	private String endDate;
	
	private String status; //提现状态
	
	private String flg;  //是否分页  1：是  0:否
	
	private String businNo; //提现编号

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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	

	

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinNo() {
		return businNo;
	}

	public void setBusinNo(String businNo) {
		this.businNo = businNo;
	}
	
	
	

}
