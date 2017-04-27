package com.meiduimall.service.account.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.account.constant.ApiStatusConst;

/**
 * 数据库加密解密类
 * @author chencong
 *
 */
public class DESC {
	
	private final static Logger logger=LoggerFactory.getLogger(DESC.class);
	
	private static boolean IS_OPEN = true;
	
	/**
	 * 字符串加密 系统默认方式
	 * @param str 需要加密的字符串
	 * @return
	 * @throws SystemException 
	 */
	public static String encryption(String str) throws SystemException{
		if (StringUtil.isEmptyByString(str) || "null".equals(str)) {
			return null;
		} else {
			if (IS_OPEN) {
				//加密key在config文件中,config文件不在member_common工程中
				return encrypt(str, SystemConfig.configMap.get("API_KEY_NAME"));
			} else {
				return str;
			}
		}
	}

	/**
	 * 字符串解密 系统默认方式
	 * @param str 需要解密的字符串
	 * @return
	 * @throws SystemException 
	 */
	public static String deyption(String str) throws SystemException {
		if (StringUtil.isEmptyByString(str) || "null".equals(str)) {
			return null;
		} else {
			if (IS_OPEN) {
				return decrypt(str, SystemConfig.configMap.get("API_KEY_NAME"));
			} else {
				return str;
			}
		}
	}

	/**
	 * 字符串加密
	 * @param str 需要加密的字符串
	 * @param memberId 会员编号
	 * @return
	 * @throws SystemException 
	 */
	public static String encryption(String str, String memberId) throws SystemException {
		if (StringUtil.isEmptyByString(memberId) || StringUtil.isEmptyByString(str) || "null".equals(str)) {
			return null;
		} else {
			if (IS_OPEN) {
				return encrypt(str, MD5Util.encrypeString(memberId));
			} else {
				return str;
			}
		}
	}

	/**
	 * 字符串解密
	 * @param str 需要解密的字符串
	 * @param memberId 会员编号
	 * @return
	 * @throws SystemException 
	 */
	public static String deyption(String str, String memberId) throws SystemException {
		if (StringUtil.isEmptyByString(memberId) || StringUtil.isEmptyByString(str) || "null".equals(str)) {
			return null;
		} else {
			if (IS_OPEN) {
				return decrypt(str, MD5Util.encrypeString(memberId));
			} else {
				return str;
			}
		}
	}

	private static SecretKey keyGenerator(String keyStr) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte input[] = HexString2Bytes(keyStr);
		DESKeySpec desKey = new DESKeySpec(input);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		return securekey;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	private static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	private static String encrypt(String data, String key) throws SystemException  {
		String result=null;
		try {
			Key deskey=keyGenerator(key);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			SecureRandom random = new SecureRandom();
			cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
			result=Base64.encodeBase64String(cipher.doFinal(data.getBytes("GBK")));
		} catch (Exception e) {
			logger.error("执行encrypt()方法程序异常：{}",e.toString());
			throw new SystemException(ApiStatusConst.ENCRYPTION_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.ENCRYPTION_EXCEPTION));
		}
		return result;
	}

	private static String decrypt(String data, String key) throws SystemException {
		String result=null;
		try {
			Key deskey = keyGenerator(key);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			result=new String(cipher.doFinal(Base64.decodeBase64(data)), "GBK");
		} catch (Exception e) {
			logger.error("执行decrypt()方法程序异常：{}",e.toString());
			throw new SystemException(ApiStatusConst.DECRYPTION_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DECRYPTION_EXCEPTION));
		}
		return result;
	}

}