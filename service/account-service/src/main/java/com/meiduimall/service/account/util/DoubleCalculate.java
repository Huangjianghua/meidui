package com.meiduimall.service.account.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 小数运算工具类
 * @author chencong
 *
 */
public class DoubleCalculate {

	private static final Log log = LogFactory.getLog(DoubleCalculate.class);

	/**
	 * 提供精确加法计算的add方法
	 * 
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确减法运算的sub方法
	 * 
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确乘法运算的mul方法
	 * 
	 * @param value1
	 *            被乘数
	 * @param value2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供精确的除法运算方法div
	 * 
	 * @param value1
	 *            被除数
	 * @param value2
	 *            除数
	 * @param scale
	 *            精确范围 =精确度不能小于0
	 * @return 两个参数的商
	 */
	public static double div(double value1, double value2, int scale) {
		if (scale < 0) {
			scale = 8;// throw new IllegalAccessException("精确度不能小于0");
		}
		MathContext mc = new MathContext(scale, RoundingMode.HALF_DOWN);
		// 如果精确范围小于0，抛出异常信息

		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.divide(b2, mc).doubleValue();
	}

	public static double div2(double v1, double v2, int scale) {
        if (scale < 0) {
        	scale = 2;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	
	// 传入一个数字，8位小数点
	public static String getFormalValue(String str){
		if(str == null || str.trim().equals("") ){
			return "0.00000000";
		}else{
			if (str.indexOf(",") != -1) {
				str = str.replace(",", "");
				Double d = new Double(str);
				// DecimalFormat decimalFormat = new DecimalFormat("0.00");
				DecimalFormat formater = new DecimalFormat("0.00000000");
				formater.setMaximumFractionDigits(8);
				formater.setGroupingSize(0);
				formater.setRoundingMode(RoundingMode.FLOOR);
				return formater.format(d).toString();
			} else {
				Double d = new Double(str);
				// DecimalFormat decimalFormat = new DecimalFormat("0.00");
				DecimalFormat formater = new DecimalFormat("0.00000000");
				formater.setMaximumFractionDigits(8);
				formater.setGroupingSize(0);
				formater.setRoundingMode(RoundingMode.FLOOR);
				return formater.format(d).toString();
			}
		}
	}

	// 传入一个数字，2位小数点
	public static String getFormalValueTwo(String str) {
		if (str == null || str == "") {
			return "0.00";
		} else {
			if (str.indexOf(",") != -1) {
				str = str.replace(",", "");
				Double d = new Double(str);
				// DecimalFormat decimalFormat = new DecimalFormat("0.00");
				DecimalFormat formater = new DecimalFormat("0.00");
				formater.setMaximumFractionDigits(2);
				formater.setGroupingSize(0);
				formater.setRoundingMode(RoundingMode.FLOOR);
				return formater.format(d).toString();
			} else {
				Double d = new Double(str);
				// DecimalFormat decimalFormat = new DecimalFormat("0.00");
				DecimalFormat formater = new DecimalFormat("0.00");
				formater.setMaximumFractionDigits(2);
				formater.setGroupingSize(0);
				formater.setRoundingMode(RoundingMode.FLOOR);
				return formater.format(d).toString();
			}
		}
	}
}
