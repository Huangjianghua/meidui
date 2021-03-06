package com.meiduimall.service.account.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstRegisterSource;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;

/**
 * 会员管理-会员列表 model
 * @author:   jianhua.huang 
 * @version:  2017年5月5日 下午5:52:33 0.1 
 * Description:
 */
public class MSAccountList implements Serializable {

	private static final long serialVersionUID = 2328919110614394094L;
	private String memId;
	/** 用户账户 */
	private String memLoginName;
	/** 手机号 */
	private String memPhone;
	/** 推荐人手机 **/
	private String memParentIdPhone;
	/** 注册来源0表示PC端注册1表示o2o注册2表示会员结算系统数据迁移注册3表示壹购物注册4表示壹购物商城迁移 */
	private String memSignSource;
	/** 注册时间 **/
	private String memRegTime;
	/** 上次登录时间 **/
	private String pfLastLoginTime;
	/** 冻结积分 **/
	private Double mchFreezePointsCount;
	/** 积分余额 **/
	private String mchPointsBalanceCount;

	/** 余额 **/
	private Double mchBalanceCount;

	/** 冻结余额 **/
	private Double mchFreezeBalanceCount;
	private String status;

	public String getMemLoginName(){
		return memLoginName;
	}

	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}

	public String getMemPhone(){
		return this.memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemParentIdPhone() {
		return this.memParentIdPhone;
	}

	public void setMemParentIdPhone(String memParentIdPhone) {
		this.memParentIdPhone = memParentIdPhone;
	}

	public String getMemSignSource() {
		if(StringUtils.isNotBlank(this.memSignSource)){
			return ConstRegisterSource.getNameByCode(Integer.parseInt(this.memSignSource));
		}
		return this.memSignSource;
	}

	public void setMemSignSource(String memSignSource) {
		this.memSignSource = memSignSource;
	}

	public String getMemRegTime() {
		return memRegTime;
	}

	public void setMemRegTime(String memRegTime) {
		this.memRegTime = memRegTime;
	}

	public String getPfLastLoginTime() {
		return pfLastLoginTime;
	}

	public void setPfLastLoginTime(String pfLastLoginTime) {
		this.pfLastLoginTime = pfLastLoginTime;
	}

	public Double getMchFreezePointsCount() {
		return mchFreezePointsCount;
	}

	public void setMchFreezePointsCount(Double mchFreezePointsCount) {
		this.mchFreezePointsCount = mchFreezePointsCount;
	}

	public String getMchPointsBalanceCount() throws NumberFormatException, MdSysException {
		if(StringUtils.isNotBlank(mchPointsBalanceCount)&&mchFreezePointsCount!=null){
			Double totalPoint=Double.valueOf(DESC.deyption(mchPointsBalanceCount,this.memId));
			if(this.mchFreezePointsCount!=null&&this.mchFreezePointsCount>=0){
				totalPoint=DoubleCalculate.sub(totalPoint, mchFreezePointsCount);
				totalPoint=Math.abs(totalPoint);
			}
			if(this.mchFreezePointsCount!=null&&this.mchFreezePointsCount<0){
				totalPoint=DoubleCalculate.add(totalPoint, mchFreezePointsCount);
			}
			this.mchFreezePointsCount=Math.abs(mchFreezePointsCount);
			return  totalPoint.toString();
		}
		return DESC.deyption(mchPointsBalanceCount,this.memId);
	}

	public void setMchPointsBalanceCount(String mchPointsBalanceCount) {
		this.mchPointsBalanceCount = mchPointsBalanceCount;
	}

	public Double getMchBalanceCount() {
		return mchBalanceCount;
	}

	public void setMchBalanceCount(Double mchBalanceCount) {
		this.mchBalanceCount = mchBalanceCount;
	}

	public Double getMchFreezeBalanceCount() {
		return mchFreezeBalanceCount;
	}

	public void setMchFreezeBalanceCount(Double mchFreezeBalanceCount) {
		this.mchFreezeBalanceCount = mchFreezeBalanceCount;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getStatus() {
		if(ConstSysParamsDefination.MEMBER_STATUS_OK.equals(status)){
			status="正常";
		}else{
			status="禁用";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
