package com.meiduimall.service.search.util;

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
import org.springframework.util.StringUtils;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.password.util.MD5;
import com.meiduimall.service.search.constant.Constant;

/**
 * 数据库加密解密类
 * 
 * @author chencong
 *
 */
public class DESC {

	private final static Logger logger = LoggerFactory.getLogger(DESC.class);

	private final static String key = Constant.API_KEY_NAME;// 加密解密需要的key

	/**
	 * 字符串加密 系统默认方式
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return
	 * @throws MdSysException
	 */
	public static String firstEncryption(String str) {
		try {
			return encrypt(str, key);
		} catch (MdSysException e) {
			logger.error("firstEncryption-- " + e);
			return str;
		}
	}

	/**
	 * 字符串解密 系统默认方式
	 * 
	 * @param str
	 *            需要解密的字符串
	 * @return
	 * @throws MdSysException
	 */
	public static String firstDeyption(String str) {
		try {
			return decrypt(str, key);
		} catch (MdSysException e) {
			logger.error("firstDeyption-- " + e);
			return str;
		}
	}

	/**
	 * 字符串加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @param memberId
	 *            会员编号
	 * @return
	 * @throws MdSysException
	 */
	public static String firstEncryption(String str, String memberId) {
		try {
			return encrypt(str, MD5.encode(memberId).toUpperCase());
		} catch (Exception e) {
			logger.error("firstEncryption-- " + e);
			return str;
		}
	}

	/**
	 * 字符串解密
	 * 
	 * @param str
	 *            需要解密的字符串
	 * @param memberId
	 *            会员编号
	 * @return
	 * @throws MdSysException
	 */
	public static String firstDeyption(String str, String memberId) {
		try {
			return decrypt(str, MD5.encode(memberId).toUpperCase());
		} catch (Exception e) {
			logger.error("firstDeyption-- " + e);
			return str;
		}
	}

	private static String encrypt(String data, String key) throws MdSysException {
		String result = null;
		try {
			Key deskey = keyGenerator(key);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			SecureRandom random = new SecureRandom();
			cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
			result = Base64.encodeBase64String(cipher.doFinal(data.getBytes("GBK")));
		} catch (Exception e) {
			logger.error("加密程序异常：{}", e.toString());
			throw new MdSysException(9999);
		}
		return result;
	}

	private static String decrypt(String data, String key) throws MdSysException {
		if (StringUtils.isEmpty(data)) {
			return "";
		}
		String result = null;
		try {
			Key deskey = keyGenerator(key);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			result = new String(cipher.doFinal(Base64.decodeBase64(data)), "GBK");
		} catch (Exception e) {
			logger.error("解密程序异常：{}", e.toString());
			throw new MdSysException(9999);
		}
		return result;
	}

	private static SecretKey keyGenerator(String keyStr)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte input[] = HexString2Bytes(keyStr);
		DESKeySpec desKey;
		desKey = new DESKeySpec(input);
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

}