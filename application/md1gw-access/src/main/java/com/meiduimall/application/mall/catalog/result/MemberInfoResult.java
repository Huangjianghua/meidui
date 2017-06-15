package com.meiduimall.application.mall.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 会员基本信息
 * @author yangchang
 */
public class MemberInfoResult {
	
	/** 登录名 */
	@JsonProperty("login_name")
	private String loginName = "";

	/** 会员昵称 */
	@JsonProperty("nick_name")
	private String nickName = "";

	/** 性别 */
	private String sex = "";

	/** 用户APP头像地址 */
	@JsonProperty("pic_url")
	private String picUrl = "";

	/** 手机号 */
	private String phone = "";

	/** 邮箱 */
	private String email = "";

	/** 生日 */
	private String birthday = "";

	/** 可用积分 */
	@JsonProperty("use_points")
	private String usePoints = "0";

	/** 全部积分(包括冻结积分) */
	@JsonProperty("all_points")
	private String allPoints = "0";

	/** 冻结积分 */
	@JsonProperty("freeze_points")
	private String freezePoints = "0";

	/** 可用余额 */
	@JsonProperty("use_money")
	private String useMoney = "0";

	/** 全部余额(包括冻结余额) */
	@JsonProperty("all_money")
	private String allMoney = "0";

	/** 冻结余额 */
	@JsonProperty("freeze_money")
	private String freezeMoney = "0";

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUsePoints() {
		return usePoints;
	}

	public void setUsePoints(String usePoints) {
		this.usePoints = usePoints;
	}

	public String getAllPoints() {
		return allPoints;
	}

	public void setAllPoints(String allPoints) {
		this.allPoints = allPoints;
	}

	public String getFreezePoints() {
		return freezePoints;
	}

	public void setFreezePoints(String freezePoints) {
		this.freezePoints = freezePoints;
	}

	public String getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(String useMoney) {
		this.useMoney = useMoney;
	}

	public String getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public String getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(String freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
}
