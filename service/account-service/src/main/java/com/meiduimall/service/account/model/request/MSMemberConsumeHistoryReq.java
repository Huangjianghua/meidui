package com.meiduimall.service.account.model.request;

import java.io.Serializable;


/**
 * 会员消费记录
 * @author chencong
 *
 */
public class MSMemberConsumeHistoryReq  implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 会员消费记录表ID **/
	private String mchId;

	/** 会员ID **/
	private String memId;

	/** 订单号 **/
	private String orderId;
	
	/**消费金额**/
	private String mchMoney;
	
	/**商家赠送积分**/
	private String mchBshopGiveIntegral;

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

	/**结算状态 0已结算 1未结算 **/
	private Integer mchSettingStatus;
	
	/** 发放状态 0已发放 1未发放 **/
	private Integer mchIssueStatus;
	
	/**消费劵消费金额**/
	private String mchConsumeCouponCount;
	
	/** 美兑积分 **/
	private Double mchConsumePointsCount;
	
	/** 余额 **/
	private Double mchShoppingCouponCount;

	/**消费类型1：表示单独使用消费劵支付2：混合支付3: 其他第三方支付*/
	private String mchPayType;
	
	/**退单状态1表示已完成2表示其他已退单*/
	private String mchStatus;
	
	
	
	public String getMchConsumeCouponCount() {
		return mchConsumeCouponCount;
	}

	public void setMchConsumeCouponCount(String mchConsumeCouponCount) {
		this.mchConsumeCouponCount = mchConsumeCouponCount;
	}

	public String getMchBshopGiveIntegral() {
		return mchBshopGiveIntegral;
	}

	public void setMchBshopGiveIntegral(String mchBshopGiveIntegral) {
		this.mchBshopGiveIntegral = mchBshopGiveIntegral;
	}

	public String getMchMoney() {
		return mchMoney;
	}

	public void setMchMoney(String mchMoney) {
		this.mchMoney = mchMoney;
	}

	public Integer getMchIssueStatus() {
		return mchIssueStatus;
	}

	public void setMchIssueStatus(Integer mchIssueStatus) {
		this.mchIssueStatus = mchIssueStatus;
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

	public Double getMchConsumePointsCount() {
		if("".equals(mchConsumePointsCount) || null == mchConsumePointsCount) {
			return 0.00;
		} else {
			return mchConsumePointsCount;
		}
	}

	public void setMchConsumePointsCount(Double mchConsumePointsCount) {
		this.mchConsumePointsCount = mchConsumePointsCount;
	}

	public Double getMchShoppingCouponCount() {
		if("".equals(mchShoppingCouponCount) || null == mchShoppingCouponCount) {
			return 0.00;
		} else {
			return mchShoppingCouponCount;
		}
	}

	public void setMchShoppingCouponCount(Double mchShoppingCouponCount) {
		this.mchShoppingCouponCount = mchShoppingCouponCount;
	}

	@Override
	public String toString() {
		return "MSMemberConsumeHistory [mchId=" + mchId + ", memId=" + memId + ", orderId=" + orderId
				+ ", mchCreatedDate=" + mchCreatedDate + ", mchUpdatedDate=" + mchUpdatedDate + ", mchUpdatedBy="
				+ mchUpdatedBy + ", mchOrginCategory=" + mchOrginCategory + ", mchProductName=" + mchProductName
				+ ", mchOrginType=" + mchOrginType + ", mchOrginMemId=" + mchOrginMemId + ", mchRemark=" + mchRemark
				+ ", mchSettingStatus=" + mchSettingStatus + ", mchIssueStatus=" + mchIssueStatus
				+ ", mchConsumePointsCount=" + mchConsumePointsCount + ", mchShoppingCouponCount="
				+ mchShoppingCouponCount + ", mchPayType=" + mchPayType + ", mchStatus=" + mchStatus + "]";
	}
}
