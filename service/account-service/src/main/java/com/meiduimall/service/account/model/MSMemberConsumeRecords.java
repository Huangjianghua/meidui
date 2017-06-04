package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员消费记录表ms_member_consume_records实体类
 * @author chencong
 *
 */
public class MSMemberConsumeRecords extends BaseModel  implements Serializable{
	
	private static final long serialVersionUID = 4973129173785277755L;

	/** 会员消费记录表ID **/
	private String id;

	/** 订单号 **/
	private String orderId;
	
	/** 消费总额 **/
	private String consumeAmount;
	
	/** 消费余额**/
	private String consumeMoney;
	
	/** 消费积分 **/
	private String consumePoints;

	/** 消费时间 **/
	private Date tradeTime;
	
	/**订单来源 **/
	private String orderSource;
	
	/**消费商品名称 **/
	private String productName;
	
	/**消费类型1：表示单独使用积分支付2：混合支付3: 其他第三方支付 **/
	private String payType;
	
	/**订单状态1表示已完成2表示其他已退单 **/
	private String orderStatus;
	
	/** 创建时间 */
	private Date createDate;
	
	/** 创建人 */
	private String createUser;

	/** 修改时间 */
	private Date updateDate;
	
	/** 更新人*/
	private String updateUser;
	
	/**备注*/
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(String consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public String getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(String consumeMoney) {
		this.consumeMoney = consumeMoney;
	}

	public String getConsumePoints() {
		return consumePoints;
	}

	public void setConsumePoints(String consumePoints) {
		this.consumePoints = consumePoints;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
