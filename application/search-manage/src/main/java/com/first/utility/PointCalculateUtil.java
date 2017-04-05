package com.first.utility;

import java.util.List;
import java.util.Map;

public class PointCalculateUtil {
	
	public static double getPoint(String str, double price, double cost) throws IllegalAccessException {
		Object obj = PHPSerializer.unserialize(str.getBytes());
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = (List<Map<String, String>>) obj;
		double profit = DoubleCalculate.sub(price, cost);
		for (Map<String, String> map : list) {
			String pointStr = map.get("point");
			String highStr = map.get("high");
			String type = map.get("type");
			String lowStr = map.get("low");
			
			int low = Integer.parseInt(lowStr);
			long high = Long.parseLong(highStr);
			int point = Integer.parseInt(pointStr);
			
			if (point == -1) {
				return point;
			}
			if (low < profit && profit <= high) {
				if ("abs".equals(type)) {
					return point;
				} else if ("percent".equals(type)) {
					return DoubleCalculate.mul(cost, DoubleCalculate.div(point, 100, 2), 0);
				} else {
					throw new IllegalAccessException("不支持的积分计算类型");
				}
			}
		}
		return 0;
	}
}
