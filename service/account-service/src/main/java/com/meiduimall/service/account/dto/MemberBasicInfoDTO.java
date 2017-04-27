package com.meiduimall.service.account.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.StringUtil;


/**
 * 查询会员基本信息接口用到的映射实体类
 * @author chencong
 *
 */
public class MemberBasicInfoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	/**会员id*/
	private String memId;
	
	/**用户积分总额*/
	private String totalpoints;
	
	/**级别名称*/
	/*private String level_name;*/
	
	/**会员昵称*/
	private String nick_name;
	
	/**手机号*/
	private String phone;
	
	/**用户APP头像地址*/
	private String pic_url;
	
	/**消费劵(剩余消费劵已经折合过RMB) */
	private String consume_coupon;
	
	/**订单能使用卷的最大比例*/
	private String order_percentage;
	
	/**邮箱编号*/
	private String email;
	
	private String birthday;
	
	private String sex;
	
	private String addressAreas;
	
	private String address;
	
	private String name;
	
	private String user_id;
	
	/*private String memFirstPreOrderRank;*/
	
	/*注册时间*/
	private Date registertime;


	/***
	 * 消费券
	 * @date 2016-5-13 15:55:38
	 * **/
	private String shoppingCoupon;
	
	/** 美兑积分  */
	private String consume_points;
	
	/** 会员账户余额  */
	private BigDecimal balance;
	
	/** 注册年份 **/
	private String memRegYear;

	/** 会员注册月份 **/
	private String memRegMonth;

	/** 会员注册天 **/
	private String memRegDay;
	
	public String getShoppingCoupon() {
		return shoppingCoupon;
	}

	public void setShoppingCoupon(String shoppingCoupon) {
		this.shoppingCoupon = shoppingCoupon;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) throws SystemException {
		this.user_id = DESC.deyption(user_id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name)throws SystemException {
		this.name = DESC.deyption(name);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressAreas() {
		return addressAreas;
	}

	public void setAddressAreas(String addressAreas) {
		this.addressAreas = addressAreas;
	}

	public String getBirthday() {
		if(!StringUtil.isEmptyByString(birthday))
		{
			return birthday.substring(0, 10);
		}else{
			return birthday;	
		}
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email)throws SystemException {
		this.email =DESC.deyption(email);
	}

	public String getOrder_percentage() {
		return order_percentage;
	}

	public void setOrder_percentage(String order_percentage) {
		this.order_percentage = order_percentage;
	}

	public String getConsume_coupon() {
		return consume_coupon;
	}

	public void setConsume_coupon(String consume_coupon)throws SystemException {
		this.consume_coupon =StringUtil.isEmptyByString(consume_coupon)?DESC.deyption(consume_coupon,memId):"0";
	}

	public String getTotalpoints() {
		return totalpoints;
	}

	public void setTotalpoints(String totalpoints)throws SystemException {
		this.totalpoints = DoubleCalculate.getFormalValue(DESC.deyption(totalpoints,memId));
	}

/*	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = StringUtil.isExistingSpecial(level_name)?SysEncrypConst.MEMBER_DJ:RankInfoUtils.getRankByDictId(level_name).getRankName();
	}*/

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name)throws SystemException {
		this.nick_name = DESC.deyption(nick_name);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws SystemException{
		this.phone =DESC.deyption(phone);
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url)throws SystemException {
		this.pic_url = DESC.deyption(pic_url,memId);
	}

/*	public String getMemFirstPreOrderRank() {
		return memFirstPreOrderRank;
	}

	public void setMemFirstPreOrderRank(String memFirstPreOrderRank) {
		this.memFirstPreOrderRank = memFirstPreOrderRank;
	}*/
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getConsume_points() {
		return consume_points;
	}

	public void setConsume_points(String consume_points) {
		this.consume_points = consume_points;
	}

	public String getMemRegYear() {
		return memRegYear;
	}

	public void setMemRegYear(String memRegYear) {
		this.memRegYear = memRegYear;
	}

	public String getMemRegMonth() {
		return memRegMonth;
	}

	public void setMemRegMonth(String memRegMonth) {
		this.memRegMonth = memRegMonth;
	}

	public String getMemRegDay() {
		return memRegDay;
	}

	public void setMemRegDay(String memRegDay) {
		this.memRegDay = memRegDay;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public Date getRegistertime() {
		return registertime;
	}

	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	
}
