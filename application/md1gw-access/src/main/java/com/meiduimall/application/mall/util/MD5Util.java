package com.meiduimall.application.mall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.mall.constant.OauthConst;
import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.exception.ServiceException;

public class MD5Util {
	

	/**
	 * 根据请求参数和key生成签名
	 * @param parameters 包含请求参数的list集合
	 * @param key 密钥
	 * @return
	 * @throws Exception 
	 */
	public static String getSign(Collection<? extends Map.Entry<String, String>> parameters,String key,String clientID,String timestamp,int type) 
	{
		try {
			Logger.info("参数:"+JSONObject.toJSONString(parameters));
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
			StringBuilder buffer = new StringBuilder();
			for (int k = 0; k < arr.length; k++) {
				buffer.append(arr[k]);
				buffer.append(OauthConst.CONNECTION_SYMBOL);
			}
			buffer.append(OauthConst.SECRETKEY_NAME);
			buffer.append(key);
			String syssign=MD5Util.mD5EncryptBy32(buffer.toString()).toUpperCase();
			return syssign;
		} catch (Exception e) {
			Logger.error("system error", e);
			throw e;
		}
	}

	// 该方法将你输入的字符串，通过md5加密，返回一个加密后的字符串(30位:去除前2位)
	public static String mD5EncryptBy30(String inStr) {
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

		StringBuilder str = new StringBuilder();
		String tempStr = "";
		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				str.append("0");
				str.append(tempStr);
			} else {
				str.append(tempStr);
			}
		}
		return str.toString().toLowerCase();
	}

	/**
	 * Description : 32位加密
	 * 
	 * @param values
	 * @return
	 */
	public static String mD5EncryptBy32(String values) {
		StringBuilder buf = new StringBuilder("");
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
			Logger.error("MD5Encrypt异常:%s", e);
			throw new ServiceException(MallApiCode.MD5ENCRYPT_EXCEPTION, MallApiCode.getZhMsg(MallApiCode.MD5ENCRYPT_EXCEPTION));
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
	public static String mD5EncryptBy16(String values) {
		String str = "";
		try {
			str = MD5Util.mD5EncryptBy32(values);
		} catch (Exception e) {
			 Logger.error("16位加密错误: %s", e);
		}
		return str.substring(8, str.length() - 8);
	}

	// 随机生成5位随机数
	public final static String get5Radom() {
		StringBuilder newString = new StringBuilder();

		// 得到0.0到1.0之间的数字,并扩大100000倍
		double doubleP = Math.random() * 100000;

		// 如果数据等于100000,则减少1
		if (doubleP >= 100000) {
			doubleP = 99999;
		}

		// 然后把这个数字转化为不包含小数点的整数
		int tempString = (int) Math.ceil(doubleP);

		 

		// 把得到的数增加为固定长度,为5位
		while (newString.length() < 5) {
			newString.append("0");
			newString.append(tempString);
		}

		return newString.toString();
	}

	// 主要把传递过来的字符串参数转化为经过MD5算法加密的字符串
	public static final String encrypeString(String neededEncrypedString){
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
			Logger.error("MD5算法加密错误:%s", ex);
			throw new ServiceException(MallApiCode.MD5_ERROR, MallApiCode.getZhMsg(MallApiCode.MD5_ERROR));
		}

		// 返回加密之后的字符串
		return encrypeString;
	}

	/**
	 * Description : 生成一个随机数然后MD5加密
	 * 
	 * @return
	 */
	public static final String getMD5StringByRandom() {
		String md5 = "";
		try {
			md5 = encrypeString(get5Radom());
		} catch (Exception e) {
			Logger.error("getMD5String异常:", e);
		}
		return md5;
	}

	// 固定密钥加密
	public static String hexEncode(String str) {
		String hexString = null;
		if (str != null && str.length() > 0) {
			char[] digital = "0123456789ABCDEF".toCharArray();
			StringBuilder sb = new StringBuilder("");
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
	public static String hexDecode(String hexString) {
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
	public static final String getRandomNum() {

		// 当前秒数
		String timeMillis = String.valueOf(System.currentTimeMillis() / 1000L);

		StringBuilder newString = new StringBuilder();
		// 得到0.0到1.0之间的数字,并扩大100000倍
		double doubleP = Math.random() * 100000;
		// 如果数据等于100000,则减少1
		if (doubleP >= 100000) {
			doubleP = 99999;
		}
		// 然后把这个数字转化为不包含小数点的整数
		int tempString = (int) Math.ceil(doubleP);
		 
		// 把得到的数增加为固定长度,为5位
		while (newString.length() < 5) {
			newString.append("0");
			newString.append(tempString);
		}
		return (timeMillis + newString);
	}
	
	
	 //微信Md5加密工具
	  private static String byteArrayToHexString(byte b[]) {
	      StringBuilder resultSb = new StringBuilder();
	      for (int i = 0; i < b.length; i++)
	         resultSb.append(byteToHexString(b[i]));

	      return resultSb.toString();
	   }
	//微信Md5加密工具
	   private static String byteToHexString(byte b) {
	      int n = b;
	      if (n < 0)
	         n += 256;
	      int d1 = n / 16;
	      int d2 = n % 16;
	      return hexDigits[d1] + hexDigits[d2];
	   }
	 //微信Md5加密工具
	   public static String mD5Encode(String origin, String charsetname) {
	      String resultString = null;
	      try {
	         resultString = origin;
	         MessageDigest md = MessageDigest.getInstance("MD5");
	         if (charsetname == null || "".equals(charsetname))
	            resultString = byteArrayToHexString(md.digest(resultString
	                  .getBytes()));
	         else
	            resultString = byteArrayToHexString(md.digest(resultString
	                  .getBytes(charsetname)));
	      } catch (Exception e) {
	    	  Logger.error("微信Md5加密工具异常: %s", e);
	      }
	      return resultString;
	   }
	 //微信Md5加密工具
	   private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
	         "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}