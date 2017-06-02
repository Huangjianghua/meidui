package com.meiduimall.application.usercenter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.constant.ConstSysParams;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * MD5工具类
 * @author chencong
 *
 */
public class MD5Utils {
	
	private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);
	
	/**
	 * 生成签名
	 * @param reqJson HTTP请求数据
	 * @param secretkey 密钥
	 * @return 统一返回数据格式
	 * @throws MdSysException 系统异常
	 */
	public static String getSign(JSONObject reqJson,String secretkey) throws MdSysException{
		String serverSign=null;
		String[] arr = new String[reqJson.containsKey(ConstSysParams.SIGN)?(reqJson.size()- 1):reqJson.size()];
		int i=0;
		try {
			for (String key: reqJson.keySet()) {
				if (ConstSysParams.SIGN.equals(key))
					continue;
				arr[i] = key+ ConstSysParams.EQUALS_SYMBOL +reqJson.getString(key);
				i++;
			}
			Arrays.sort(arr);
			StringBuffer buffer = new StringBuffer();
			for (int k = 0; k < arr.length; k++) {
				buffer.append(arr[k]);
				buffer.append(ConstSysParams.CONNECTION_SYMBOL);
			}
			buffer.append(ConstSysParams.KEY_LAST);
			buffer.append(secretkey);
			serverSign=MD5Utils.MD5EncryptBy32(buffer.toString()).toUpperCase();
		} catch (Exception e) {
			logger.error("生成签名程序异常",e.toString());
			throw new MdSysException(ConstApiStatus.CREATE_SIGN_EXCEPTION);
		}
		return serverSign;
	}
	/**
	 * MD5 32位加密
	 * @param values 需要加密的字符串
	 * @return 加密后的32位字符串
	 * @throws Exception
	 */
	public static String MD5EncryptBy32(String values) throws Exception {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(values.getBytes());
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
			logger.error("exec MD5EncryptBy32() error:{}",e.getMessage());
			throw e;
		}
		return buf.toString();
	}
	
	public static void updateSign(JSONObject reqJson,String clientID,String key) throws MdSysException{
		reqJson.put(ConstSysParams.CLIENTID,clientID);
		reqJson.put(ConstSysParams.TIMESATAMP,System.currentTimeMillis());
		reqJson.put(ConstSysParams.SIGN,MD5Utils.getSign(reqJson,key));
		logger.info("接入层请求网关更新签名成功");
	}
	
}