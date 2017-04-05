/**
 * 
 */
package com.first.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanming
 * @category 级别比率表
 */
public class LevelAndRatioUtil {
	public static final List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	
	static{
		List<Map> listmap = new ArrayList<Map>();
		Map<String,String> map = new HashMap<String,String>();
		List<String> listlevel = new ArrayList<String>();
		List<String> listratio = new ArrayList<String>();
		listlevel.add(0, "普卡");
		listlevel.add(1, "银卡");
		listlevel.add(2,"金卡");
		listlevel.add(3, "钻卡");
		listlevel.add(4, "VIP");
		listlevel.add(5, "县经销");
		listlevel.add(6, "三级区经销");
		listlevel.add(7, "二级区经销");
		listlevel.add(8, "一级区经销");
		listlevel.add(9, "三级市经销");
		listlevel.add(10, "二级市经销");
		listlevel.add(11, "一级市经销");
		listlevel.add(12, "三级省代理");
		listlevel.add(13, "二级省代理");
		listlevel.add(14, "一级省代理");
		listlevel.add(15, "二级全国代理");
		listlevel.add(16, "一级全国代理");
		listlevel.add(17, "二级全洲代理");
		listlevel.add(18, "一级全洲代理");
		listlevel.add(19, "二级全球代理");
		listlevel.add(20, "一级全球代理");
		listlevel.add(21, "一级全球代理VIP");
		listratio.add(0, "5%");
		listratio.add(1, "8%");
		listratio.add(2, "11%");
		listratio.add(3, "14%");
		listratio.add(4, "17%");
		listratio.add(5, "20%");
		listratio.add(6, "24%");
		listratio.add(7, "28%");
		listratio.add(8, "32%");
		listratio.add(9, "36%");
		listratio.add(10, "40%");
		listratio.add(11, "44%");
		listratio.add(12, "48%");
		listratio.add(13, "52%");
		listratio.add(14, "56%");
		listratio.add(15, "60%");
		listratio.add(16, "64%");
		listratio.add(17, "68%");
		listratio.add(18, "72%");
		listratio.add(19, "76%");
		listratio.add(20, "80%");
		listratio.add(21, "85%");

		
		for(int i = 0;i<22;i++){
			map.put(listlevel.get(i), listratio.get(i));
			listmap.add(i, map);
		}		
	}
	
}
