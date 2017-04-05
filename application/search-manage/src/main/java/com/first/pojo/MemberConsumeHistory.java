package com.first.pojo;

/**
 * 
 * 会员消费记录表
 * 
 **/
public class MemberConsumeHistory {

	/** 会员消费记录表ID **/
	private String mchId;

	/** 会员ID **/
	private String memId;

	/** 订单号 **/
	private String orderId;

	/** 消费金额 **/
	private String mchMoney;
	
	/** 成本金额 **/
	private String costMoney;
	
	/** 话费充值金额 **/
	private String billMoney;
	
	/** 流量充值金额 **/
	private String flowMoney;
	
	/** 油卡充值金额 **/
	private String oilMoney;
	
	/**移动流量充值成本*/
	private String mobileMoney;
	
	/**联通流量充值成本*/
	private String linkMoney;
	
	/**电信流量充值成本*/
	private String telecommunicationsMoney;

	/** 商家赠送积分 **/
	private String mchBshopGiveIntegral;
	
	/**充话费赠送积分 */
	private String billIntegral;
	
	/**充流量赠送积分 */
	private String flowIntegral;
	
	/**充油卡赠送积分 */
	private String oilIntegral;

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
	private String  mchCreatedDate;

	/** 更新时间 **/
	private String mchUpdatedDate;

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
	
	/**消费类型1表示支付宝2表示其他（比如支付宝，网银支付等等）*/
	private String mchPayType;
	
	/**退单状态1表示已完成2表示其他已退单*/
	private String mchStatus;
	
	/**订单类型1话费2流量3油卡*/
	private String mpType;
	
	/**劵消费 金额*/
	private String  mchConsumeCouponCount;
	
	/**流量充值成功数 */
	private String flowRechargeSuccessAccount;
	
	/**流量充值失败数 */
	private String flowRechargeFailAccount;
	
	/**话费充值失败数 */
	private String billRechargeFailAccount;
	
	/**话费充值成功数 */
	private String billRechargeSuccessAccount;
	
	/**油卡充值成功数 */
	private String oilCardRechargeSuccessAccount;
	
	/**油卡充值失败数 */
	private String oilCardRechargeFailAccount;
	
	/**油卡充值总数 */
	private String oilCardRechargeTotalAccount;
	
	/**话费值总数 */
	private String billCardRechargeTotalAccount;
	
	/**流量充值总数 */
	private String flowCardRechargeTotalAccount;
	
	/**充值成功总数 */
	private String rechargeSuccessAccount;
	
	/**充值失败总数 */
	private String rechargeFailAccount;
	
	

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	//根据id 进行解密
	public String getMchMoney() {
		return mchMoney;
	}

	public void setMchMoney(String mchMoney) {
		this.mchMoney = mchMoney;
	}

	public String getMchBshopGiveIntegral() {
		return mchBshopGiveIntegral;
	}

	public void setMchBshopGiveIntegral(String mchBshopGiveIntegral) {
		this.mchBshopGiveIntegral = mchBshopGiveIntegral;
	}

	public String getMchTotalIntegral() {
		return mchTotalIntegral;
	}

	public void setMchTotalIntegral(String mchTotalIntegral) {
		this.mchTotalIntegral = mchTotalIntegral;
	}

	public String getBcLevel() {
		return bcLevel;
	}

	public void setBcLevel(String bcLevel) {
		this.bcLevel = bcLevel;
	}

	public String getMchCurrentReturnedIntegral() {
		return mchCurrentReturnedIntegral;
	}

	public void setMchCurrentReturnedIntegral(String mchCurrentReturnedIntegral) {
		this.mchCurrentReturnedIntegral = mchCurrentReturnedIntegral;
	}

	public String getMchNextReturnIntegral() {
		return mchNextReturnIntegral;
	}

	public void setMchNextReturnIntegral(String mchNextReturnIntegral) {
		this.mchNextReturnIntegral = mchNextReturnIntegral;
	}

