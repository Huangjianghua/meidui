/**
 * 
 */
package com.meiduimall.service.member.model.response;

import java.io.Serializable;
import java.util.Date;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.util.DESC;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月3日 下午5:51:39 0.1 
 * Description: 返回会员锁定列表
 */
public class MemberLockDTO implements Serializable {

	private static final long serialVersionUID = 949660461284006757L;
	/** 会员系统ID **/
	private String memId;
	/**会员姓名*/
	private String memName;
	/** 会员登录名 **/
	private String memLoginName;
	/** 会员最新手机号 **/
	private String memPhone;
	/**会员邮箱			*/
	private String memEmail;
	/**锁定次数*/
	private String memLockCount;
	/** 会员本次登录时间 **/
	private Date memLoginTime;
	/**锁定次数明文字段   */
	private String memLockCountTranslation;
	/**
	 * @return the memId
	 */
	public String getMemId() {
		return memId;
	}
	/**
	 * @param memId the memId to set
	 */
	public void setMemId(String memId) {
		this.memId = memId;
	}
	/**
	 * @return the memName
	 * @throws MdSysException 
	 */
	public String getMemName() throws MdSysException {
		return DESC.deyption(memName);
	}
	/**
	 * @param memName the memName to set
	 */
	public void setMemName(String memName) {
		this.memName = memName;
	}
	/**
	 * @return the memLoginName
	 * @throws MdSysException 
	 */
	public String getMemLoginName() throws MdSysException {
		return DESC.deyption(memLoginName);
	}
	/**
	 * @param memLoginName the memLoginName to set
	 */
	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}
	/**
	 * @return the memPhone
	 * @throws MdSysException 
	 */
	public String getMemPhone() throws MdSysException {
		return DESC.deyption(memPhone);
	}
	/**
	 * @param memPhone the memPhone to set
	 */
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	/**
	 * @return the memEmail
	 * @throws MdSysException 
	 */
	public String getMemEmail() throws MdSysException {
		return DESC.deyption(memEmail);
	}
	/**
	 * @param memEmail the memEmail to set
	 */
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	/**
	 * @return the memLockCount
	 */
	public String getMemLockCount() {
		return memLockCount;
	}
	/**
	 * @param memLockCount the memLockCount to set
	 */
	public void setMemLockCount(String memLockCount) {
		this.memLockCount = memLockCount;
	}
	/**
	 * @return the memLoginTime
	 */
	public Date getMemLoginTime() {
		return memLoginTime;
	}
	/**
	 * @param memLoginTime the memLoginTime to set
	 */
	public void setMemLoginTime(Date memLoginTime) {
		this.memLoginTime = memLoginTime;
	}
	/**
	 * @return the memLockCountTranslation
	 */
	public String getMemLockCountTranslation() {
		return memLockCountTranslation;
	}
	/**
	 * @param memLockCountTranslation the memLockCountTranslation to set
	 */
	public void setMemLockCountTranslation(String memLockCountTranslation) {
		this.memLockCountTranslation = memLockCountTranslation;
	}
	
}
