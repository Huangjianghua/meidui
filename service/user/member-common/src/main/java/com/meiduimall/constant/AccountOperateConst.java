package com.meiduimall.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户操作类型常量，包括积分和现金
 * @author chencong
 *
 */
public class AccountOperateConst {
	
	/** 积分操作类型 */
	public static Map<String, String> scoreOpType = new HashMap<String, String>();
	static {
		scoreOpType.put("005CBCBA-B541-11E6-A063-FCAA149389FF", "新注册赠送");
		scoreOpType.put("05E7AF9E-B541-11E6-A063-FCAA149389FF", "邀请注册赠送");
		scoreOpType.put("0A3F1BFA-B541-11E6-A063-FCAA149389FF", "现金充值");
		scoreOpType.put("0F1264FB-B541-11E6-A063-FCAA149389FF", "积分赠送");
		scoreOpType.put("662F6748-B602-11E6-A063-FCAA149389FF", "积分转入");
		scoreOpType.put("BCCB4476-B540-11E6-A063-FCAA149389FF", "其它");
		scoreOpType.put("C48195CB-B601-11E6-A063-FCAA149389FF", "积分转出");
		scoreOpType.put("D90F5998-9CD6-11E6-AB9D-FCAA149389FF", "调增");
		scoreOpType.put("D90F5C23-9CD6-11E6-AB9D-FCAA149389FF", "调减");
		scoreOpType.put("D90F5D10-9CD6-11E6-AB9D-FCAA149389FF", "订单消费");
		scoreOpType.put("D90F5DEA-9CD6-11E6-AB9D-FCAA149389FF", "订单退款");
		scoreOpType.put("E6BB5917-B540-11E6-A063-FCAA149389FF", "充值");
		scoreOpType.put("ED41FA17-B540-11E6-A063-FCAA149389FF", "财务操作");
		scoreOpType.put("F1BD17EC-B540-11E6-A063-FCAA149389FF", "订单取消");
		scoreOpType.put("F6BDCD07-B540-11E6-A063-FCAA149389FF", "附近消费");
		scoreOpType.put("FB739AD7-B540-11E6-A063-FCAA149389FF", "全民推广");
	}
	
	/** 账户类型-积分账户：AT01 */
	public static final String ACCOUNT_TYPE_POINTS = "AT01";
	/** 账户类型-现金账户：AT02 */
	public static final String ACCOUNT_TYPE_MONEY = "AT02";
}
