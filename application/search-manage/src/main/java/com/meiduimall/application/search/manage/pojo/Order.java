package com.meiduimall.application.search.manage.pojo;

import java.io.Serializable;

import com.meiduimall.application.search.manage.utility.DESC;

/**
 * 
 * 会员消费记录表
 * 
 **/
public class Order implements Serializable {
	

	private static final long serialVersionUID = -2877438787646171674L;

	/** 会员消费记录表ID **/
	private String mchId;

	/** 会员ID **/
	private String memId;

	/** 订单号 **/
	private String orderId;
	
	/** 套餐id **/
	private String pId;
	
	/**套餐类型**/
	private String mchType;

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

	/** 结算状态 0位正常 1未不正常 **/
	private Integer mchSettingStatus;
	
	/** 结算状态 0位正常 1未不正常 **/
	private Integer mchIssueStatus;
	
	public Integer getMchIssueStatus() {
		return mchIssueStatus;
	}

	public void setMchIssueStatus(Integer mchIssueStatus) {
		this.mchIssueStatus = mchIssueStatus;
	}

	/**消费类型1表示支付宝2表示其他（比如支付宝，网银支付等等）*/
	private String mchPayType;
	
	/**退单状态1表示已完成2表示其他已退单*/
	private String mchStatus;
	
	/**劵消费 金额*/
	private String  mchConsumeCouponCount;

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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setMchMoney(String mchMoney) {
		this.mchMoney = mchMoney;
	}

