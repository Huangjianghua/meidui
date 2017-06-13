package com.meiduimall.service.member.model.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.DoubleCalculate;
import com.meiduimall.service.member.util.StringUtil;

/**
 * 会员基本信息映射响应实体
 * @author chencong
 *
 */
public class ResponseMemberBasicInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**会员id*/
	private String memId;
	
	/**会员当前积分总额*/
	@JsonProperty("total_points")
	private String totalPoints;
	
	/**会员当前可用积分总额*/
	@JsonProperty("available_points")
	private String availablePoints;

	/**会员余额总额*/
	@JsonProperty("total_money")
	private String totalMoney;
	
	/**会员余额总额*/
	@JsonProperty("available_money ")
	private String availableMoney ;

	/**登录名*/
	private String login_name;

	/**会员昵称*/
	private String nick_name;
	
	/**手机号*/
	private String phone;
	
	/**用户APP头像地址*/
	private String pic_url;
	
	/**邮箱编号*/
	private String email;
	
	/**生日*/
	private String birthday;
	
	/**性别*/
	private String sex;
	
	/**会员姓名*/
	private String name;
	
	/**注册时间*/
	private Date registertime;
	
	/** 注册年份 **/
	private String memRegYear;

	/** 会员注册月份 **/
	private String memRegMonth;

	/** 会员注册天 **/
	private String memRegDay;

	/** 会员地址，省市区中文名称组合，“;”隔开 **/
	private String memAddressShengShiQu;

	/** 会员详细地址**/
	private String memAddressDetail;

	/** 是否开启支付密码*/
	private String paypwd_isopen;
	
	/** 是否设置支付密码*/
	private String paypwd_isset;
	

	public String getName() {
		return name;
	}

	public void setName(String name) throws MdSysException {
		this.name = DESC.deyption(name);
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
		this.sex = "1".equals(sex)?"男":"女";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws MdSysException {
		this.email =DESC.deyption(email);
	}

	public String getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(String totalPoints)throws MdSysException {
		this.totalPoints = DoubleCalculate.getFormalValueTwo(DESC.deyption(totalPoints,memId));
	}
	
	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public String getAvailableMoney() {
		return availableMoney;
	}

	public void setAvailableMoney(String availableMoney) {
		this.availableMoney = availableMoney;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name)throws MdSysException {
		this.nick_name = DESC.deyption(nick_name);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws MdSysException {
		this.phone =DESC.deyption(phone);
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url)throws MdSysException {
		this.pic_url = DESC.deyption(pic_url,memId);
	}

	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
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
	
	public String getMemAddressShengShiQu() {
		return memAddressShengShiQu;
	}

	public void setMemAddressShengShiQu(String memAddressShengShiQu) {
		this.memAddressShengShiQu = memAddressShengShiQu;
	}

	public String getMemAddressDetail() {
		return memAddressDetail;
	}

	public void setMemAddressDetail(String memAddressDetail) {
		this.memAddressDetail = memAddressDetail;
	}
	
	public String getPaypwd_isopen() {
		return paypwd_isopen;
	}

	public void setPaypwd_isopen(String paypwd_isopen) {
		this.paypwd_isopen = paypwd_isopen;
	}

	public String getPaypwd_isset() {
		return paypwd_isset;
	}

	public void setPaypwd_isset(String paypwd_isset) {
		this.paypwd_isset = paypwd_isset;
	}

	
	public Date getRegistertime() {
		return registertime;
	}

	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	
	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name)throws MdSysException {
		this.login_name =DESC.deyption(login_name);
	}
	
	public String getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(String availablePoints) {
		this.availablePoints = availablePoints;
	}

	@Override
	public String toString() {
		return "ResponseMemberBasicInfo [memId=" + memId + ", totalPoints=" + totalPoints + ", availablePoints="
				+ availablePoints + ", totalmoney=" + totalMoney + ", totalpoints=" + totalPoints + ", login_name="
				+ login_name + ", nick_name=" + nick_name + ", phone=" + phone + ", pic_url=" + pic_url + ", email="
				+ email + ", birthday=" + birthday + ", sex=" + sex + ", name=" + name + ", registertime="
				+ registertime + ", memRegYear=" + memRegYear + ", memRegMonth=" + memRegMonth + ", memRegDay="
				+ memRegDay + ", memAddressShengShiQu=" + memAddressShengShiQu + ", memAddressDetail="
				+ memAddressDetail + ", paypwd_isopen=" + paypwd_isopen + ", paypwd_isset=" + paypwd_isset + "]";
	}
	
}
