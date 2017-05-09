package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EctoolsPayments implements Serializable {
    private String paymentId; //支付单号

    private BigDecimal money;

    private BigDecimal curMoney;

    private BigDecimal cartXfc;

    private String status;

    private Integer userId;

    private String userName;

    private String payType;

    private String payAppId;

    private String payName;

    private Integer payedTime;

    private String opName;

    private Integer opId;

    private String account;

    private String bank;

    private String payAccount;

    private String currency;

    private BigDecimal paycost;

    private String payVer;

    private String ip;

    private Integer createdTime;

    private Integer modifiedTime;

    private String memo;

    private Boolean disabled;

    private String tradeNo;

    private String thirdpartyAccount;

    private BigDecimal walletPay;

    private BigDecimal coupPay;

    private BigDecimal shopingPay;

    private String platformId;

    private Integer errorNum;

    private BigDecimal errorMoney;

    private Integer pointPay;

    private String returnUrl;

    private String tids;

    private String tradeOwnMoney;

    private static final long serialVersionUID = 1L;

    
    
    
    
    public EctoolsPayments(String paymentId, String status) {
		super();
		this.paymentId = paymentId;
		this.status = status;
	}

	public EctoolsPayments() {
		super();
	}

	
	
	public EctoolsPayments(String paymentId, BigDecimal errorMoney) {
		super();
		this.paymentId = paymentId;
		this.errorMoney = errorMoney;
	}

	public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getCurMoney() {
        return curMoney;
    }

    public void setCurMoney(BigDecimal curMoney) {
        this.curMoney = curMoney;
    }

    public BigDecimal getCartXfc() {
        return cartXfc;
    }

    public void setCartXfc(BigDecimal cartXfc) {
        this.cartXfc = cartXfc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayAppId() {
        return payAppId;
    }

    public void setPayAppId(String payAppId) {
        this.payAppId = payAppId == null ? null : payAppId.trim();
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public Integer getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(Integer payedTime) {
        this.payedTime = payedTime;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getPaycost() {
        return paycost;
    }

    public void setPaycost(BigDecimal paycost) {
        this.paycost = paycost;
    }

    public String getPayVer() {
        return payVer;
    }

    public void setPayVer(String payVer) {
        this.payVer = payVer == null ? null : payVer.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getThirdpartyAccount() {
        return thirdpartyAccount;
    }

    public void setThirdpartyAccount(String thirdpartyAccount) {
        this.thirdpartyAccount = thirdpartyAccount == null ? null : thirdpartyAccount.trim();
    }

    public BigDecimal getWalletPay() {
        return walletPay;
    }

    public void setWalletPay(BigDecimal walletPay) {
        this.walletPay = walletPay;
    }

    public BigDecimal getCoupPay() {
        return coupPay;
    }

    public void setCoupPay(BigDecimal coupPay) {
        this.coupPay = coupPay;
    }

    public BigDecimal getShopingPay() {
        return shopingPay;
    }

    public void setShopingPay(BigDecimal shopingPay) {
        this.shopingPay = shopingPay;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    

    public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public BigDecimal getErrorMoney() {
        return errorMoney;
    }

    public void setErrorMoney(BigDecimal errorMoney) {
        this.errorMoney = errorMoney;
    }

    public Integer getPointPay() {
        return pointPay;
    }

    public void setPointPay(Integer pointPay) {
        this.pointPay = pointPay;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public String getTids() {
        return tids;
    }

    public void setTids(String tids) {
        this.tids = tids == null ? null : tids.trim();
    }

    public String getTradeOwnMoney() {
        return tradeOwnMoney;
    }

    public void setTradeOwnMoney(String tradeOwnMoney) {
        this.tradeOwnMoney = tradeOwnMoney == null ? null : tradeOwnMoney.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paymentId=").append(paymentId);
        sb.append(", money=").append(money);
        sb.append(", curMoney=").append(curMoney);
        sb.append(", cartXfc=").append(cartXfc);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", payType=").append(payType);
        sb.append(", payAppId=").append(payAppId);
        sb.append(", payName=").append(payName);
        sb.append(", payedTime=").append(payedTime);
        sb.append(", opName=").append(opName);
        sb.append(", opId=").append(opId);
        sb.append(", account=").append(account);
        sb.append(", bank=").append(bank);
        sb.append(", payAccount=").append(payAccount);
        sb.append(", currency=").append(currency);
        sb.append(", paycost=").append(paycost);
        sb.append(", payVer=").append(payVer);
        sb.append(", ip=").append(ip);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", memo=").append(memo);
        sb.append(", disabled=").append(disabled);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", thirdpartyAccount=").append(thirdpartyAccount);
        sb.append(", walletPay=").append(walletPay);
        sb.append(", coupPay=").append(coupPay);
        sb.append(", shopingPay=").append(shopingPay);
        sb.append(", platformId=").append(platformId);
        sb.append(", errorNum=").append(errorNum);
        sb.append(", errorMoney=").append(errorMoney);
        sb.append(", pointPay=").append(pointPay);
        sb.append(", returnUrl=").append(returnUrl);
        sb.append(", tids=").append(tids);
        sb.append(", tradeOwnMoney=").append(tradeOwnMoney);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}