package com.meiduimall.model;

import java.io.Serializable;
import java.util.Date;

import com.meiduimall.util.DESC;
import com.meiduimall.util.StringUtil;

/**
 * 
 * 会员基础表(解密)
 * 
 **/
public class MemberGet implements Serializable {

	private static final long serialVersionUID = 5700495965716385250L;

	/** 会员系统ID **/
	private String memId;
	
	private String memGroupLevel;

	//消费卷
	private String memIntegralConsumeCoupon;
	
	private String memName;
	
	/**账户积分余额*/
	private String memintegralBalance;

	private String memLevelDictId;

	/** 会员上次注册邮箱 **/
	private String memOldEmail;

	/** 会员最新邮箱 **/
	private String memEmail;

	/** 会员登录名 **/
	private String memLoginName;

	/** 会员上一次绑定手机号 **/
	private String memOldPhone;

	/** 会员最新手机号 **/
	private String memPhone;

	/** 会员昵称 **/
	private String memNickName;

	/** 会员登录密码 **/
	private String memLoginPwd;

	/** 会员支付密码 **/
	private String memPayPwd;

	/** 会员推荐人ID **/
	private String memParentId;

	/** 会员状态 **/
	private String dictMemStatus;

	/** 会员是否所有绑定 **/
	private  Boolean memIsAllActivated;

	/** 会员创建时间 **/
	private java.util.Date memCreatedDate;
	
	/** 会员本次登录时间 **/
	private java.util.Date memLoginTime;

	/** 会员更新时间 **/
	private java.util.Date memUpdatedDate;

	/** 会员性别 **/
	private String memSex;

	/** 会员生日 **/
	private java.util.Date memBirthday;

	/** 会员自定义头像,主要给APP或PC应用 **/
	private String memPic;

	/** 会员当前详细住址 **/
	private String memCurrentAddress;

	/** 注册年份 **/
	private Integer memRegYear;

	/** 会员注册月份 **/
	private Integer memRegMonth;

	/** 会员注册天 **/
	private Integer memRegDay;

	/** 会员更新人ID **/
	private String memUpdatedBy;

	/** 会员创建人ID **/
	private String memCreatedBy;

	/** 会员创建种类。1为自己创建。2位平台创建。 **/
	private Integer memCreatedCategory;

	/** 父节点ID串1 **/
	private String memParentValue1;

	/** 父节点ID串2 **/
	private String memParentValue2;

	/** 父节点ID串3 **/
	private String memParentValue3;

	/** License Key **/
	private String licenseKey;

	/** 基本账号总额 **/
	private String memBasicAccountTotalQuantity;

	/** 基本账号状态ID **/
	private String memBasicAccountStatus;

	/** 消费返本 **/
	private String memIntegralConsumeOrignal;

	/** 批发返利 **/
	private String memIntegralTradeProfit;

	/** 当前业绩 **/
	private String memIntegralCurrentResults;

	/** 当前可批发积分 **/
	private String memIntegralCurrentValidTradeIntegral;

	/** 已批发积分 **/
	private String memIntegralCurrentTradedIntegral;

	/** 下期可批发积分 **/
	private String memIntegralNextVaidTradeIntegral;

	/** 履约返还积分 **/
	private String memIntegralFirstReturnIntegral;

	/** 已返还积分 **/
	private String memIntegralRetrunedIntegral;

	/** 剩余返还积分 **/
	private String memIntegralSurplusIntegral;

	/** 挖矿金额 **/
	private String memIntegralCapitalIncrease;

	/** 积分状态 **/
	private String memIntegralStatus;

	/** 消费已返回全部 **/
	private String memIntegralConsumeReturnedALL;

	/** 通过消费返本 **/
	private String memIntegralConsumeReturned;

	/** 消费待返本 **/
	private String memIntegralConsumeWillReturn;

	/** 钱包地址 **/
	private String memWallentUrl;

	/** 上次登录时间 **/
	private java.util.Date pfLastLoginTime;
	/**
	 * 预购权
	 */
	private String memPreorderRight;
	
