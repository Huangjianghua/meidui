package com.meiduimall.service.member.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 手机号归属地
 * @author jun.wu@meiduimall.com
 *
 */
public class MSMemberMobileArea implements Serializable{
 
	private static final long serialVersionUID = 2743523925892625404L;

	private String memId;

    private String phone;

    private String provinceName;

    private String cityName;

    private String sp;

    private Date createDate;

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp == null ? null : sp.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}