package com.meiduimall.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.meiduimall.core.Constants;

/**
 * 工具类
 * @author chencong
 *
 */
public class ToolUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ToolUtils.class);


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
			if(Constants.ENCODE_UTF8.equalsIgnoreCase(code)){
				encodestr = URLEncoder.encode(str, Constants.ENCODE_UTF8);
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
		String defaultCharSet=Constants.ENCODE_UTF8;
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
