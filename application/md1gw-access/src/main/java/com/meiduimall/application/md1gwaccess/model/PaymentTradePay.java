package com.meiduimall.application.md1gwaccess.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PaymentTradePay implements Serializable{

	 
	private static final long serialVersionUID = 2123132076151876724L;
	private String pay_password;    //"=>"ayaj787177",//支付密码
	
	private Map<String,String> payment = new HashMap<String,String>(); 
	
	private Payment paymentObj = null;
	
	public static class Payment implements Externalizable{
		private String paymentId; //"=>"16112901080187172271",//支付单号,必须
		private BigDecimal money = new BigDecimal(0); //"=>"2555.00",//第三方支付金额,必须
		private BigDecimal orderMoney = new BigDecimal(0); //"=>"2599.00",//订单总金额,必须
		private BigDecimal orderXfc = new BigDecimal(0);//"=>"0.00",
		private String tids; //"=>"16112813461255447227",//商家订单号
		private String payway; //"=>"2", // 2积分支付，1 余额支付
		private String curHash; //"=>"",
		private String payFrom; //"=>"android",
		private String packageName; //"=>"1gw",
		private Integer pointPay; //"=>"44", //使用积分支付金额
		private BigDecimal payMoney = new BigDecimal(0); //"=>"0.00", //使用余额支付金额
		private String payAppId; //"=>"appAlipay", //wxpayjsapi微信，appAlipay 支付宝
		
		 
		public String getTids() {
			return tids;
		}
		public void setTids(String tids) {
			this.tids = tids;
		}
		public String getPayway() {
			return payway;
		}
		public void setPayway(String payway) {
			this.payway = payway;
		}
		public BigDecimal getMoney() {
			return money;
		}
		public void setMoney(BigDecimal money) {
			this.money = money;
		}
		
	
	
		 
		 
		public String getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(String paymentId) {
			this.paymentId = paymentId;
		}
		public BigDecimal getOrderMoney() {
			return orderMoney;
		}
		public void setOrderMoney(BigDecimal orderMoney) {
			this.orderMoney = orderMoney;
		}
		public BigDecimal getOrderXfc() {
			return orderXfc;
		}
		public void setOrderXfc(BigDecimal orderXfc) {
			this.orderXfc = orderXfc;
		}
		public String getCurHash() {
			return curHash;
		}
		public void setCurHash(String curHash) {
			this.curHash = curHash;
		}
		public String getPayFrom() {
			return payFrom;
		}
		public void setPayFrom(String payFrom) {
			this.payFrom = payFrom;
		}
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		public Integer getPointPay() {
			return pointPay;
		}
		public void setPointPay(Integer pointPay) {
			this.pointPay = pointPay;
		}
		public BigDecimal getPayMoney() {
			return payMoney;
		}
		public void setPayMoney(BigDecimal payMoney) {
			this.payMoney = payMoney;
		}
		 
		public String getPayAppId() {
			return payAppId;
		}
		public void setPayAppId(String payAppId) {
			this.payAppId = payAppId;
		}
		/**
		 * 序列化操作的扩展类
		 */
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
	        out.writeObject(paymentId);
	        out.writeObject(money);
	        out.writeObject(orderMoney);
	        out.writeObject(orderXfc);
	        out.writeObject(tids);
	        out.writeObject(payway);
	        out.writeObject(curHash);
	        out.writeObject(payFrom);
	        out.writeObject(packageName);
	        out.writeObject(pointPay);
	        out.writeObject(payMoney);
	        out.writeObject(payAppId);
			
		}
		 /**
	     * 反序列化的扩展类
	     */
		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			paymentId=(String) in.readObject();
			money=(BigDecimal) in.readObject();
			orderMoney=(BigDecimal) in.readObject();
			orderXfc=(BigDecimal) in.readObject();
			tids=(String) in.readObject();
			payway=(String) in.readObject();
			curHash=(String) in.readObject();
			payFrom=(String) in.readObject();
			packageName=(String) in.readObject();
			pointPay=(Integer) in.readObject();
			payMoney=(BigDecimal) in.readObject();
			payAppId=(String) in.readObject();
			
		}
		
		
	}
	private String max_point; //=>"44", //本次最多可使用的积分金额
	private String user_id = "308836";    //= 用户商城id, //后台合并数组，非用户提交
	private String platform; //  = "wap" //触屏版固定值，后台合并数组，非用户提交
	private String token;
	
	
	
	

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Map<String, String> getPayment() {
		
		return payment;
	}
	public Payment getPaymentObj() {
		paymentObj=null;
		if(null==paymentObj) {
			paymentObj = new Payment();
			paymentObj.setPaymentId(payment.get("payment_id"));
			paymentObj.setMoney(new BigDecimal(payment.get("money")));
			paymentObj.setOrderMoney(new BigDecimal(payment.get("order_money")));
			paymentObj.setOrderXfc(new BigDecimal(payment.get("order_xfc")));
			paymentObj.setTids(payment.get("tids"));
			paymentObj.setPayway(payment.get("payway"));
			paymentObj.setCurHash(payment.get("cur_hash"));
			paymentObj.setPayFrom(payment.get("pay_from"));
			paymentObj.setPackageName(payment.get("package_name"));
			paymentObj.setPointPay(Integer.valueOf(payment.get("point_pay")));
			paymentObj.setPayMoney(new BigDecimal(payment.get("pay_money")));
			paymentObj.setPayAppId(payment.get("pay_app_id"));
		}
		return paymentObj;
	}
	public void setPayment(Map<String, String> payment) {
		this.payment = payment;
	}
	 
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	public String getMax_point() {
		return max_point;
	}
	public void setMax_point(String max_point) {
		this.max_point = max_point;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setPaymentObj(Payment paymentObj) {
		this.paymentObj = paymentObj;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
}
