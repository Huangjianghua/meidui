package com.meiduimall.service.account.constant;

import com.meiduimall.core.BaseApiCode;

/**
 * API返回状态编码和编码语义
 * @author chencong
 *
 */
public abstract class ConstApiStatus extends BaseApiCode {
	
		public final static Integer MD5_EXCEPTION= 1003;
		public final static Integer DECRYPTION_EXCEPTION= 1004;
		public final static Integer ENCRYPTION_EXCEPTION= 1005;
		public final static Integer HTTP_EXCEPTION= 1006;
		public final static Integer SUCCESS = 0;
		public final static String SUCCESS_C = "Success"; 
		public final static String SUCCESS_M = "操作成功"; 
		/**服务处理异常   */
		public final static Integer SERVER_DEAL_WITH_EXCEPTION= 3005;

		/**操作数据库程序异常*/
		public final static Integer OPERATION_DB_EX= 9001;
		public final static String OPERATION_DB_EX_C = "操作数据库程序异常";
		/**必填参数为空*/
		public final static Integer REQUIRED_PARAM_EMPTY= 9002;
		public final static String  REQUIRED_PARAM_EMPTY_C= "必填参数为空";
		
		
		/**订单*/
		public final static Integer POINTS_BIGGERTHAN_MONEY=7001;
		public final static Integer MIX_PAYTYPE_ERROR=7002;
		public final static Integer PAYTYPE_ERROR=7003;
		public final static Integer REPEAT_ORDER=7004;
		public final static Integer NOT_ENOUGH_POINTS=7005;
		public final static Integer REPEAT_FREEZ_ORDER=7006;
		public final static Integer DJ_NOT_EQUALS_DJ=7007;
		public final static Integer NO_DJ_POINTS=7008;
		public final static Integer ORDER_STATUS_ERROR=7009;
		
		/**支付密码*/
		public final static Integer SET_PAYPWD_EXCEPTION=7010;
		public final static Integer SET_PAYPWD_STATUS_EXCEPTION=7011;
		public final static Integer PAYPWD_NOT_EXIST=7012;
		public final static Integer VALIDATE_PAYPWD_EXCEPTION=7013;
		public final static Integer PAYPWD_NOT_RIGHT=7014;
		public final static Integer UPDATE_PAYPWD_EXCEPTION=7015;
		public final static Integer RETRIEVE_PAYPWD_EXCEPTION=7016;
		public final static Integer OLD_PAYPWD_NOT_RIGHT=7017;
		
		public static final Integer USER_NOT_EXIST = 7018;
		public static final Integer BANK_INFO_ALREADY_EXIST = 7019;
		public static final Integer GET_MEMBER_BASIC_INFO_FAILED = 7020;
		public static final Integer VALIDATE_CODE_NOT_PASS = 7021;
		
		
		/********************* 会员列表相关                      ****************/
		/**查询会员为空*/
		public final static Integer ACCOUNT_IS_NULL_ERROR=7101;
		/** 查询会员调整余额明细异常 */
		public final static Integer ACCOUNT_REVISE_IS_NULL_ERROR=7102;
		/**调减金额不能大于当前金额 */
		public final static Integer ACCOUNT_REVISE_BALANCE_ERROR=7103;
		/**修改调整余额操作错误 */
		public final static Integer UPDATE_ACCOUNT_REVISE_BALANCE_ERROR=7104;
		/** 写入账户变动明细出现错误*/
		public final static Integer INSERT_ACCOUNT_DETAIL_ERROR=7106;
		/** 修改提现记录操作出现错误  */
		public final static Integer UPDATE_BANK_WITHDRAW_DEPOSIT_ERROR=7107;
		/** 根据ID 查看提现记录错误  */
		public final static Integer QUERY_BANK_WITHDRAW_BY_ID_ERROR=7108;
		/** 查看提现记录明细错误  */
		public final static Integer QUERY_BANK_WITHDRAW__DETAIL_BY_ID_ERROR=7109;
		/** 结算操作处理用户账号余额错误  */
		public final static Integer DEALWLTH_ACCOUNT_MONEY_ERROR=7110;
		
