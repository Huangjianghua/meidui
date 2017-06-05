package com.meiduimall.service.account.constant;

/**
 * 其他系统参数名称定义
 * @author chencong
 *
 */
public class ConstSysParamsDefination {
	
	/** 默认分享人名称 */
	public static final String MD1GW_DEFAULT_SHARE_LOGIN_NAME = "meidui";
	
	/** 新用户注册增加积分 */
	public static final String MD1GW_REGISTER_ADD_POINTS = "100";
	
	/** 账户类型-积分账户：AT01 */
	public static final String ACCOUNT_TYPE_POINTS = "AT01";
	/** 账户类型-现金账户：AT02 */
	public static final String ACCOUNT_TYPE_MONEY = "AT02";
	
	/**加密解密的key*/
	public static final String DESC_KEY="DESC_KEY";
	/**加密方式*/
	public static final String DES="DES";
	public static final String MD5="MD5";
	/**编码方式*/
	public static final String GBK="GBK";
	
	/**短信服务给会员分配的sysKey*/
	public static final String SMS_SYSKEY="member_service";
	
	/**账户状态 正常*/
	public static final String MEMBER_STATUS_OK="21B260C3-A230-4601-8D62-420OPT20AT44";
	/**禁用*/
	public static final String MEMBER_STATUS_NO="21B260C3-A230-4601-8D62-420OPT20AT45";
	
	/** 资金增减：调账的方向,“IN”：调增，“OUT”：调减 */
	public static final String CAPITAL_IN = "IN"; 
	/** 资金增减：调账的方向,“IN”：调增，“OUT”：调减 */
	public static final String CAPITAL_OUT = "OUT"; 
	
	/** change_type 变更类型：1：修改 */
	public static final String CHANGE_TYPE_UPDATE = "1";
	/** change_type 变更类型：2：删除 */
	public static final String CHANGE_TYPE_DELETE = "2";
	
	/** 审核操作  agree同意 */
	public final static String OPERATE_AGREE="agree";
	/** 审核操作  reject拒绝  */
	public final static String OPERATE_REJECT="reject";
	
	/** 调整类型 */
	public final static String TRADETYPE="CWTZ";
	
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
	
	/**5万 */
	public final static Double FIFTY_THOUSAND=50000.0;
	
	/** 最低可提现金额 */
	public static final Double MIN_APPLY_CARRY_CASH = new Double("2");
	/** 最低提现手续费 */
	public static final Double MIN_APPLY_CARRY_FEE = new Double("2");
	
    public final static String IS_Y="Y";
	
	public final static String IS_N="N";
	
	/**手续费比例 0.01 */
	public final static Double FEESCALE=0.01;
	/**冻结类型InOrOut  	1表示冻结*/
	public final static Integer FREEZE=1;
	/**冻结类型InOrOut  	-1表示解冻*/
	public final static Integer THAW=-1;
	/**	表示系统用户			*/
	public final static String SYSTEM_USER="system";

}
