package com.meiduimall.service.settlement.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.meiduimall.service.settlement.common.ShareProfitConstants;

/**
 * 工具类,采用fastjson
 * @author chencong
 *
 */
public class ToolUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ToolUtils.class);

	
	/**
	 * 传参创建map,必须是键值对的形式，例如quickMap("name","张三","age","14")
	 * @param item
	 * @return
	 */
	public static Map<String,String> quickMap(String...item){
		if(item.length%2==0){
			Map<String,String> quickMap = new HashMap<String,String>();
			for(int i=0;i<item.length;i++){
				quickMap.put(item[i], item[++i]);
			}
			return quickMap;
		}
		return null;
	}
	
	/**
	 * map转json
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, String> map)
	{
		if(map!=null)
		{
			return JSON.toJSONString(map);
		}
		return null;
	}
	
	/**
	 * json转map
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json)
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(json))
		{		
			return (Map<String, Object>)JSON.parse(json);
		}
		return map;
	}
	
	
	
	/**
	 * json转model
	 * @return
	 */
	public static Object jsonToModel(String json,Class<?> object)
	{
		if(StringUtils.isNoneBlank(json))
		{
			return JSON.parseObject(json,object);
		}
		return null;
	}
	
	//获取今天
	public static String getCurrentDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	
	//获取昨天
	public static String getUpDAY() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		return dateFormat.format(c.getTime());
	}

	/**
	 * 获取六位年月日
	 * @return
	 */
	public static String getCurrentSixDay() {
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}
	
	/**
	 * Description : 字符串utf-8转码
	 * Created By : Fkx 
	 * Creation Time : 2016-10-28 上午11:10:46 
	 * 
	 * @param str
	 * @param code
	 * @return
	 */
	public static String encode(String str,String code){
		
		String encodestr = str;
		
		try {
			if(ShareProfitConstants.ENCODE_UTF8.equalsIgnoreCase(code)){
				encodestr = URLEncoder.encode(str, ShareProfitConstants.ENCODE_UTF8);
			}else{
				return encodestr;
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		return encodestr;
	}
	
	/**
	 * 描述:  明文密码MD5加密,32位<br>
	 * @param values
	 * @return
	 */
	public static String MD5Encrypt(String values) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(values.toLowerCase().getBytes());
			byte b[] = md.digest();

			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	/**
	 * 描述:  生成不重复随机数，生成方式：毫秒+5位随机数<br>
	 * @return
	 */
	public final static String getRandomNum() {
		
		//当前秒数
		String timeMillis = String.valueOf(System.currentTimeMillis()/1000L);
		
		String newString = null;
		// 得到0.0到1.0之间的数字,并扩大100000倍
		double doubleP = Math.random() * 100000;
		// 如果数据等于100000,则减少1
		if (doubleP >= 100000) {
			doubleP = 99999;
		}
		// 然后把这个数字转化为不包含小数点的整数
		int tempString = (int) Math.ceil(doubleP);
		// 转化为字符串
		newString = "" + tempString;
		// 把得到的数增加为固定长度,为5位
		while (newString.length() < 5) {
			newString = "0" + newString;
		}

		return (timeMillis + newString);
	}
	
	/**
	 * Description : 加载配置文件
	 * Created By : Fkx 
	 * Creation Time : 2016-10-28 上午11:34:07 
	 * 
	 * @param config
	 * @return
	 */
	public static Map<String, String> loadProperty(String config) {
		InputStream is = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
			Properties pro = new Properties();
			pro.load(is);
			Iterator<Object> localIterator = pro.keySet().iterator();
			while (localIterator.hasNext()) {
				Object key = localIterator.next();
				map.put(key.toString(), pro.get(key).toString());
			}
		} catch (Exception ex) {
			System.out.println("配置文件:" + config + "加载出错!");
			ex.printStackTrace();
		} finally {
			try {
				if (is != null)
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * @param str
	 * @param charset
	 * @return
	 * @author alex.xu
	 */
	public static String encodeStr(String str,String charset){
		
		String encodestr = "";
		String defaultCharSet=ShareProfitConstants.ENCODE_UTF8;
		if(!StringUtils.isEmpty(charset)){
			defaultCharSet=charset;
		}
		
		try {
			if(!StringUtils.isEmpty(str)){
				encodestr = URLEncoder.encode(str, defaultCharSet);
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		return encodestr;
	}
	
}
