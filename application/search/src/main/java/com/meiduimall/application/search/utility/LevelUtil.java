package com.meiduimall.application.search.utility;

import java.util.ArrayList;
import java.util.List;

public class LevelUtil {

	//传入积分，输出积分级别与分益比率
	public static List<String> judgeLevel(String str){
		List<String> list = new ArrayList<String>();
		int i = Integer.parseInt(str);
		if(1 <= i && i <5){
			list.add(0, "普卡");
			list.add(1, "5%");
			return list;
		}
		else if(5 <= i && i<10){
			list.add(0, "银卡");
			list.add(1, "8%");
			return list;
		}
		else if(10 <=i && i<15){
			list.add(0,"金卡");
			list.add(1, "11%");
			return list;
		}
		else if(15 <= i&& i<25){
			list.add(0, "钻卡");
			list.add(1, "14%");
			return list;
		}
		else if(25 <= i&& i<50){
			list.add(0, "VIP");
			list.add(1, "17%");
			return list;
		}
		else if(50 <= i&& i<100){
			list.add(0, "县经销");
			list.add(1, "20%");
			return list;
		}
		else if(100 <= i&& i<200){
			list.add(0, "三级区经销");
			list.add(1, "24%");
			return list;
		}
		else if(200 <= i&& i<500){
			list.add(0, "二级区经销");
			list.add(1, "28%");
			return list;
		}
		else if(500 <= i&& i<1000){
			list.add(0, "一级区经销");
			list.add(1, "32%");
			return list;
		}
		else if(1000 <= i&& i<2000){
			list.add(0, "三级市经销");
			list.add(1, "36%");
			return list;
		}
		else if(2000 <= i&& i<5000){
			list.add(0, "二级市经销");
			list.add(1, "40%");
			return list;
		}
		else if(5000 <= i&& i<10000){
			list.add(0, "一级市经销");
			list.add(1, "44%");
			return list;
		}
		else if(10000 <= i&& i<20000){
			list.add(0, "三级省代理");
			list.add(1, "48%");
			return list;
		}
		else if(20000 <= i&& i<40000){
			list.add(0, "二级省代理");
			list.add(1, "52%");
			return list;
		}
		else if(40000 <= i&& i<80000){
			list.add(0, "一级省代理");
			list.add(1, "56%");
			return list;
		}
		else if(80000 <= i&& i<150000){
			list.add(0, "二级全国代理");
			list.add(1, "60%");
			return list;
		}
		else if(150000 <= i&& i<250000){
			list.add(0, "一级全国代理");
			list.add(1, "64%");
			return list;
		}
		else if(250000 <= i&& i<400000){
			list.add(0, "二级全洲代理");
			list.add(1, "68%");
			return list;
		}
		else if(400000 <= i&& i<600000){
			list.add(0, "一级全洲代理");
			list.add(1, "72%");
			return list;
		}
		else if(600000 <= i&& i<800000){
			list.add(0, "二级全球代理");
			list.add(1, "76%");
			return list;
		}
		else if(800000 <= i&& i<1000000){
			list.add(0, "一级全球代理");
			list.add(1, "80%");
			return list;
		}
		else if(1000000 <= i){
			list.add(0, "一级全球代理VIP");
			list.add(1, "85%");
			return list;
		}
		else{
			return list;
		}
	}
}