	public String getMchReturnProfitIntegral() {
		return mchReturnProfitIntegral;
	}

	public void setMchReturnProfitIntegral(String mchReturnProfitIntegral) {
		this.mchReturnProfitIntegral = mchReturnProfitIntegral;
	}

	public String getMchFunds() {
		return mchFunds;
	}

	public void setMchFunds(String mchFunds) {
		this.mchFunds = mchFunds;
	}

	public String getMchCurrentGetIntegral() {
		return mchCurrentGetIntegral;
	}

	public void setMchCurrentGetIntegral(String mchCurrentGetIntegral) {
		this.mchCurrentGetIntegral = mchCurrentGetIntegral;
	}


	public String getMchCreatedDate() {
		return mchCreatedDate;
	}

	public void setMchCreatedDate(String mchCreatedDate) {
		this.mchCreatedDate = mchCreatedDate;
	}

	public String getMchUpdatedDate() {
		return mchUpdatedDate;
	}

	public void setMchUpdatedDate(String mchUpdatedDate) {
		this.mchUpdatedDate = mchUpdatedDate;
	}

	public String getMchUpdatedBy() {
		return mchUpdatedBy;
	}

	public void setMchUpdatedBy(String mchUpdatedBy) {
		this.mchUpdatedBy = mchUpdatedBy;
	}

	public String getMchOrginCategory() {
		return mchOrginCategory;
	}

	public void setMchOrginCategory(String mchOrginCategory) {
		this.mchOrginCategory = mchOrginCategory;
	}

	public String getMchProductName() {
		return mchProductName;
	}

	public void setMchProductName(String mchProductName) {
		this.mchProductName = mchProductName;
	}

	public String getMchOrginType() {
		return mchOrginType;
	}

	public void setMchOrginType(String mchOrginType) {
		this.mchOrginType = mchOrginType;
	}

	public String getMchOrginMemId() {
		return mchOrginMemId;
	}

	public void setMchOrginMemId(String mchOrginMemId) {
		this.mchOrginMemId = mchOrginMemId;
	}

	public String getMchRemark() {
		return mchRemark;
	}

	public void setMchRemark(String mchRemark) {
		this.mchRemark = mchRemark;
	}

	public Integer getMchSettingStatus() {
		return mchSettingStatus;
	}

	public void setMchSettingStatus(Integer mchSettingStatus) {
		this.mchSettingStatus = mchSettingStatus;
	}

	public Integer getMchIssueStatus() {
		return mchIssueStatus;
	}

	public void setMchIssueStatus(Integer mchIssueStatus) {
		this.mchIssueStatus = mchIssueStatus;
	}

	public String getMchPayType() {
		return mchPayType;
	}

	public void setMchPayType(String mchPayType) {
		this.mchPayType = mchPayType;
	}

	public String getMchStatus() {
		return mchStatus;
	}

	public void setMchStatus(String mchStatus) {
		this.mchStatus = mchStatus;
	}

	public String getMchConsumeCouponCount() {
		return mchConsumeCouponCount;
	}

	public void setMchConsumeCouponCount(String mchConsumeCouponCount) {
		this.mchConsumeCouponCount = mchConsumeCouponCount;
	}

	public String getFlowRechargeSuccessAccount() {
		return flowRechargeSuccessAccount;
	}

	public void setFlowRechargeSuccessAccount(String flowRechargeSuccessAccount) {
		this.flowRechargeSuccessAccount = flowRechargeSuccessAccount;
	}

	public String getFlowRechargeFailAccount() {
		return flowRechargeFailAccount;
	}

	public void setFlowRechargeFailAccount(String flowRechargeFailAccount) {
		this.flowRechargeFailAccount = flowRechargeFailAccount;
	}

	public String getBillRechargeFailAccount() {
		return billRechargeFailAccount;
	}

