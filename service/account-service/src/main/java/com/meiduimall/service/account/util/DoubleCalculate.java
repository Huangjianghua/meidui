package com.meiduimall.service.account.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 小数运算工具类
 * @author chencong
 *
 */
public class DoubleCalculate {

	/**
	 * 两个小数相加
	 * @param value1 加数
	 * @param value2 被加数
	 * @return 两个小数的和
	 */
	public static double add(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 两个小数相减
	 * @param value1 被减数
	 * @param value2 减数
	 * @return 两个小数的差
	 */
	public static double sub(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 两个小数相乘
	 * @param value1 被乘数
	 * @param value2 乘数
	 * @return 两个小数的积
	 */
	public static double mul(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 两个小数相除
	 * @param value1 被除数
	 * @param value2 除数
	 * @return 两个小数的商
	 */
	public static double div(double value1, double value2, int scale) {
		if (scale < 0) {
			scale = 8;
		}
		MathContext mc = new MathContext(scale, RoundingMode.HALF_DOWN);
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.divide(b2, mc).doubleValue();
	}
	
	/**
	 * 将小数保留两位
	 * @param d 原小数
	 * @return 保留两位小数点后的数字
	 */
	public static Double getFormalValueTwo(Double d) {
		if (d == null){
			return 0.00;
		} else {
			Double newD = new Double(d);
			DecimalFormat formater = new DecimalFormat("0.00");
			formater.setMaximumFractionDigits(2);
			formater.setGroupingSize(0);
			formater.setRoundingMode(RoundingMode.FLOOR);
			return Double.valueOf(formater.format(newD));
		}
	}
}
