package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.util.PageHelp;

/**
 * 会员余额调整明细Model Request
 * @author:   jianhua.huang 
 * @version:  2017年5月5日 下午5:53:13 0.1 
 * Description:
 */
public class RequestAccountReviseDetail extends PageHelp implements Serializable {

	private static final long serialVersionUID = -7221481053458274360L;
	
	private String id;
	/**
	 * 会员编号
	 */
	private String memId;

	/** 用户账户 */
	private String memLoginName;
	/** 手机号 */
	private String memPhone;
	/**
	 * 钱包类型
	 */
	private String walletType;
	/**
	 * 调整类型(1-调增,2-调减)
	 */
	private String reviseType;

	/**
	 * 状态(WR-待审核,AR-已审核,RR-已拒绝)
	 */
	private String status;

	/** 操作开始时间 */
	private String createdDateBegin;
	/** 操作开始时间 */
	private String createdDateEnd;

	/** 审核开始时间 */
	private String updatedDateBegin;
	/** 审核结束时间 */
	private String updatedDateEnd;

	private String isDelete;
	/**是否分页  1：是  0:否	*/
	private String flg;  

	
	
	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemLoginName() {
		return memLoginName;
	}

	public void setMemLoginName(String memLoginName){
		this.memLoginName =memLoginName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) throws MdSysException {
		this.memPhone = memPhone;
	}

	public String getReviseType() {
		return reviseType;
	}

	public void setReviseType(String reviseType) {
		this.reviseType = reviseType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDateBegin() {
		return createdDateBegin;
	}

	public void setCreatedDateBegin(String createdDateBegin) {
		this.createdDateBegin = createdDateBegin;
	}

	public String getCreatedDateEnd() {
		return createdDateEnd;
	}

	public void setCreatedDateEnd(String createdDateEnd) {
		this.createdDateEnd = createdDateEnd;
	}

	public String getUpdatedDateBegin() {
		return updatedDateBegin;
	}

	public void setUpdatedDateBegin(String updatedDateBegin) {
		this.updatedDateBegin = updatedDateBegin;
	}

	public String getUpdatedDateEnd() {
		return updatedDateEnd;
	}

	public void setUpdatedDateEnd(String updatedDateEnd) {
		this.updatedDateEnd = updatedDateEnd;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

}
