/**
 * 
 */
package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.PageHelp;

/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: MSAccountBalanceCondition.java
 * @Author: jianhua.huang
 * @Date: 2017年4月18日 上午11:47:22
 * @Description: 会员余额查询 model request
 */
public class MSAccountListRequest extends PageHelp implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3571960708868903583L;
	private String memId;
	/** 用户账户 */
	private String memLoginName;
	/** 手机号 */
	private String memPhone;
	/** 推荐人手机 **/
	private String memParentIdPhone;
	/** 注册来源0表示PC端注册1表示o2o注册2表示会员结算系统数据迁移注册3表示壹购物注册4表示壹购物商城迁移 */
	private String memSignSource;
	/** 注册时间 begin **/
	private String memRegTimeBegin;
	/** 注册时间 end **/
	private String memRegTimeEnd;
	/** 上次登录时间 begin **/
	private String lastLoginTimeBegin;
	/** 上次登录时间 end **/
	private String lastLoginTimeEnd;
	/**是否分页  1：是  0:否	*/
	private String flg;  

	public String getMemLoginName() {
		return memLoginName;
	}

	public void setMemLoginName(String memLoginName) throws SystemException {
		this.memLoginName = DESC.encryption(memLoginName);
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) throws SystemException {
		this.memPhone = DESC.encryption(memPhone);
	}

	public String getMemParentIdPhone() {
		return memParentIdPhone;
	}

	public void setMemParentIdPhone(String memParentIdPhone) throws SystemException {
		this.memParentIdPhone = DESC.encryption(memParentIdPhone);
	}

	public String getMemSignSource() {
		return memSignSource;
	}

	public void setMemSignSource(String memSignSource) {
		this.memSignSource = memSignSource;
	}

	public String getMemRegTimeBegin() {
		return memRegTimeBegin;
	}

	public void setMemRegTimeBegin(String memRegTimeBegin) {
		this.memRegTimeBegin = memRegTimeBegin;
	}

	public String getMemRegTimeEnd() {
		return memRegTimeEnd;
	}

	public void setMemRegTimeEnd(String memRegTimeEnd) {
		this.memRegTimeEnd = memRegTimeEnd;
	}

	public String getLastLoginTimeBegin() {
		return lastLoginTimeBegin;
	}

	public void setLastLoginTimeBegin(String lastLoginTimeBegin) {
		this.lastLoginTimeBegin = lastLoginTimeBegin;
	}

	public String getLastLoginTimeEnd() {
		return lastLoginTimeEnd;
	}

	public void setLastLoginTimeEnd(String lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

}