	/**
	 * 初始业绩
	 */
	private String memIntegralInitResults;
	
	/**
	 * 新增业绩
	 */
	private String memIntegralIncreatedResults;
	
	/**
	 * 当前级别
	 */
	private String memPresentLevel;
	
	
	/**
	 * 用户类型：1代表会员，2，代表经销商，3，代表公司运营
	 */
	private String memType;
	
	
	/**注册来源0表示PC端注册1表示o2o注册2表示会员结算系统数据迁移注册3表示壹购物注册4表示壹购物商城迁移*/
	private String memSignSource;
	
	/**
	 * 消费返利
	 */
	private String memIntegralConsumeProfit;
	
	/**
	 * 冻结积分
	 */
	private String memIntegralFrozen;
	
	/**
	 * 是否有预购资格1.为有资格,0为没有资格
	 */
	private String memIsPreorderRights;
	

	/**
	 * 是否开设店铺1为允许.0为为禁止
	 */
	private String memIsAllowShop;
	
	/** 会员账户被禁用/解禁的时间 **/
	private Date memBanDate;
	
	//批发返本
	private String memIntegralTradeOriginal;
	
	/**
	 * 是否已经全部消费返还
	 */
	private int memIsConsumeReturnAll;
	
	/** 修改手机号时间 **/
	private Date changePhoneDate;
	
	
	/**累计退单积分**/
	
	private String memCancelorder;
	
	//批发返利累计
	private String memTotalProfit;
	
	//消费返利累计
	private String memTotalConsumeProfit;
	
	//锁定次数
	private String memLockCount;
	
	//首次预购级别<只用于展示，不参与运算>
	private String memFirstPreOrderRank;
	
	//台湾会员注册地址
	private String memTwCurrentAddress;
	
	private String memLoginNameIsdefaultIschanged;
	
	private String memParentIsdefaultIschanged;
	
	
	public String getMemTwCurrentAddress() {
		return memTwCurrentAddress;
	}

	public void setMemTwCurrentAddress(String memTwCurrentAddress) {
		this.memTwCurrentAddress = memTwCurrentAddress;
	}
	
	public String getMemTotalProfit() {
		return memTotalProfit==null?"0":DESC.deyption(memTotalProfit,this.memId);
	}

	public void setMemTotalProfit(String memTotalProfit) {
		this.memTotalProfit = memTotalProfit;
	}

	public String getMemTotalConsumeProfit() {
		return memTotalConsumeProfit==null?"0":DESC.deyption(memTotalConsumeProfit,this.memId);
	}

	public void setMemTotalConsumeProfit(String memTotalConsumeProfit) {
		this.memTotalConsumeProfit = memTotalConsumeProfit;
	}
	
	public String getMemCancelorder() {
		return memCancelorder==null?"0":DESC.deyption(memCancelorder,this.memId);
	}

	public void setMemCancelorder(String memCancelorder) {
		this.memCancelorder =  memCancelorder;
	}

	public Date getMemLoginTime() {
		return memLoginTime;
	}

	public Date changePhoneDate() {
		return changePhoneDate;
	}
	
	public Date getChangePhoneDate() {
		return changePhoneDate;
	}

	public void setChangePhoneDate(java.util.Date changePhoneDate) {
		this.changePhoneDate = changePhoneDate;
	}

	public void setMemLoginTime(java.util.Date memLoginTime) {
		this.memLoginTime = memLoginTime;
	}

	public int getMemIsConsumeReturnAll() {
		return memIsConsumeReturnAll>0?memIsConsumeReturnAll:0;
	}

	public void setMemIsConsumeReturnAll(int memIsConsumeReturnAll) {
		this.memIsConsumeReturnAll = memIsConsumeReturnAll;
	}

	public String getMemIntegralTradeOriginal() {
		return memIntegralTradeOriginal==null?"0":DESC.deyption(memIntegralTradeOriginal,this.memId);
	}

