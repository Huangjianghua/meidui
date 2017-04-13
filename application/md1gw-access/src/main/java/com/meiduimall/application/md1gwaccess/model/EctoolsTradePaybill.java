package com.meiduimall.application.md1gwaccess.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EctoolsTradePaybill implements Serializable {
    private Integer paybillId;

    private String paymentId;

    private String tid;

    private String status;

    private BigDecimal payment;

    private String userId;

    private Integer payedTime;

    private Integer createdTime;

    private Integer modifiedTime;

    private String platformId;

    private static final long serialVersionUID = 1L;

    
    
    public EctoolsTradePaybill(String status,String paymentId) {
		super();
		this.paymentId = paymentId;
		this.status = status;
	}

	public EctoolsTradePaybill() {
		super();
	}

	public Integer getPaybillId() {
        return paybillId;
    }

    public void setPaybillId(Integer paybillId) {
        this.paybillId = paybillId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment == null ? null : payment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(Integer payedTime) {
        this.payedTime = payedTime;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paybillId=").append(paybillId);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", tid=").append(tid);
        sb.append(", status=").append(status);
        sb.append(", payment=").append(payment);
        sb.append(", userId=").append(userId);
        sb.append(", payedTime=").append(payedTime);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", platformId=").append(platformId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}