package com.meiduimall.password;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;
import com.meiduimall.password.util.MD5;

public class GatewaySignUtil {
	
	
	/** &符号**/
	public final static String URLCONNCTION = "&";
	/** =符号**/
	public final static String URLEQUALS = "=";
	/**
	 * 编码类型
	 */
	public final static String INPUT_CHARSET_DEFAULT = "utf-8"; // 默认utf-8
	
	
	/**
	 * 功能描述:  产生签名
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午10:28:34
	 * @param appKey
	 * @param param
	 * @return String   
	 * @throws
	 */
	public static String  sign(String appKey,Map<String,String> param) {
		Map<String, String> map = new TreeMap<String, String>();
		map.putAll(param);
		Set<String> keySet = map.keySet();
		StringBuffer buffer = new StringBuffer();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (key.equals("sign")){
            	continue;
            }
            String value = map.get(key);
            if ( value == null || value.length() < 1 ) {
            	continue;
            }
            buffer.append(key);
            buffer.append(URLEQUALS);
            buffer.append(value);
            buffer.append(URLCONNCTION);
        }
        buffer.append("key=");
        buffer.append(appKey);
        return MD5.MD5Encode(buffer.toString()).toUpperCase();
	}
	
	/**
	 * 功能描述: 产生签名的字符串
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午10:28:55
	 * @param appKey
	 * @param parameters
	 * @throws Exception    
	 * @return String   
	 * @throws
	 */
	public static String buildEncodeSortParam(String appKey,Map<String, String> parameters) throws Exception{
		StringBuilder getSB = new StringBuilder();
		String key;
		String value;
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			if (key.equals("sign")||StringUtils.isEmpty(value)) {
				continue;
			}
			getSB.append(key).append(URLEQUALS)
					.append(URLEncoder.encode(value, INPUT_CHARSET_DEFAULT))
					.append(URLCONNCTION);
		}
		String[] arrs = getSB.toString().split(URLCONNCTION);
		Arrays.sort(arrs);
		return StringUtils.join(arrs, URLCONNCTION).concat("key=").concat(appKey);
	}
	


}
