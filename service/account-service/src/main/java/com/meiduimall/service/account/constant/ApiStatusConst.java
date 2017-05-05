package com.meiduimall.service.account.constant;

import com.meiduimall.core.BaseApiCode;

/**
 * API返回状态编码和编码语义
 * @author chencong
 *
 */
public abstract class ApiStatusConst extends BaseApiCode {
	
	    /**公共*/
		public final static Integer OPERATION_DB_EX= 1001;
		public final static Integer REQUIRED_PARAM_EMPTY= 1002;
		public final static Integer MD5_EXCEPTION= 1003;
		public final static Integer DECRYPTION_EXCEPTION= 1004;
		public final static Integer ENCRYPTION_EXCEPTION= 1005;
		public final static Integer HTTP_EXCEPTION= 1006;
		public final static Integer SUCCESS = 0;
		public final static String SUCCESS_C = "Success"; 
		public final static String SUCCESS_M = "操作成功"; 
		
		
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
		public final static Integer OLD_PAYPWD_NOT_RIGHT=7016;
		
		public static final Integer USER_NOT_EXIST = 7017;
		public static final Integer BANK_INFO_ALREADY_EXIST = 7018;
		
		static {
			zhMsgMap.put(OPERATION_DB_EX, "操作数据库程序异常");
			zhMsgMap.put(REQUIRED_PARAM_EMPTY, "必填参数为空");
			zhMsgMap.put(MD5_EXCEPTION, "生成MD5程序异常");
			zhMsgMap.put(DECRYPTION_EXCEPTION, "解密程序异常");
			zhMsgMap.put(ENCRYPTION_EXCEPTION, "加密程序异常");
			zhMsgMap.put(HTTP_EXCEPTION, "HTTP请求异常");
			
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
			
			zhMsgMap.put(USER_NOT_EXIST, "当前用户在会员系统不存在");
			zhMsgMap.put(BANK_INFO_ALREADY_EXIST, "当前会员银行卡账户信息已存在");
		}
		
		/**
		 * 会员余额 审核相关
		 */
		//审核操作  agree同意
		public final static String OPERATE_AGREE="agree";
		//审核操作  reject拒绝
		public final static String OPERATE_REJECT="reject";
		
		/**
		 * 查询会员账号NUll
		 */
		public final static Integer ACCOUNT_IS_NULL_ERROR=3001;
		public final static String ACCOUNT_IS_NULL_ERROR_C="查询会员账号错误";
		
		/**
		 * 查询会员调整余额明细 NUll
		 */
		public final static Integer ACCOUNT_REVISE_IS_NULL_ERROR=3002;
		public final static String ACCOUNT_REVISE_IS_NULL_ERROR_C="查询会员调整余额明细错误";
		
		/**
		 * 调减金额不能大于当前金额 NUll
		 */
		public final static Integer ACCOUNT_REVISE_BALANCE_ERROR=3003;
		public final static String ACCOUNT_REVISE_BALANCE_ERROR_C="调减金额不能大于当前金额";
		
}
