package com.meiduimall.service.account.model;

import java.io.Serializable;

import com.meiduimall.service.account.util.PageHelp;

public class MSRechargeApply extends PageHelp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9169330321048734969L;
	
	/**充值申请表主键 */
	private String id;
	
	/**美兑用户名 */
	private String mdUser;
	
	/**企业标识 */
	private String clientId;
	
	/**外部系统充值订单号 */
	private String bizId;
	
	/**充值金额 */
	private String rechargeAmout;
	
	/**时间戳 */
	private String reqTime;
	
	/**回调地址 */
	private String callbackUrl;
	
	/**充值类型 */
	private String rechargeType;
	
	/**是否分页  1：是  0:否	*/
	private String flg;  

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMdUser() {
		return mdUser;
	}

	public void setMdUser(String mdUser) {
		this.mdUser = mdUser;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getRechargeAmout() {
		return rechargeAmout;
	}

	public void setRechargeAmout(String rechargeAmout) {
		this.rechargeAmout = rechargeAmout;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}	
	
	
}