	public void setMemIntegralTradeOriginal(String memIntegralTradeOriginal) {
		this.memIntegralTradeOriginal =  memIntegralTradeOriginal;
	}
	
	public String getMemGroupLevel() {
		return memGroupLevel;
	}

	public void setMemGroupLevel(String memGroupLevel) {
		this.memGroupLevel = memGroupLevel;
	}
	
	public String getMemintegralBalance() {
		return memintegralBalance;
	}

	public void setMemintegralBalance(String memintegralBalance) {
		this.memintegralBalance = memintegralBalance;
	}
	
	public java.util.Date getMemBanDate() {
		return memBanDate;
	}

	public java.util.Date memBandate() {
		return memBanDate;
	}

	public void setMemBanDate(java.util.Date memBanDate) {
		this.memBanDate = memBanDate;
	}
	
	
	public String getMemIntegralConsumeProfit() {
		return memIntegralConsumeProfit==null?"0":DESC.deyption(memIntegralConsumeProfit,this.memId);
	}

	public void setMemIntegralConsumeProfit(String memIntegralConsumeProfit) {
		this.memIntegralConsumeProfit = memIntegralConsumeProfit;
	}

	public String getMemIntegralFrozen() {
		return DESC.deyption(memIntegralFrozen,this.memId);
	}

	public void setMemIntegralFrozen(String memIntegralFrozen) {
		this.memIntegralFrozen = memIntegralFrozen;
	}

	public String getMemIsPreorderRights() {
		return memIsPreorderRights;
	}

	public void setMemIsPreorderRights(String memIsPreorderRights) {
		this.memIsPreorderRights = memIsPreorderRights;
	}

	public String getMemIsAllowShop() {
		return memIsAllowShop;
	}

