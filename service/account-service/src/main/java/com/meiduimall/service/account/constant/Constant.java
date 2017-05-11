/**
 * 
 */
package com.meiduimall.service.account.constant;

/**
 * @author:   jianhua.huang 
 * @version:  2017年4月27日 下午5:02:24 0.1 
 * Description: 基本常量
 */
public class Constant {
	/**
	 * 会员余额 审核相关
	 */
	/** 审核操作  agree同意 */
	public final static String OPERATE_AGREE="agree";
	/** 审核操作  reject拒绝  */
	public final static String OPERATE_REJECT="reject";
	
	/** 提现客服审核操作  */
	public final static String CUSTOMER_OPERATE="customer";
	
	/** 提现客服审核操作说明  */
	public final static String  CUSTOMER_OPERATE_DESCRIPTION="客服审核驳回";
	/** 提现财务审核操作说明  */
	public final static String  FINANCE_OPERATE_DESCRIPTION="财务审核驳回";
	
	/** 账号余额明细  */
	public final static String ACCOUNT_BALANCE_DETAIL_REMARK="余额提现";
	/**	账号提现手续费		*/
	public final static String ACCOUNT_FEE_DETAIL_REMARK="提现手续费";
	
	public final static String IS_Y="Y";
	
	public final static String IS_N="N";
	
	/**手续费比例 0.01 */
	public final static Double FEESCALE=0.01;
	
	/**0 */
	public final static Double ZERO=0.0;
	
	/**5万 */
	public final static Double FIFTY_THOUSAND=50000.0;
	
	/** 1  */
	public final static String ONE="1";
	/** -1  */
	public final static String NEGATIVE_NUMBER_ONE="-1";
	/** 调整类型 */
	public final static String TRADETYPE="CWTZ";
}
