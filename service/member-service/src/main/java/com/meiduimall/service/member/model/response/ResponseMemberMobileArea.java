/**
 * 
 */
package com.meiduimall.service.member.model.response;

import java.io.Serializable;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.util.DESC;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月3日 下午5:51:39 0.1 
 * Description: 返回会员锁定列表
 */
public class ResponseMemberMobileArea implements Serializable {

	private static final long serialVersionUID = 949660461284006757L;
	/** 会员系统ID **/
	private String memId;
	 
	/** 会员最新手机号 **/
	private String memPhone;
 
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
	 
 
	
}