	public void setMemIsAllowShop(String memIsAllowShop) {
		this.memIsAllowShop = memIsAllowShop;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public String getMemIntegralConsumeCoupon() {
		return memIntegralConsumeCoupon==null?"0":DESC.deyption(memIntegralConsumeCoupon,this.memId);
	}

	public void setMemIntegralConsumeCoupon(String memIntegralConsumeCoupon) {
		this.memIntegralConsumeCoupon = memIntegralConsumeCoupon;
	}

	public String getMemBasicAccountTotalQuantity() {
		return memBasicAccountTotalQuantity==null?"0":DESC.deyption(memBasicAccountTotalQuantity, this.memId);
	}

	public void setMemBasicAccountTotalQuantity(String memBasicAccountTotalQuantity) {
		this.memBasicAccountTotalQuantity = memBasicAccountTotalQuantity;
	}

	public String getMemPresentLevel() {
		return memPresentLevel==null?"0":memPresentLevel;
	}

	public void setMemPresentLevel(String memPresentLevel) {
		this.memPresentLevel = memPresentLevel;
	}

	public String getMemIntegralInitResults() {
		return DESC.deyption(memIntegralInitResults,this.memId);
	}

	public String getMemIntegralIncreatedResults() {
		return DESC.deyption(memIntegralIncreatedResults,this.memId);
	}

	public void setMemIntegralInitResults(String memIntegralInitResults) {
		this.memIntegralInitResults = memIntegralInitResults;
	}

	public void setMemIntegralIncreatedResults(String memIntegralIncreatedResults) {
		this.memIntegralIncreatedResults = memIntegralIncreatedResults;
	}

	public String getMemSignSource() {
		return memSignSource;
	}

	public void setMemSignSource(String memSignSource) {
		this.memSignSource = memSignSource;
	}

	public String getMemPreorderRight() {
		return memPreorderRight;
	}

	public void setMemPreorderRight(String memPreorderRight) {
		this.memPreorderRight = memPreorderRight;
	}

	public String getMemName() {
		return DESC.deyption(this.memName);
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	public String getMemLevelDictId() {
		return this.memLevelDictId;
	}

	public void setMemLevelDictId(String memLevelDictId) {
		this.memLevelDictId = memLevelDictId;
	}
	
	public java.util.Date getPfLastLoginTime() {
		return pfLastLoginTime;
	}

	public void setPfLastLoginTime(Date pfLastLoginTime) {
		this.pfLastLoginTime = pfLastLoginTime;
	}

	public String getMemWallentUrl() {
		return memWallentUrl;
	}

	public void setMemWallentUrl(String memWallentUrl) {
		this.memWallentUrl = memWallentUrl;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public String getMemBasicAccountTotal() {
		return DESC.deyption(this.memBasicAccountTotalQuantity,this.memId);
	}

	public void setMemBasicAccountTotal(String memBasicAccountTotalQuantity) {
		this.memBasicAccountTotalQuantity = memBasicAccountTotalQuantity;
	}

	public String getMemBasicAccountStatus() {
		return memBasicAccountStatus;
	}

	public void setMemBasicAccountStatus(String memBasicAccountStatus) {
		this.memBasicAccountStatus = memBasicAccountStatus;
	}

	public String getMemIntegralConsumeOrignal() {
		return memIntegralConsumeOrignal==null?"0":DESC.deyption(memIntegralConsumeOrignal,this.memId);
	}
	public void setMemIntegralConsumeOrignal(String memIntegralConsumeOrignal) {
		this.memIntegralConsumeOrignal = memIntegralConsumeOrignal;
	}

	public String getMemIntegralTradeProfit() {
		return memIntegralTradeProfit==null?"0":DESC.deyption(memIntegralTradeProfit,this.memId);
	}

	public void setMemIntegralTradeProfit(String memIntegralTradeProfit) {
		this.memIntegralTradeProfit = memIntegralTradeProfit;
	}

	public String getMemIntegralCurrentResults() {
		return memIntegralCurrentResults==null?"0":DESC.deyption(memIntegralCurrentResults,this.memId);
	}

	public void setMemIntegralCurrentResults(String memIntegralCurrentResults) {
		this.memIntegralCurrentResults = memIntegralCurrentResults;
	}

	public String getMemIntegralCurrentValidTradeIntegral() {
		return memIntegralCurrentValidTradeIntegral==null?"0":DESC.deyption(memIntegralCurrentValidTradeIntegral,this.memId);
	}

	public void setMemIntegralCurrentValidTradeIntegral(String memIntegralCurrentValidTradeIntegral) {
		this.memIntegralCurrentValidTradeIntegral = memIntegralCurrentValidTradeIntegral;
	}

	public String getMemIntegralCurrentTradedIntegral() {
		return memIntegralCurrentTradedIntegral==null?"0":DESC.deyption(memIntegralCurrentTradedIntegral,this.memId);
	}

	public void setMemIntegralCurrentTradedIntegral(String memIntegralCurrentTradedIntegral) {
		this.memIntegralCurrentTradedIntegral = memIntegralCurrentTradedIntegral;
	}

	public String getMemIntegralNextVaidTradeIntegral() {
		return DESC.deyption(memIntegralNextVaidTradeIntegral,this.memId);
	}

	public void setMemIntegralNextVaidTradeIntegral(String memIntegralNextVaidTradeIntegral) {
		this.memIntegralNextVaidTradeIntegral = memIntegralNextVaidTradeIntegral;
	}

	public String getMemIntegralFirstReturnIntegral() {
		return memIntegralFirstReturnIntegral==null?"0":DESC.deyption(memIntegralFirstReturnIntegral,this.memId);
	}

	public void setMemIntegralFirstReturnIntegral(String memIntegralFirstReturnIntegral) {
		this.memIntegralFirstReturnIntegral = memIntegralFirstReturnIntegral;
	}

	public String getMemIntegralRetrunedIntegral() {
		return memIntegralRetrunedIntegral==null?"0":DESC.deyption(memIntegralRetrunedIntegral,this.memId);
	}

	public void setMemIntegralRetrunedIntegral(String memIntegralRetrunedIntegral) {
		this.memIntegralRetrunedIntegral = memIntegralRetrunedIntegral;
	}

	public String getMemIntegralSurplusIntegral() {
		return memIntegralSurplusIntegral==null?"0":DESC.deyption(memIntegralSurplusIntegral,this.memId);
	}

	public void setMemIntegralSurplusIntegral(String memIntegralSurplusIntegral) {
		this.memIntegralSurplusIntegral = memIntegralSurplusIntegral;
	}

	public String getMemIntegralCapitalIncrease() {
		return memIntegralCapitalIncrease==null?"0":DESC.deyption(memIntegralCapitalIncrease,this.memId);
	}

	public void setMemIntegralCapitalIncrease(String memIntegralCapitalIncrease) {
		this.memIntegralCapitalIncrease = memIntegralCapitalIncrease;
	}

	public String getMemIntegralStatus() {
		return DESC.deyption(memIntegralStatus,this.memId);
	}

	public void setMemIntegralStatus(String memIntegralStatus) {
		this.memIntegralStatus = memIntegralStatus;
	}

	public String getMemIntegralConsumeReturnedALL() {
		return DESC.deyption(memIntegralConsumeReturnedALL,this.memId);
	}

	public void setMemIntegralConsumeReturnedALL(String memIntegralConsumeReturnedALL) {
		this.memIntegralConsumeReturnedALL = memIntegralConsumeReturnedALL;
	}

	public String getMemIntegralConsumeReturned() {
		return memIntegralConsumeReturned==null?"0":DESC.deyption(memIntegralConsumeReturned,this.memId);
	}

	public void setMemIntegralConsumeReturned(String memIntegralConsumeReturned) {
		this.memIntegralConsumeReturned = memIntegralConsumeReturned;
	}

	public String getMemIntegralConsumeWillReturn() {
		return DESC.deyption(memIntegralConsumeWillReturn,this.memId);
	}

	public void setMemIntegralConsumeWillReturn(String memIntegralConsumeWillReturn) {
		this.memIntegralConsumeWillReturn = memIntegralConsumeWillReturn;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMemOldEmail(String memOldEmail) {
		this.memOldEmail = memOldEmail;
	}

	public String getMemOldEmail() {
		return DESC.deyption(this.memOldEmail);
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemEmail() {
		return DESC.deyption(this.memEmail);
	}

	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}

	public String getMemLoginName() {
		return DESC.deyption(this.memLoginName);
	}

	public void setMemOldPhone(String memOldPhone) {
		this.memOldPhone = memOldPhone;
	}

	public String getMemOldPhone() {
		return DESC.deyption(this.memOldPhone);
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemPhone() {
		return DESC.deyption(this.memPhone);
	}

	public void setMemNickName(String memNickName) {
		this.memNickName = memNickName;
	}

	public String getMemNickName() {
		return DESC.deyption(this.memNickName);
	}

	public void setMemLoginPwd(String memLoginPwd) {
		this.memLoginPwd = memLoginPwd;
	}

	public String getMemLoginPwd() {
		return this.memLoginPwd;
	}

	public void setMemPayPwd(String memPayPwd) {
		this.memPayPwd = memPayPwd;
	}

	public String getMemPayPwd() {
		return this.memPayPwd;
	}

	public void setMemParentId(String memParentId) {
		this.memParentId = memParentId;
	}

	public String getMemParentId() {
		return this.memParentId;
	}

	public void setDictMemStatus(String dictMemStatus) {
		this.dictMemStatus = dictMemStatus;
	}

	public String getDictMemStatus() {
		return this.dictMemStatus;
	}

	public void setMemIsAllActivated(Boolean memIsAllActivated) {
		this.memIsAllActivated = memIsAllActivated;
	}

	public Boolean getMemIsAllActivated() {
		return this.memIsAllActivated;
	}

	public void setMemCreatedDate(java.util.Date memCreatedDate) {
		this.memCreatedDate = memCreatedDate;
	}

	public java.util.Date getMemCreatedDate() {
		return this.memCreatedDate;
	}

	public void setMemUpdatedDate(java.util.Date memUpdatedDate) {
		this.memUpdatedDate = memUpdatedDate;
	}

	public java.util.Date getMemUpdatedDate() {
		return this.memUpdatedDate;
	}

	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}

	public String getMemSex() {
		return this.memSex;
	}

	public void setMemBirthday(java.util.Date memBirthday) {
		this.memBirthday = memBirthday;
	}

	public java.util.Date getMemBirthday() {
		return this.memBirthday;
	}

	public void setMemPic(String memPic) {
		this.memPic = memPic;
	}

	public String getMemPic() {
		return DESC.deyption(this.memPic,this.memId);
	}

	public void setMemCurrentAddress(String memCurrentAddress) {
		this.memCurrentAddress = memCurrentAddress;
	}

	public String getMemCurrentAddress() {
		return this.memCurrentAddress;
	}

	public void setMemRegYear(Integer memRegYear) {
		this.memRegYear = memRegYear;
	}

	public Integer getMemRegYear() {
		return this.memRegYear;
	}

	public void setMemRegMonth(Integer memRegMonth) {
		this.memRegMonth = memRegMonth;
	}

	public Integer getMemRegMonth() {
		return this.memRegMonth;
	}

	public void setMemRegDay(Integer memRegDay) {
		this.memRegDay = memRegDay;
	}

	public Integer getMemRegDay() {
		return this.memRegDay;
	}

	public void setMemUpdatedBy(String memUpdatedBy) {
		this.memUpdatedBy = memUpdatedBy;
	}

	public String getMemUpdatedBy() {
		return this.memUpdatedBy;
	}

	public void setMemCreatedBy(String memCreatedBy) {
		this.memCreatedBy = memCreatedBy;
	}

	public String getMemCreatedBy() {
		return this.memCreatedBy;
	}

	public void setMemCreatedCategory(Integer memCreatedCategory) {
		this.memCreatedCategory = memCreatedCategory;
	}

	public Integer getMemCreatedCategory() {
		return this.memCreatedCategory;
	}

	public void setMemParentValue1(String memParentValue1) {
		this.memParentValue1 = memParentValue1;
	}

	public String getMemParentValue1() {
		return this.memParentValue1;
	}

	public void setMemParentValue2(String memParentValue2) {
		this.memParentValue2 = memParentValue2;
	}

	public String getMemParentValue2() {
		return this.memParentValue2;
	}

	public void setMemParentValue3(String memParentValue3) {
		this.memParentValue3 = memParentValue3;
	}

	public String getMemParentValue3() {
		return this.memParentValue3;
	}

	public void setMemLicenseKey(String licensekey) {
		this.licenseKey = licensekey;
	}

	public String getMemLicenseKey() {
		return this.licenseKey;
	}

	public String getMemLockCount() {
		return StringUtil.isEmptyByString(memLockCount)?"0":DESC.deyption(memLockCount,this.memId);
	}

	public void setMemLockCount(String memLockCount) {
		this.memLockCount = memLockCount;
	}

	public String getMemFirstPreOrderRank() {
		return memFirstPreOrderRank;
	}

	public void setMemFirstPreOrderRank(String memFirstPreOrderRank) {
		this.memFirstPreOrderRank = memFirstPreOrderRank;
	}
	
	public String getMemLoginNameIsdefaultIschanged() {
		return DESC.deyption(memLoginNameIsdefaultIschanged,memId);
	}

	public void setMemLoginNameIsdefaultIschanged(String memLoginNameIsdefaultIschanged) {
		this.memLoginNameIsdefaultIschanged = memLoginNameIsdefaultIschanged;
	}

	public String getMemParentIsdefaultIschanged() {
		return DESC.deyption(memParentIsdefaultIschanged,memId);
	}

	public void setMemParentIsdefaultIschanged(String memParentIsdefaultIschanged) {
		this.memParentIsdefaultIschanged = memParentIsdefaultIschanged;
	}
}