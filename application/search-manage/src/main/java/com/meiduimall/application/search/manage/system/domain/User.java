package com.meiduimall.application.search.manage.system.domain;

import java.io.Serializable;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.utility.DESC;

public class User extends PageView implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 帐号名称
	 */
	private String userName;
	/**
	 * 帐号密码
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 最后修改时间
	 */
	private String updateTime;
	/**
	 * 创建人
	 */
	private String createAccount;
	/**
	 * 最后修改人
	 */
	private String updateAccount;
	/**
	 * 帐号状态(y:启用,n:禁用；默认y)
	 */
	private String status;
	/**
	 * 角色id
	 */
	private String rid;
	
	private String nickName;
	
	/**
	 * 角色名称
	 */
	private String role_name;
	
	/**
	 * 验证码
	 */
	private String vcode ;
	
	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 
	 * 说明
	 */
	private String explanation;
	
	/**
	 * 加密用户名
	 */
	@SuppressWarnings("unused")
	private String enUserName ;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public String getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getEnUserName() {
		if(null==DESC.firstDeyption(this.userName)){
			return this.userName;
		}
		return  DESC.firstDeyption(this.userName);
		//return enUserName;
	}

	public void setEnUserName(String enUserName) {
		this.enUserName = enUserName;
	}

}
