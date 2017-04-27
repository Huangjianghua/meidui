package com.meiduimall.service.account.util;

import java.util.Map;


/**
 * 类名:  SqlConvertUtil<br>
 * 描述:  sql脚本处理工具类 <br>
 * 创建时间: 2016-12-5
 */
public class SqlConvertUtil {

	/**
	 * 方法名: convertManyForString<br>
	 * 描述:  对传入字符串进行分割处理，拼接成：'1','2','3'<br>
	 * 创建时间: 2016-12-5
	 * @param str
	 * @param splitStr
	 * @return
	 */
	public static String[] convertManyByStringArray(String str,String splitStr){
		String[] arys =str.split(splitStr);
		return arys;
	}
	
	/**
	 * 方法名: MapGenerateUpdateSql<br>
	 * 描述:  Map集合生成update中set脚本 <br>
	 * 创建时间: 2016-12-18
	 * @param sets
	 * @param updateMap
	 * @return
	 */
	public static String MapGenerateUpdateSql(String[] sets,Map<String,String> updateMap){
		StringBuffer sql = new StringBuffer();
		for (String key : sets) {
			if(updateMap.containsKey(key)){
				sql.append(key).append("='").append(updateMap.get(key)).append("',");
			}
		}
		if(sql.length() == 0){
			return "";
		}
		return sql.substring(0, (sql.length()-1));
	}
}
