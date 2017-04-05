package com.first.domain;

import java.io.Serializable;

import com.first.page.PageView;
import com.first.utility.DESC;

/**
 * 流水帐实体
 * @author Liujun
 * @date 2016年4月12日
 */
public class RunningAccount extends PageView implements Serializable {

	/**
	 * @author Administrator
	 */
	private static final long serialVersionUID = -9026333306066399014L;
	
	/** 会员消费记录表ID **/
	private String mchId;

	/** 会员ID **/
	private String memId;

	/** 订单号 **/
	private String orderId;
	
	/**套餐id**/
	private String pId;

	/** 消费金额 **/
	private String mchMoney;
	
	/** 成本价 **/
	private String mchCosts;

	/** 商家赠送积分 **/
	private String mchBshopGiveIntegral;

	/** 消费金额 **/
	private String mchTotalIntegral;

	/** 累计等级比率 **/
	private String bcLevel;

	/** 当次积分返本 **/
	private String mchCurrentReturnedIntegral;

	/** 待返本积分 **/
	private String mchNextReturnIntegral;

	/** 返利积分 **/
	private String mchReturnProfitIntegral;

	/** 消费基金 **/
	private String mchFunds;

	/** 本次所得积分 **/
	private String mchCurrentGetIntegral;

	/** 消费时间 **/
	private java.util.Date mchCreatedDate;

	/** 更新时间 **/
	private java.util.Date mchUpdatedDate;

	/** 更新人 **/
	private String mchUpdatedBy;

	/** 订单来源 **/
	private String mchOrginCategory;

	/** 消费商品名称 **/
	private String mchProductName;

	/** 消费来源 **/
	private String mchOrginType;

	/** 消费来源会员ID **/
	private String mchOrginMemId;

	/** 备注 **/
	private String mchRemark;
	
	/** 开始时间 **/
	private String startTime;
	
	/** 结束时间 **/
	private String endTime;

	/** 结算状态 0位正常 1未不正常 **/
	private Integer mchSettingStatus;
	
	/** 结算状态 0位正常 1未不正常 **/
	private Integer mchIssueStatus;
	
	/**消费类型1表示支付宝2表示其他（比如支付宝，网银支付等等）*/
	private String mchPayType;
	
	/**退单状态1表示已完成2表示其他已退单*/
	private String mchStatus;
	
	/**劵消费 金额*/
	private String  mchConsumeCouponCount;
	
	/** 发卡商 **/
	private String mpCardIssuer;
	
	/** 套餐名称 **/
	private String mpName;
	
	/** 套餐类别 **/
	private String mpType;
	
	/** 判断是否是查询 **/
	private Integer queryData;
	
	public Integer getMchIssueStatus() {
		return mchIssueStatus;
	}

	public void setMchIssueStatus(Integer mchIssueStatus) {
		this.mchIssueStatus = mchIssueStatus;
	}
	
	public String getMchConsumeCouponCount() {
		return mchConsumeCouponCount;
	}

	public void setMchConsumeCouponCount(String mchConsumeCouponCount) {
		this.mchConsumeCouponCount = mchConsumeCouponCount;
	}

	public String getMchStatus() {
		return mchStatus;
	}

	public void setMchStatus(String mchStatus) {
		this.mchStatus = mchStatus;
	}

	public String getMchPayType() {
		return mchPayType;
	}

