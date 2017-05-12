package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysuserUser implements Serializable {
    private Integer userId;

    private Integer gradeId;

    private String name;

    private String username;

    private Integer point;

    private BigDecimal money;

    private String coupon;

    private String payPercent;

    private String referId;

    private String referUrl;

    private Integer birthday;

    private String sex;

    private Boolean wedlock;

    private String education;

    private String vocation;

    private String regIp;

    private Integer regtime;

    private String cur;

    private String lang;

    private Boolean disabled;

    private Integer experience;

    private String source;

    private String area;

    private String addr;

    private Boolean emailVerify;

    private BigDecimal frozenMoney;

    private BigDecimal mPoint;

    private BigDecimal mFrozenPoint;

    private static final long serialVersionUID = 1L;

    
    
    public SysuserUser() {
		super();
	}

    
    
	public SysuserUser(Integer userId) {
		super();
		this.userId = userId;
	}



	public SysuserUser(Integer userId, BigDecimal frozenMoney) {
		super();
		this.userId = userId;
		this.frozenMoney = frozenMoney;
	}



	public SysuserUser(Integer userId, Integer gradeId) {
		super();
		this.userId = userId;
		this.gradeId = gradeId;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon == null ? null : coupon.trim();
    }

    public String getPayPercent() {
        return payPercent;
    }

    public void setPayPercent(String payPercent) {
        this.payPercent = payPercent == null ? null : payPercent.trim();
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId == null ? null : referId.trim();
    }

    public String getReferUrl() {
        return referUrl;
    }

    public void setReferUrl(String referUrl) {
        this.referUrl = referUrl == null ? null : referUrl.trim();
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Boolean getWedlock() {
        return wedlock;
    }

    public void setWedlock(Boolean wedlock) {
        this.wedlock = wedlock;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation == null ? null : vocation.trim();
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    public Integer getRegtime() {
        return regtime;
    }

    public void setRegtime(Integer regtime) {
        this.regtime = regtime;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur == null ? null : cur.trim();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Boolean getEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(Boolean emailVerify) {
        this.emailVerify = emailVerify;
    }

    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public BigDecimal getmPoint() {
        return mPoint;
    }

    public void setmPoint(BigDecimal mPoint) {
        this.mPoint = mPoint;
    }

    public BigDecimal getmFrozenPoint() {
        return mFrozenPoint;
    }

    public void setmFrozenPoint(BigDecimal mFrozenPoint) {
        this.mFrozenPoint = mFrozenPoint;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", gradeId=").append(gradeId);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", point=").append(point);
        sb.append(", money=").append(money);
        sb.append(", coupon=").append(coupon);
        sb.append(", payPercent=").append(payPercent);
        sb.append(", referId=").append(referId);
        sb.append(", referUrl=").append(referUrl);
        sb.append(", birthday=").append(birthday);
        sb.append(", sex=").append(sex);
        sb.append(", wedlock=").append(wedlock);
        sb.append(", education=").append(education);
        sb.append(", vocation=").append(vocation);
        sb.append(", regIp=").append(regIp);
        sb.append(", regtime=").append(regtime);
        sb.append(", cur=").append(cur);
        sb.append(", lang=").append(lang);
        sb.append(", disabled=").append(disabled);
        sb.append(", experience=").append(experience);
        sb.append(", source=").append(source);
        sb.append(", area=").append(area);
        sb.append(", addr=").append(addr);
        sb.append(", emailVerify=").append(emailVerify);
        sb.append(", frozenMoney=").append(frozenMoney);
        sb.append(", mPoint=").append(mPoint);
        sb.append(", mFrozenPoint=").append(mFrozenPoint);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}