		/** 当前会员银行卡账户信息不存在  */
		public final static Integer ACCOUNT_BANK_CARD_IS_NULL=7111;
		/** 超过最大可提现限制50000  */
		public final static Integer ACCOUNT_APPLY_CARRY_CASH_ERROR=7112;
		/** 余额不足无法支付  */
		public final static Integer ACCOUNT_INSUFFICIENT_BALANCE_ERROR=7113;
		/** 余额低于最低提现金额  */
		public final static Integer ACCOUNT_BALANCE_BELOW_WITHDRAW_ERROR=7113;
		/** 实际提现金额不能大于申请提现金额 */
		public final static Integer WITHDRAW_ERROR=7114;
		/** 实际提现金额 大于提现手续费 */
		public final static Integer ACTUAL_WITHDRAW_MONEY_THEN_ZERO_ERROR=7115;
		/**修改会员账户余额出现错误 */
		public final static Integer UPDATE_ACCOUNT_BALANCE_ERROR=7116;
		
		/**提现参数不能为null  */
		public final static Integer PARAMETER_IS_NULL=7117;
		/**提现参数memId不能为null  */
		public final static Integer PARAMETER_MEMID_IS_NULL=7118;
		/**提现参数accountNo不能为null  */
		public final static Integer PARAMETER_ACCOUNTNO_IS_NULL=7119;
		/**提现参数applyCarryCash不能为null  */
		public final static Integer PARAMETER_APPLYCARRYCASH_IS_NULL=7120;
		
		/**新增提现记录异常  */
		public final static Integer INSERT_WITHDRAW_ERROR=7121;
		/**冻结余额变动失败 */
		public final static Integer FROZEN_BALANCE_FAILED_ERROR=7122;
		
		/**查询会员列表出现错误 */
		public final static Integer QUERY_MEMBER_LIST_ERROR=7123;
		/**添加调整余额操作出现错误 */
		public final static Integer INSERT_MEMBER_REVISE_DETAIL_ERROR=7124;
		/**添加账号冻结明细错误 */
		public final static Integer INSERT_MEMBER_FREEZE_DETAIL_ERROR=7125;
		/**查询钱包类型异常**/ 
		public final static Integer QUERY_WALLETTYPE_EXCEPTION=7126;
		/**查询转账积分列表错误			*/
		public final static Integer QUERY_TRANSFER_POINTS_ERROR=7127;
		
