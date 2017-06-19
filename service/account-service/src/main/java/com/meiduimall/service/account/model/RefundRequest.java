package com.meiduimall.service.account.model;

import java.io.Serializable;

public class RefundRequest implements Serializable{

	private static final long serialVersionUID = -5374791235919888760L;
	/**退款表主键ID**/
	private String refId; 
	/**订单号**/
	private String orderId; 
	/**外部订单号**/
	private String extorderId; 
	/**公司**/
	private String extcompanyName; 
	/**会员账号**/
	private String accountId; 
	/**退款金额**/
	private String refundAmout; 
	/**帐户类型**/
	private String accountType; 
	/**申请时间**/
	private String requestDate; 
	/**状态**/
	private String status; 
	/**是否分页  1：是  0:否	*/
	private String flg;
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getExtorderId() {
		return extorderId;
	}
	public void setExtorderId(String extorderId) {
		this.extorderId = extorderId;
	}
	public String getExtcompanyName() {
		return extcompanyName;
	}
	public void setExtcompanyName(String extcompanyName) {
		this.extcompanyName = extcompanyName;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getRefundAmout() {
		return refundAmout;
	}
	public void setRefundAmout(String refundAmout) {
		this.refundAmout = refundAmout;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	} 
	
}
