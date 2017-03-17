package com.meiduimall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.OauthConst;

public class MD5Util {
	
	/**
	 * 根据请求参数和key生成签名
	 * @param parameters 包含请求参数的list集合
	 * @param key 密钥
	 * @return
	 * @throws Exception 
	 */
	public static String getSign(Collection<? extends Map.Entry<String, String>> parameters,String key,String clientID,String timestamp,int type) throws Exception
	{
		try {
			Logger.info("长度"+JSONObject.toJSONString(parameters));
			String[] arr = new String[parameters.size() - 1];
			int i = 0;
			for (Map.Entry<String, String> p : parameters) {
				if (OauthConst.SIGN.equals(p.getKey()))
					continue;
				else
					//如果是接入层请求网关生成签名，更换clientID和timestamp
					if(2==type)
					{
						if(OauthConst.CLIENT_ID.equals(p.getKey()))
						{
							p.setValue(clientID);
						}
						if(OauthConst.TIMESATAMP.equals(p.getKey()))
						{
							p.setValue(timestamp);
						}
					}
					arr[i] = p.getKey() + OauthConst.EQUALS_SYMBOL + p.getValue();
				i++;
			}
			Arrays.sort(arr);
			StringBuffer buffer = new StringBuffer();
			for (int k = 0; k < arr.length; k++) {
				buffer.append(arr[k]);
				buffer.append(OauthConst.CONNECTION_SYMBOL);
			}
			buffer.append(OauthConst.SECRETKEY_NAME);
			buffer.append(key);
			String syssign=MD5Util.MD5EncryptBy32(buffer.toString()).toUpperCase();
			return syssign;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 该方法将你输入的字符串，通过md5加密，返回一个加密后的字符串(30位:去除前2位)
	public static String MD5EncryptBy30(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("MD5"); // 可以选中其他的算法如SHA
			byte[] digest = md.digest(inStr.getBytes());
			// 返回的是byet[]，要转化为String存储比较方便
			outStr = bytetoString(digest);
		} catch (NoSuchAlgorithmException e) {
			Logger.error("MD5Encrypt16异常:", e);
		}
		return outStr;
	}

	public static String bytetoString(byte[] digest) {

		String str = "";
		String tempStr = "";
		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				str = str + "0" + tempStr;
			} else {
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}

	/**
	 * Description : 32位加密
	 * 
	 * @param values
	 * @return
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
			Logger.error("MD5Encrypt异常:", e);
			throw e;
		}
		return buf.toString();
	}
	
	/**
	 * Description : 16位加密(前去8位, 后去8位)
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月16日 下午5:59:04 
	 * 
	 * @param values
	 * @return
	 */
	public static String MD5EncryptBy16(String values) throws Exception {
		String str = MD5Util.MD5EncryptBy32(values);
		return str.substring(8, str.length() - 8);
	}

	// 随机生成5位随机数
	public final static String get5Radom() {
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

		return newString;
	}

	// 主要把传递过来的字符串参数转化为经过MD5算法加密的字符串
	public final static String encrypeString(String neededEncrypedString) throws Exception {
		// 初始化加密之后的字符串
		String encrypeString = null;

		// 16进制的数组
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		// 字符串的加密过程
		try {
			// 把需要加密的字符串转化为字节数组
			byte[] neededEncrypedByteTemp = neededEncrypedString.getBytes();

			// 得到MD5的加密算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 更新算法使用的摘要
			md.update(neededEncrypedByteTemp);

			// 完成算法加密过程
			byte[] middleResult = md.digest();

			// 把加密后的字节数组转化为字符串
			int length = middleResult.length;
			char[] neededEncrypedByte = new char[length * 2];
			int k = 0;
			for (int i = 0; i < length; i++) {
				byte byte0 = middleResult[i];
				neededEncrypedByte[k++] = hexDigits[byte0 >>> 4 & 0xf];
				neededEncrypedByte[k++] = hexDigits[byte0 & 0xf];
			}
			encrypeString = new String(neededEncrypedByte);
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex);
		}

		// 返回加密之后的字符串
		return encrypeString;
	}

	/**
	 * Description : 生成一个随机数然后MD5加密
	 * 
	 * @return
	 */
	public final static String getMD5StringByRandom() {
		String md5 = "";
		try {
			md5 = encrypeString(get5Radom());
		} catch (Exception e) {
			Logger.error("getMD5String异常:", e);
		}
		return md5;
	}

	// 固定密钥加密
	public static String HexEncode(String str) {
		String hexString = null;
		if (str != null && str.length() > 0) {
			char[] digital = "0123456789ABCDEF".toCharArray();
			StringBuffer sb = new StringBuffer("");
			try {
				byte[] bs = str.getBytes("utf-8");
				int bit;
				for (int i = 0; i < bs.length; i++) {
					bit = (bs[i] & 0x0f0) >> 4;
					sb.append(digital[bit]);
					bit = bs[i] & 0x0f;
					sb.append(digital[bit]);
				}
			} catch (Exception e) {
				Logger.error("HexEncode异常:", e);
			}
			hexString = sb.toString();
		}

		return hexString;
	}

	// 固定密钥解密
	public static String HexDecode(String hexString) {
		String str = null;
		if (hexString != null && hexString.length() > 0) {
			String digital = "0123456789ABCDEF";
			char[] hex2char = hexString.toCharArray();
			byte[] bytes = new byte[hexString.length() / 2];
			int temp;
			for (int i = 0; i < bytes.length; i++) {
				temp = digital.indexOf(hex2char[2 * i]) * 16;
				temp += digital.indexOf(hex2char[2 * i + 1]);
				bytes[i] = (byte) (temp & 0xff);
			}
			try {
				str = new String(bytes, "utf-8");
			} catch (Exception e) {
				Logger.error("HexDecode异常:", e);
			}
		}
		return str;
	}

	/**
	 * 方法名: getRandomNum<br>
	 * 描述: 生成不重复随机数，生成方式：毫秒+5位随机数<br>
	 * 编写者: admin <br>
	 * 创建时间: 2016-10-19
	 * 
	 * @return
	 */
	public final static String getRandomNum() {

		// 当前秒数
		String timeMillis = String.valueOf(System.currentTimeMillis() / 1000L);

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
}