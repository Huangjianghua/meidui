package com.first.utility;

import java.util.List;
import java.util.Map;

public class PointCalculateUtil {
	
	public static double getPoint(String str, double price, double cost) throws IllegalAccessException {
		Object obj = PHPSerializer.unserialize(str.getBytes());
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) obj;
		double profit = DoubleCalculate.sub(price, cost);
		for (Map<String, Object> map : list) {
			String pointStr = map.get("point") + "";
			String highStr = map.get("high") + "";
			String type = map.get("type") + "";
			String lowStr = map.get("low") + "";
			
			Double low = Double.parseDouble(lowStr);
			Double high = Double.parseDouble(highStr);
			Double point = Double.parseDouble(pointStr);
			
			if (low < profit && profit <= high) {
				if (point == -1) {
					return point;
				}
				if ("abs".equals(type)) {
					double result = DoubleCalculate.sub(profit, point);
					return result < 0 ? 0 : result;
				} else if ("percent".equals(type)) {
					double result = DoubleCalculate.sub(profit, DoubleCalculate.mul(profit, DoubleCalculate.div(point, 100, 2), 0));
					return result < 0 ? 0 : result;
				} else {
					throw new IllegalAccessException("不支持的积分计算类型");
				}
			}
		}
		return 0;
	}
}
