package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import com.meiduimall.service.member.util.PageHelp;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月3日 下午12:01:16 0.1 
 * Description: 登陆解锁列表request Model
 */
public class RequestLoginUnlock extends PageHelp implements Serializable {

	private static final long serialVersionUID = 520498521348518599L;
	/** 用户账户 */
	private String memLoginName;
	/** 手机号 */
	private String memPhone;
	
	/**邮箱 */
	private String memEmail;
	
	private String flag;

	/**
	 * @return the memLoginName
	 */
	public String getMemLoginName() {
		return memLoginName;
	}

	/**
	 * @param memLoginName the memLoginName to set
	 */
	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}

	/**
	 * @return the memPhone
	 */
	public String getMemPhone() {
		return memPhone;
	}

	/**
	 * @param memPhone the memPhone to set
	 */
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	/**
	 * @return the memEmail
	 */
	public String getMemEmail() {
		return memEmail;
	}

	/**
	 * @param memEmail the memEmail to set
	 */
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	

}
