package com.meiduimall.model;

import java.io.Serializable;
import java.util.Date;

import com.meiduimall.util.DESC;
import com.meiduimall.util.StringUtil;

/**
 * 
 * 会员基础表(加密)
 * 
 **/
public class MemberSet implements Serializable {

	private static final long serialVersionUID = 4550764542554779921L;

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
		return memTotalProfit;
	}

	public void setMemTotalProfit(String memTotalProfit) {
		this.memTotalProfit = DESC.encryption(memTotalProfit==null?"0":memTotalProfit,memId);
	}

	public String getMemTotalConsumeProfit() {
		return memTotalConsumeProfit;
	}

	public void setMemTotalConsumeProfit(String memTotalConsumeProfit) {
		this.memTotalConsumeProfit = DESC.encryption(memTotalConsumeProfit==null?"0":memTotalConsumeProfit,memId);
	}
	
	public String getMemCancelorder() {
		return memCancelorder;
	}

	public void setMemCancelorder(String memCancelorder) {
		this.memCancelorder =  DESC.encryption(memCancelorder==null?"0":memCancelorder,memId);
	}

	public Date getMemLoginTime() {
		return memLoginTime;
	}

	public Date changePhoneDate() {
		return changePhoneDate;
	}
	
	public java.util.Date getChangePhoneDate() {
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
		return memIntegralTradeOriginal;
	}

	public void setMemIntegralTradeOriginal(String memIntegralTradeOriginal) {
		this.memIntegralTradeOriginal =  DESC.encryption(memIntegralTradeOriginal==null?"0":memIntegralTradeOriginal,memId);
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
		return memIntegralConsumeProfit;
	}

	public void setMemIntegralConsumeProfit(String memIntegralConsumeProfit) {
		this.memIntegralConsumeProfit = DESC.encryption(memIntegralConsumeProfit==null?"0":memIntegralConsumeProfit,this.memId);
	}

	public String getMemIntegralFrozen() {
		return memIntegralFrozen;
	}

	public void setMemIntegralFrozen(String memIntegralFrozen) {
		this.memIntegralFrozen = DESC.encryption(memIntegralFrozen,this.memId);
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
		return memIntegralConsumeCoupon;
	}

	public void setMemIntegralConsumeCoupon(String memIntegralConsumeCoupon) {
		this.memIntegralConsumeCoupon = DESC.encryption(memIntegralConsumeCoupon==null?"0":memIntegralConsumeCoupon,this.memId);
	}

	public String getMemBasicAccountTotalQuantity() {
		return memBasicAccountTotalQuantity;
	}

	public void setMemBasicAccountTotalQuantity(String memBasicAccountTotalQuantity) {
		this.memBasicAccountTotalQuantity = DESC.encryption(memBasicAccountTotalQuantity==null?"0":memBasicAccountTotalQuantity, this.memId);
	}

	public String getMemPresentLevel() {
		return memPresentLevel;
	}

	public void setMemPresentLevel(String memPresentLevel) {
		this.memPresentLevel = memPresentLevel;
	}

	public String getMemIntegralInitResults() {
		return memIntegralInitResults;
	}

	public String getMemIntegralIncreatedResults() {
		return memIntegralIncreatedResults;
	}

	public void setMemIntegralInitResults(String memIntegralInitResults) {
		this.memIntegralInitResults = DESC.encryption(memIntegralInitResults,this.memId);
	}

	public void setMemIntegralIncreatedResults(String memIntegralIncreatedResults) {
		this.memIntegralIncreatedResults = DESC.encryption(memIntegralIncreatedResults,this.memId);
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
		this.memName = DESC.encryption(memName);
	}
	
	public String getMemLevelDictId() {
		return this.memLevelDictId;
	}

	public void setMemLevelDictId(String memLevelDictId) {
		this.memLevelDictId = memLevelDictId;
	}
	
	public Date getPfLastLoginTime() {
		return pfLastLoginTime;
	}

	public void setPfLastLoginTime(java.util.Date pfLastLoginTime) {
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
		return this.memBasicAccountTotalQuantity;
	}

	public void setMemBasicAccountTotal(String memBasicAccountTotalQuantity) {
		this.memBasicAccountTotalQuantity = DESC.encryption(memBasicAccountTotalQuantity, this.memId);
	}

	public String getMemBasicAccountStatus() {
		return memBasicAccountStatus;
	}

	public void setMemBasicAccountStatus(String memBasicAccountStatus) {
		this.memBasicAccountStatus = memBasicAccountStatus;
	}

	public String getMemIntegralConsumeOrignal() {
		return memIntegralConsumeOrignal;
	}
	public void setMemIntegralConsumeOrignal(String memIntegralConsumeOrignal) {
		this.memIntegralConsumeOrignal = DESC.encryption(memIntegralConsumeOrignal==null?"0":memIntegralConsumeOrignal,this.memId);
	}

	public String getMemIntegralTradeProfit() {
		return memIntegralTradeProfit;
	}

	public void setMemIntegralTradeProfit(String memIntegralTradeProfit) {
		this.memIntegralTradeProfit = DESC.encryption(memIntegralTradeProfit==null?"0":memIntegralTradeProfit,this.memId);
	}

	public String getMemIntegralCurrentResults() {
		return memIntegralCurrentResults;
	}

	public void setMemIntegralCurrentResults(String memIntegralCurrentResults) {
		this.memIntegralCurrentResults = DESC.encryption(memIntegralCurrentResults==null?"0":memIntegralCurrentResults,this.memId);
	}

	public String getMemIntegralCurrentValidTradeIntegral() {
		return memIntegralCurrentValidTradeIntegral;
	}

	public void setMemIntegralCurrentValidTradeIntegral(String memIntegralCurrentValidTradeIntegral) {
		this.memIntegralCurrentValidTradeIntegral = DESC.encryption(memIntegralCurrentValidTradeIntegral==null?"0":memIntegralCurrentValidTradeIntegral,this.memId);
	}

	public String getMemIntegralCurrentTradedIntegral() {
		return memIntegralCurrentTradedIntegral;
	}

	public void setMemIntegralCurrentTradedIntegral(String memIntegralCurrentTradedIntegral) {
		this.memIntegralCurrentTradedIntegral = DESC.encryption(memIntegralCurrentTradedIntegral==null?"0":memIntegralCurrentTradedIntegral,this.memId);
	}

	public String getMemIntegralNextVaidTradeIntegral() {
		return memIntegralNextVaidTradeIntegral;
	}

	public void setMemIntegralNextVaidTradeIntegral(String memIntegralNextVaidTradeIntegral) {
		this.memIntegralNextVaidTradeIntegral = DESC.encryption(memIntegralNextVaidTradeIntegral,this.memId);
	}

	public String getMemIntegralFirstReturnIntegral() {
		return memIntegralFirstReturnIntegral;
	}

	public void setMemIntegralFirstReturnIntegral(String memIntegralFirstReturnIntegral) {
		this.memIntegralFirstReturnIntegral = DESC.encryption(memIntegralFirstReturnIntegral==null?"0":memIntegralFirstReturnIntegral,this.memId);
	}

	public String getMemIntegralRetrunedIntegral() {
		return memIntegralRetrunedIntegral;
	}

	public void setMemIntegralRetrunedIntegral(String memIntegralRetrunedIntegral) {
		this.memIntegralRetrunedIntegral = DESC.encryption(memIntegralRetrunedIntegral==null?"0":memIntegralRetrunedIntegral,this.memId);
	}

	public String getMemIntegralSurplusIntegral() {
		return memIntegralSurplusIntegral;
	}

	public void setMemIntegralSurplusIntegral(String memIntegralSurplusIntegral) {
		this.memIntegralSurplusIntegral = DESC.encryption(memIntegralSurplusIntegral==null?"0":memIntegralSurplusIntegral,this.memId);
	}

	public String getMemIntegralCapitalIncrease() {
		return memIntegralCapitalIncrease;
	}

	public void setMemIntegralCapitalIncrease(String memIntegralCapitalIncrease) {
		this.memIntegralCapitalIncrease = DESC.encryption(memIntegralCapitalIncrease==null?"0":memIntegralCapitalIncrease,this.memId);
	}

	public String getMemIntegralStatus() {
		return memIntegralStatus;
	}

	public void setMemIntegralStatus(String memIntegralStatus) {
		this.memIntegralStatus = DESC.encryption(memIntegralStatus,this.memId);
	}

	public String getMemIntegralConsumeReturnedALL() {
		return memIntegralConsumeReturnedALL;
	}

	public void setMemIntegralConsumeReturnedALL(String memIntegralConsumeReturnedALL) {
		this.memIntegralConsumeReturnedALL = DESC.encryption(memIntegralConsumeReturnedALL,this.memId);
	}

	public String getMemIntegralConsumeReturned() {
		return memIntegralConsumeReturned;
	}

	public void setMemIntegralConsumeReturned(String memIntegralConsumeReturned) {
		this.memIntegralConsumeReturned = DESC.encryption(memIntegralConsumeReturned==null?"0":memIntegralConsumeReturned,this.memId);
	}

	public String getMemIntegralConsumeWillReturn() {
		return memIntegralConsumeWillReturn;
	}

	public void setMemIntegralConsumeWillReturn(String memIntegralConsumeWillReturn) {
		this.memIntegralConsumeWillReturn = DESC.encryption(memIntegralConsumeWillReturn,this.memId);
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMemOldEmail(String memOldEmail) {
		this.memOldEmail = DESC.encryption(memOldEmail);
	}

	public String getMemOldEmail() {
		return this.memOldEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = DESC.encryption(memEmail);
	}

	public String getMemEmail() {
		return this.memEmail;
	}

	public void setMemLoginName(String memLoginName) {
		this.memLoginName = DESC.encryption(memLoginName);
	}

	public String getMemLoginName() {
		return this.memLoginName;
	}

	public void setMemOldPhone(String memOldPhone) {
		this.memOldPhone = DESC.encryption(memOldPhone);
	}

	public String getMemOldPhone() {
		return this.memOldPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = DESC.encryption(memPhone);
	}

	public String getMemPhone() {
		return this.memPhone;
	}

	public void setMemNickName(String memNickName) {
		this.memNickName = DESC.encryption(memNickName);
	}

	public String getMemNickName() {
		return this.memNickName;
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
		this.memPic = DESC.encryption(memPic,this.memId);
	}

	public String getMemPic() {
		return this.memPic;
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
		return StringUtil.isEmptyByString(memLockCount)?"0":memLockCount;
	}

	public void setMemLockCount(String memLockCount) {
		this.memLockCount =  DESC.encryption((memLockCount==null?"0":memLockCount),memId);
	}

	public String getMemFirstPreOrderRank() {
		return memFirstPreOrderRank;
	}

	public void setMemFirstPreOrderRank(String memFirstPreOrderRank) {
		this.memFirstPreOrderRank = memFirstPreOrderRank;
	}
	
	public String getMemLoginNameIsdefaultIschanged() {
		return memLoginNameIsdefaultIschanged;
	}

	public void setMemLoginNameIsdefaultIschanged(String memLoginNameIsdefaultIschanged) {
		this.memLoginNameIsdefaultIschanged = DESC.encryption(memLoginNameIsdefaultIschanged,memId);
	}

	public String getMemParentIsdefaultIschanged() {
		return memParentIsdefaultIschanged;
	}

	public void setMemParentIsdefaultIschanged(String memParentIsdefaultIschanged) {
		this.memParentIsdefaultIschanged = DESC.encryption(memParentIsdefaultIschanged,memId);
	}
}