	public void setBillRechargeFailAccount(String billRechargeFailAccount) {
		this.billRechargeFailAccount = billRechargeFailAccount;
	}

	public String getBillRechargeSuccessAccount() {
		return billRechargeSuccessAccount;
	}

	public void setBillRechargeSuccessAccount(String billRechargeSuccessAccount) {
		this.billRechargeSuccessAccount = billRechargeSuccessAccount;
	}

	public String getOilCardRechargeSuccessAccount() {
		return oilCardRechargeSuccessAccount;
	}

	public void setOilCardRechargeSuccessAccount(String oilCardRechargeSuccessAccount) {
		this.oilCardRechargeSuccessAccount = oilCardRechargeSuccessAccount;
	}

	public String getOilCardRechargeFailAccount() {
		return oilCardRechargeFailAccount;
	}

	public void setOilCardRechargeFailAccount(String oilCardRechargeFailAccount) {
		this.oilCardRechargeFailAccount = oilCardRechargeFailAccount;
	}

	public String getOilCardRechargeTotalAccount() {
		return oilCardRechargeTotalAccount;
	}

	public void setOilCardRechargeTotalAccount(String oilCardRechargeTotalAccount) {
		this.oilCardRechargeTotalAccount = oilCardRechargeTotalAccount;
	}

	public String getBillCardRechargeTotalAccount() {
		return billCardRechargeTotalAccount;
	}

	public void setBillCardRechargeTotalAccount(String billCardRechargeTotalAccount) {
		this.billCardRechargeTotalAccount = billCardRechargeTotalAccount;
	}

	public String getFlowCardRechargeTotalAccount() {
		return flowCardRechargeTotalAccount;
	}

	public void setFlowCardRechargeTotalAccount(String flowCardRechargeTotalAccount) {
		this.flowCardRechargeTotalAccount = flowCardRechargeTotalAccount;
	}

	public String getRechargeSuccessAccount() {
		return rechargeSuccessAccount;
	}

	public void setRechargeSuccessAccount(String rechargeSuccessAccount) {
		this.rechargeSuccessAccount = rechargeSuccessAccount;
	}

	public String getRechargeFailAccount() {
		return rechargeFailAccount;
	}

	public void setRechargeFailAccount(String rechargeFailAccount) {
		this.rechargeFailAccount = rechargeFailAccount;
	}

	public String getMpType() {
		return mpType;
	}

	public void setMpType(String mpType) {
		this.mpType = mpType;
	}

	public String getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}

	public String getFlowMoney() {
		return flowMoney;
	}

	public void setFlowMoney(String flowMoney) {
		this.flowMoney = flowMoney;
	}

	public String getOilMoney() {
		return oilMoney;
	}

	public void setOilMoney(String oilMoney) {
		this.oilMoney = oilMoney;
	}

	public String getBillIntegral() {
		return billIntegral;
	}

	public void setBillIntegral(String billIntegral) {
		this.billIntegral = billIntegral;
	}

	public String getFlowIntegral() {
		return flowIntegral;
	}

	public void setFlowIntegral(String flowIntegral) {
		this.flowIntegral = flowIntegral;
	}

	public String getOilIntegral() {
		return oilIntegral;
	}

	public void setOilIntegral(String oilIntegral) {
		this.oilIntegral = oilIntegral;
	}

	public String getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(String costMoney) {
		this.costMoney = costMoney;
	}

	public String getMobileMoney() {
		return mobileMoney;
	}

	public void setMobileMoney(String mobileMoney) {
		this.mobileMoney = mobileMoney;
	}

	public String getLinkMoney() {
		return linkMoney;
	}

	public void setLinkMoney(String linkMoney) {
		this.linkMoney = linkMoney;
	}

	public String getTelecommunicationsMoney() {
		return telecommunicationsMoney;
	}

	public void setTelecommunicationsMoney(String telecommunicationsMoney) {
		this.telecommunicationsMoney = telecommunicationsMoney;
	}
	
}