	public void setMchPayType(String mchPayType) {
		this.mchPayType = mchPayType;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchId() {
		return this.mchId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setMchMoney(String mchMoney) {
		try {
			Double.valueOf(mchMoney);
			this.mchMoney = DESC.firstEncryption(mchMoney);
		} catch(NumberFormatException e) {
			this.mchMoney = mchMoney;
		}
	}

	public String getMchMoney() {
		if(this.getQueryData() != null && this.getQueryData() == 1) {
			return this.mchMoney;
		}
		return DESC.firstDeyption(this.mchMoney);
	}
	
	public String getMchCosts() {
		if(this.getQueryData() != null && this.getQueryData() == 1) {
			return this.mchCosts;
		}
		return DESC.firstDeyption(this.mchCosts);
		
	}

	public void setMchCosts(String mchCosts) {
		try {
			Double.valueOf(mchCosts);
			this.mchCosts = DESC.firstDeyption(mchCosts);
		} catch(NumberFormatException e) {
			this.mchCosts = mchCosts;
		}
		
	}

	public void setMchBshopGiveIntegral(String mchBshopGiveIntegral) {
		this.mchBshopGiveIntegral = mchBshopGiveIntegral;
	}

	public String getMchBshopGiveIntegral() {
		return DESC.firstDeyption(this.mchBshopGiveIntegral);
	}

	public void setMchTotalIntegral(String mchTotalIntegral) {
		this.mchTotalIntegral = mchTotalIntegral;
	}

	public String getMchTotalIntegral() {
		return DESC.firstDeyption(this.mchTotalIntegral);
	}

	public void setBcLevel(String bcLevel) {
		this.bcLevel = bcLevel;
	}

	public String getBcLevel() {
		return this.bcLevel;
	}

	public void setMchCurrentReturnedIntegral(String mchCurrentReturnedIntegral) {
		this.mchCurrentReturnedIntegral = mchCurrentReturnedIntegral;
	}

	public String getMchCurrentReturnedIntegral() {
		return DESC.firstDeyption(this.mchCurrentReturnedIntegral);
	}

	public void setMchNextReturnIntegral(String mchNextReturnIntegral) {
		this.mchNextReturnIntegral = mchNextReturnIntegral;
	}

	public String getMchNextReturnIntegral() {
		return DESC.firstDeyption(this.mchNextReturnIntegral);
	}

	public void setMchReturnProfitIntegral(String mchReturnProfitIntegral) {
		this.mchReturnProfitIntegral = mchReturnProfitIntegral;
	}

	public String getMchReturnProfitIntegral() {
		return DESC.firstDeyption(this.mchReturnProfitIntegral);
	}

	public void setMchFunds(String mchFunds) {
		this.mchFunds = mchFunds;
	}

	public String getMchFunds() {
		return DESC.firstDeyption(this.mchFunds);
	}

	public void setMchCurrentGetIntegral(String mchCurrentGetIntegral) {
		this.mchCurrentGetIntegral = mchCurrentGetIntegral;
	}

	public String getMchCurrentGetIntegral() {
		return DESC.firstDeyption(this.mchCurrentGetIntegral);
	}

	public void setMchCreatedDate(java.util.Date mchCreatedDate) {
		this.mchCreatedDate = mchCreatedDate;
	}

	public java.util.Date getMchCreatedDate() {
		return this.mchCreatedDate;
	}

	public void setMchUpdatedDate(java.util.Date mchUpdatedDate) {
		this.mchUpdatedDate = mchUpdatedDate;
	}

	public java.util.Date getMchUpdatedDate() {
		return this.mchUpdatedDate;
	}

	public void setMchUpdatedBy(String mchUpdatedBy) {
		this.mchUpdatedBy = mchUpdatedBy;
	}

	public String getMchUpdatedBy() {
		return this.mchUpdatedBy;
	}

	public void setMchOrginCategory(String mchOrginCategory) {
		this.mchOrginCategory = mchOrginCategory;
	}

	public String getMchOrginCategory() {
		return this.mchOrginCategory;
	}

	public void setMchProductName(String mchProductName) {
		this.mchProductName = mchProductName;
	}

	public String getMchProductName() {
		return this.mchProductName;
	}

	public void setMchOrginType(String mchOrginType) {
		this.mchOrginType = mchOrginType;
	}

	public String getMchOrginType() {
		return this.mchOrginType;
	}

	public void setMchOrginMemId(String mchOrginMemId) {
		this.mchOrginMemId = mchOrginMemId;
	}

	public String getMchOrginMemId() {
		return this.mchOrginMemId;
	}

	public void setMchRemark(String mchRemark) {
		this.mchRemark = mchRemark;
	}

	public String getMchRemark() {
		return this.mchRemark;
	}

	public void setMchSettingStatus(Integer mchSettingStatus) {
		this.mchSettingStatus = mchSettingStatus;
	}

	public Integer getMchSettingStatus() {
		return this.mchSettingStatus;
	}

	public String getMpCardIssuer() {
		return mpCardIssuer;
	}

	public void setMpCardIssuer(String mpCardIssuer) {
		this.mpCardIssuer = mpCardIssuer;
	}

	public String getMpName() {
		return mpName;
	}

	public void setMpName(String mpName) {
		this.mpName = mpName;
	}

	public String getMpType() {
		return mpType;
	}

	public void setMpType(String mpType) {
		this.mpType = mpType;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getQueryData() {
		return queryData;
	}

	public void setQueryData(Integer queryData) {
		this.queryData = queryData;
	}
	
}
