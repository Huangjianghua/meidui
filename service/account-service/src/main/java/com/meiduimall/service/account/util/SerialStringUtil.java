package com.meiduimall.service.account.util;


import com.meiduimall.service.account.constant.ConstDataAppSource;
import com.meiduimall.service.account.constant.ConstPointsChangeType;
import com.meiduimall.service.account.constant.ConstSpecialSymbol;

/**
 * 字符串序列转化为指定值
 * @author chencong
 *
 */
public class SerialStringUtil {
	
	/**
	 * 数据来源转换成字典值
	 * @param orderSource
	 * @return
	 */
	public static String getDictOrderSource(String orderSource){
		String dictOrderSource = "";
		String upperOrderSource = orderSource.toLowerCase();
		switch (upperOrderSource) {
		case "1gw":
			dictOrderSource = ConstDataAppSource.ORDER_SOURCE_1GW;
			break;
		case "o2o":
			dictOrderSource = ConstDataAppSource.ORDER_SOURCE_O2O;
			break;
		case "app":
			dictOrderSource = ConstDataAppSource.ORDER_SOURCE_APP;
			break;
		case "md":
			dictOrderSource = ConstDataAppSource.ORDER_SOURCE_MD;
			break;
		case "md1gw":
			dictOrderSource = ConstDataAppSource.ORDER_SOURCE_MD1GW;
			 break;
		default:
			dictOrderSource = ConstDataAppSource.ORDER_SOURCE_1GW;
		}
		return dictOrderSource;
	}
	

	/**
	 * 交易类型转换成字典值
	 * @param oprType
	 * @return
	 */
	public static String getDictOperatorType(String oprType){
		String dictId = "";
		String code = oprType.toUpperCase();
		switch (code) {
		case "0":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_QT.getCode();
			break;
		case "1":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_CZ.getCode();
			break;
		case "3":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_CW.getCode();
			break;
		case "4":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_TK.getCode();
			break;
		case "5":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_QX.getCode();
			break;
		case "6":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_FJXF.getCode();
			 break;
		case "7":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_QMTG.getCode();
			 break;
		case "8":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_XF.getCode();
			 break;
		case "9":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_ZCZS.getCode();
			 break;
		case "10":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_YQZCZS.getCode();
			 break;
		case "11":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_XJCZ.getCode();
			 break;
		case "12":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_JFZR.getCode();
			 break;
		case "13":
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_JFZC.getCode();
			 break;
		default:
			dictId = ConstPointsChangeType.POINTS_OPERATOR_TYPE_QT.getCode();
		}
		return dictId;
	}
	
	/**
	 * 多个交易类型转换成字典值
	 * @param oprTypes
	 * @param splitStr
	 * @return
	 */
	public static String getDictManyOperatorType(String oprTypes, String splitStr){
		String dictIds = "";
		if(StringUtil.isEmptyByString(oprTypes)){
			return dictIds;
		}else{
			String[] oprStrs = oprTypes.split(splitStr);
			int loopCnt = 0;
			for (String type : oprStrs) {
				loopCnt = loopCnt + 1;
				if(loopCnt == oprStrs.length){
					dictIds = dictIds + SerialStringUtil.getDictOperatorType(type);
				}else{
					dictIds = dictIds + SerialStringUtil.getDictOperatorType(type) + ",";
				}
			}
		}
		return dictIds;
	}
	
	/**
	 * 交易类型转换成固定格式备注
	 * @param oprDictId
	 * @param userid
	 * @return
	 */
	public static String getPointsRemark(String oprDictId,String userid){
		String returnStr = "";
		String code = oprDictId.toUpperCase();
		String appendStr = StringUtil.isEmptyByString(userid) ? "" : (ConstSpecialSymbol.SUB+userid);
		if(ConstPointsChangeType.POINTS_OPERATOR_TYPE_QT.equals(code)){
			returnStr = "外部[其他交易" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_CZ.getCode().equals(code)) {
			returnStr = "充值[积分充值" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_CW.getCode().equals(code)) {
			returnStr = "系统[后台调整" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_TK.getCode().equals(code)) {
			returnStr = "退款[售后退款" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_QX.getCode().equals(code)) {
			returnStr = "退款[取消订单" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_FJXF.getCode().equals(code)) {
			returnStr = "赠送[附近消费" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_QMTG.getCode().equals(code)) {
			returnStr = "提成[全民推广" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_XF.getCode().equals(code)) {
			returnStr = "消费[积分支付" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_ZCZS.getCode().equals(code)) {
			returnStr = "赠送[新注册送积分" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_YQZCZS.getCode().equals(code)) {
			returnStr = "赠送[推荐注册送积分" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_XJCZ.getCode().equals(code)) {
			returnStr = "充值[现金充值" + appendStr + "]";
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_JFZC.getCode().equals(code)) {
			returnStr = "转帐[转出到" + userid + "]";
			if(StringUtil.isEmptyByString(userid)){
				returnStr = "转帐[积分转出]";
			}
		}
		else if (ConstPointsChangeType.POINTS_OPERATOR_TYPE_JFZR.getCode().equals(code)) {
			returnStr = "转帐[从" + userid + "转入]";
			if(StringUtil.isEmptyByString(userid)){
				returnStr = "转帐[积分转入]";
			}
		}
		else {
			returnStr = "外部[其他交易" + appendStr + "]";
		}
		return returnStr;
	}

}