		static {
			zhMsgMap.put(OPERATION_DB_EX, "操作数据库程序异常");
			zhMsgMap.put(REQUIRED_PARAM_EMPTY, "必填参数为空");
			zhMsgMap.put(MD5_EXCEPTION, "生成MD5程序异常");
			zhMsgMap.put(DECRYPTION_EXCEPTION, "解密程序异常");
			zhMsgMap.put(ENCRYPTION_EXCEPTION, "加密程序异常");
			zhMsgMap.put(HTTP_EXCEPTION, "HTTP请求异常");
			zhMsgMap.put(SERVER_DEAL_WITH_EXCEPTION, "服务处理异常");
			zhMsgMap.put(POINTS_BIGGERTHAN_MONEY, "消费积分不能大于消费金额");
			zhMsgMap.put(MIX_PAYTYPE_ERROR, "混合支付支付模式，美兑积分不能为小于或等于0");
			zhMsgMap.put(PAYTYPE_ERROR, "支付类型错误");
			zhMsgMap.put(REPEAT_ORDER, "重复提交的订单");
			zhMsgMap.put(NOT_ENOUGH_POINTS, "积分余额不足");
			zhMsgMap.put(REPEAT_FREEZ_ORDER, "重复提交的冻结或解冻订单");
			zhMsgMap.put(DJ_NOT_EQUALS_DJ, "订单解冻积分不等于冻结积分");
			zhMsgMap.put(NO_DJ_POINTS, "没有冻结的积分");
			zhMsgMap.put(ORDER_STATUS_ERROR, "订单状态错误");
			
			zhMsgMap.put(PAYPWD_NOT_EXIST, "支付密码未设置");
			zhMsgMap.put(PAYPWD_NOT_RIGHT, "支付密码校验不通过");
			zhMsgMap.put(SET_PAYPWD_EXCEPTION, "设置支付密码程序异常");
			zhMsgMap.put(SET_PAYPWD_STATUS_EXCEPTION, "设置支付密码开关程序异常");
			zhMsgMap.put(VALIDATE_PAYPWD_EXCEPTION, "验证支付密码程序异常");
			zhMsgMap.put(UPDATE_PAYPWD_EXCEPTION, "修改支付密码失败，请联系客服");
			zhMsgMap.put(RETRIEVE_PAYPWD_EXCEPTION, "找回支付密码失败，请联系客服");
			zhMsgMap.put(OLD_PAYPWD_NOT_RIGHT, "旧支付密码验证不通过，请联系客服");
			zhMsgMap.put(VALIDATE_CODE_NOT_PASS, "短信验证码校验不通过");
			
			zhMsgMap.put(USER_NOT_EXIST, "当前用户在会员系统不存在");
			zhMsgMap.put(BANK_INFO_ALREADY_EXIST, "当前会员银行卡账户信息已存在");
			zhMsgMap.put(GET_MEMBER_BASIC_INFO_FAILED, "获取会员基本信息失败");
			
			zhMsgMap.put(ACCOUNT_IS_NULL_ERROR, "查询会员账号错误");
			zhMsgMap.put(ACCOUNT_REVISE_IS_NULL_ERROR, "查询会员调整余额明细错误");
			zhMsgMap.put(ACCOUNT_REVISE_BALANCE_ERROR, "调减金额不能大于当前金额");
			zhMsgMap.put(UPDATE_ACCOUNT_REVISE_BALANCE_ERROR, "修改调整余额操作错误");
			zhMsgMap.put(UPDATE_BANK_WITHDRAW_DEPOSIT_ERROR, "修改提现记录错误 ");
			
			zhMsgMap.put(QUERY_BANK_WITHDRAW_BY_ID_ERROR, "查看提现记录错误");
			zhMsgMap.put(QUERY_BANK_WITHDRAW__DETAIL_BY_ID_ERROR, "查看提现记录明细错误 ");
			zhMsgMap.put(UPDATE_PAYPWD_EXCEPTION, "更新支付密码失败，请联系客服");
        	zhMsgMap.put(DEALWLTH_ACCOUNT_MONEY_ERROR, "结算处理用户账号余额错误 ");
			zhMsgMap.put(ACCOUNT_BANK_CARD_IS_NULL, "当前会员银行卡账户信息不存在  ");
			zhMsgMap.put(ACCOUNT_APPLY_CARRY_CASH_ERROR, "超过最大可提现限制50000");
			zhMsgMap.put(ACCOUNT_INSUFFICIENT_BALANCE_ERROR, "余额不足无法支付");
			zhMsgMap.put(WITHDRAW_ERROR, "实际提现金额不能大于申请提现金额");
			zhMsgMap.put(ACTUAL_WITHDRAW_MONEY_THEN_ZERO_ERROR, "实际提现金额必须大于提现手续费");
			
			zhMsgMap.put(PARAMETER_IS_NULL, "请检查参数是否为空");
			zhMsgMap.put(PARAMETER_MEMID_IS_NULL, "memId参数不能为空");
			zhMsgMap.put(PARAMETER_ACCOUNTNO_IS_NULL, "accountNo参数不能为空");
			zhMsgMap.put(PARAMETER_APPLYCARRYCASH_IS_NULL, "applyCarryCash参数不能为空");
			zhMsgMap.put(INSERT_WITHDRAW_ERROR, "新增提现记录异常");
			zhMsgMap.put(FROZEN_BALANCE_FAILED_ERROR, "冻结余额变动失败");
			zhMsgMap.put(SERVER_DEAL_WITH_EXCEPTION, "服务器处理异常!");
			zhMsgMap.put(INSERT_MEMBER_FREEZE_DETAIL_ERROR, "添加账号冻结明细错误");
			zhMsgMap.put(QUERY_WALLETTYPE_EXCEPTION, "查询钱包类型异常");
			zhMsgMap.put(QUERY_TRANSFER_POINTS_ERROR, "查询转账积分列表错误");
			
		}
}
