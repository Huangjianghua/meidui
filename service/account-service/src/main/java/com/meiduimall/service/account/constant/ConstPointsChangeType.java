package com.meiduimall.service.account.constant;


/**
 * 积分变动类型
 * @author chencong
 *
 */
public enum ConstPointsChangeType {
	
	POINTS_OPERATOR_TYPE_TZ("D90F5998-9CD6-11E6-AB9D-FCAA149389FF", "调增"),
	POINTS_OPERATOR_TYPE_TJ("D90F5C23-9CD6-11E6-AB9D-FCAA149389FF", "调减"),
	POINTS_OPERATOR_TYPE_XF("D90F5D10-9CD6-11E6-AB9D-FCAA149389FF", "订单消费"),
	POINTS_OPERATOR_TYPE_TK("D90F5DEA-9CD6-11E6-AB9D-FCAA149389FF", "订单退款"),

	POINTS_OPERATOR_TYPE_QT("BCCB4476-B540-11E6-A063-FCAA149389FF", "其他"),
	POINTS_OPERATOR_TYPE_CZ("E6BB5917-B540-11E6-A063-FCAA149389FF", "充值"),
	POINTS_OPERATOR_TYPE_CW("ED41FA17-B540-11E6-A063-FCAA149389FF", "财务操作"),
	POINTS_OPERATOR_TYPE_QX("F1BD17EC-B540-11E6-A063-FCAA149389FF", "订单取消"),
	POINTS_OPERATOR_TYPE_FJXF("F6BDCD07-B540-11E6-A063-FCAA149389FF", "附近消费"),
	
	POINTS_OPERATOR_TYPE_QMTG("FB739AD7-B540-11E6-A063-FCAA149389FF", "全民推广"),
	POINTS_OPERATOR_TYPE_ZCZS("005CBCBA-B541-11E6-A063-FCAA149389FF", "新注册赠送"),
	POINTS_OPERATOR_TYPE_YQZCZS("05E7AF9E-B541-11E6-A063-FCAA149389FF", "邀请注册赠送"),
	POINTS_OPERATOR_TYPE_XJCZ("0A3F1BFA-B541-11E6-A063-FCAA149389FF", "现金充值"),
	POINTS_OPERATOR_TYPE_JFZS("0F1264FB-B541-11E6-A063-FCAA149389FF", "积分赠送"),
	POINTS_OPERATOR_TYPE_JFZR("662F6748-B602-11E6-A063-FCAA149389FF", "积分转入"),
	POINTS_OPERATOR_TYPE_JFZC("C48195CB-B601-11E6-A063-FCAA149389FF", "积分转出"),
	POINTS_OPERATOR_TYPE_NBZZ("21B260C3-A230-4601-8D62-420OPT20AT24", "内部转账"),
	
	POINTS_FREEZE_TYPE_DJ("D9138B5E-9CD6-11E6-AB9D-FCAA149389FF", "冻结"),
	POINTS_FREEZE_TYPE_JD("D9138E90-9CD6-11E6-AB9D-FCAA149389FF", "解冻");
	
	
	private String code;
	private String name;
	
	private ConstPointsChangeType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(String code){
		ConstPointsChangeType[] values = ConstPointsChangeType.values();
		for(ConstPointsChangeType val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}