	public String getMchMoney() {
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchMoney==null?"0":this.mchMoney);
			return this.mchMoney;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchMoney,this.memId);
		}
	}

	public String getMchCosts() {
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchCosts==null?"0":this.mchCosts);
			return this.mchCosts;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchCosts,this.memId);
		}
	}

	public void setMchCosts(String mchCosts) {
		this.mchCosts = mchCosts;
	}

	public void setMchBshopGiveIntegral(String mchBshopGiveIntegral) {
		this.mchBshopGiveIntegral = mchBshopGiveIntegral;
	}

	public String getMchBshopGiveIntegral() {
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchBshopGiveIntegral==null?"0":this.mchBshopGiveIntegral);
			return this.mchBshopGiveIntegral;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchBshopGiveIntegral,this.memId);
		}
		
	}

	public void setMchTotalIntegral(String mchTotalIntegral) {
		this.mchTotalIntegral = mchTotalIntegral;
	}

	public String getMchTotalIntegral() {
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchTotalIntegral==null?"0":this.mchTotalIntegral);
			return this.mchTotalIntegral;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchTotalIntegral,this.memId);
		}
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
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchCurrentReturnedIntegral==null?"0":this.mchCurrentReturnedIntegral);
			return this.mchCurrentReturnedIntegral;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchCurrentReturnedIntegral,this.memId);
		}
	}

	public void setMchNextReturnIntegral(String mchNextReturnIntegral) {
		this.mchNextReturnIntegral = mchNextReturnIntegral;
	}

	public String getMchNextReturnIntegral() {
		// 判断是否是加密的字符串
		try {
			
		} catch(NumberFormatException e) {
			
		}
		return DESC.firstDeyption(this.mchNextReturnIntegral,this.memId);
	}

	public void setMchReturnProfitIntegral(String mchReturnProfitIntegral) {
		this.mchReturnProfitIntegral = mchReturnProfitIntegral;
	}

	public String getMchReturnProfitIntegral() {
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchReturnProfitIntegral==null?"0":this.mchReturnProfitIntegral);
			return this.mchReturnProfitIntegral;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchReturnProfitIntegral,this.memId);
		}
	}

	public void setMchFunds(String mchFunds) {
		this.mchFunds = mchFunds;
	}

	public String getMchFunds() {
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchFunds==null?"0":this.mchFunds);
			return this.mchFunds;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchFunds,this.memId);
		}
	}

	public void setMchCurrentGetIntegral(String mchCurrentGetIntegral) {
		this.mchCurrentGetIntegral = mchCurrentGetIntegral;
	}

	public String getMchCurrentGetIntegral() {
		// 判断是否是加密的字符串
		try {
			Double.valueOf(this.mchCurrentGetIntegral==null?"0":this.mchCurrentGetIntegral);
			return this.mchCurrentGetIntegral;
		} catch(NumberFormatException e) {
			return DESC.firstDeyption(this.mchCurrentGetIntegral,this.memId);
		}
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){  
            return false;  
        }else {           
                if(this.getClass() == obj.getClass()){  
                    Order o = (Order) obj;  
                    if(this.getBcLevel().equals(o.getBcLevel())
                    		&& this.getMchBshopGiveIntegral().equals(o.getMchBshopGiveIntegral())
                    		&& this.getMchConsumeCouponCount().equals(o.getMchConsumeCouponCount())
                    		&& this.getMchCosts().equals(o.getMchCosts())
                    		&& this.getMchCreatedDate().getTime() == o.getMchCreatedDate().getTime()
                    		&& this.getMchCurrentGetIntegral().equals(o.getMchCurrentGetIntegral())
                    		&& this.getMchCurrentReturnedIntegral().equals(o.getMchCurrentReturnedIntegral())
                    		&& this.getMchFunds().equals(o.getMchFunds())
                    		&& this.getMchId().equals(o.getMchId())
                    		&& this.getMchIssueStatus().equals(o.getMchIssueStatus())
                    		&& this.getMchMoney().equals(o.getMchMoney())
                    		&& this.getMchNextReturnIntegral().equals(o.getMchNextReturnIntegral())
                    		&& this.getMchOrginCategory().equals(o.getMchOrginCategory())
                    		&& this.getMchOrginMemId().equals(o.getMchOrginMemId())
                    		&& this.getMchOrginType().equals(o.getMchOrginType())
                    		&& this.getMchPayType().equals(o.getMchPayType())
                    		&& this.getMchProductName().equals(o.getMchProductName())
                    		&& this.getMchRemark().equals(o.getMchRemark())
                    		&& this.getMchReturnProfitIntegral().equals(o.getMchReturnProfitIntegral())
                    		&& this.getMchSettingStatus().equals(o.getMchSettingStatus())
                    		&& this.getMchStatus().equals(o.getMchStatus())
                    		&& this.getMchTotalIntegral().equals(o.getMchTotalIntegral())
                    		&& this.getMchUpdatedBy().equals(o.getMchUpdatedBy())
                    		&& this.getMchUpdatedDate().getTime() == o.getMchUpdatedDate().getTime()
                    		&& this.getMemId().equals(o.getMemId())
                    		&& this.getOrderId().equals(o.getOrderId())
                    		&& this.getpId().equals(o.getpId())) {
                    	return true;
                    } else {
                    	return false;
                    }
                  
            }else{  
                return false;  
            }  
        }     
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bcLevel == null) ? 0 : bcLevel.hashCode());
		result = prime * result + ((mchBshopGiveIntegral == null) ? 0 : mchBshopGiveIntegral.hashCode());
		result = prime * result + ((mchConsumeCouponCount == null) ? 0 : mchConsumeCouponCount.hashCode());
		result = prime * result + ((mchCosts == null) ? 0 : mchCosts.hashCode());
		result = prime * result + ((mchCreatedDate == null) ? 0 : mchCreatedDate.hashCode());
		result = prime * result + ((mchCurrentGetIntegral == null) ? 0 : mchCurrentGetIntegral.hashCode());
		result = prime * result + ((mchCurrentReturnedIntegral == null) ? 0 : mchCurrentReturnedIntegral.hashCode());
		result = prime * result + ((mchFunds == null) ? 0 : mchFunds.hashCode());
		result = prime * result + ((mchId == null) ? 0 : mchId.hashCode());
		result = prime * result + ((mchIssueStatus == null) ? 0 : mchIssueStatus.hashCode());
		result = prime * result + ((mchMoney == null) ? 0 : mchMoney.hashCode());
		result = prime * result + ((mchNextReturnIntegral == null) ? 0 : mchNextReturnIntegral.hashCode());
		result = prime * result + ((mchOrginCategory == null) ? 0 : mchOrginCategory.hashCode());
		result = prime * result + ((mchOrginMemId == null) ? 0 : mchOrginMemId.hashCode());
		result = prime * result + ((mchOrginType == null) ? 0 : mchOrginType.hashCode());
		result = prime * result + ((mchPayType == null) ? 0 : mchPayType.hashCode());
		result = prime * result + ((mchProductName == null) ? 0 : mchProductName.hashCode());
		result = prime * result + ((mchRemark == null) ? 0 : mchRemark.hashCode());
		result = prime * result + ((mchReturnProfitIntegral == null) ? 0 : mchReturnProfitIntegral.hashCode());
		result = prime * result + ((mchSettingStatus == null) ? 0 : mchSettingStatus.hashCode());
		result = prime * result + ((mchStatus == null) ? 0 : mchStatus.hashCode());
		result = prime * result + ((mchTotalIntegral == null) ? 0 : mchTotalIntegral.hashCode());
		result = prime * result + ((mchType == null) ? 0 : mchType.hashCode());
		result = prime * result + ((mchUpdatedBy == null) ? 0 : mchUpdatedBy.hashCode());
		result = prime * result + ((mchUpdatedDate == null) ? 0 : mchUpdatedDate.hashCode());
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
		return result;
	}


	public String getMchType() {
		return mchType;
	}

	public void setMchType(String mchType) {
		this.mchType = mchType;
	}

	
}
