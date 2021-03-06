package com.meiduimall.service.member.util;

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

import com.meiduimall.core.Constants;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.constant.ConstSysParamsDefination;

/**
 * 数据库加密解密类
 * @author chencong
 *
 */
public class DESC {
	
	private final static Logger logger=LoggerFactory.getLogger(DESC.class);
	
	private final static String key=SystemConfig.configMap.get(ConstSysParamsDefination.DESC_KEY);//加密解密需要的key
	
	/**
	 * 字符串加密 系统默认方式
	 * @param str 需要加密的字符串
	 * @return
	 * @throws MdSysException
	 */
	public static String encryption(String str) throws MdSysException{
		return encrypt(str,key);
	}

	/**
	 * 字符串解密 系统默认方式
	 * @param str 需要解密的字符串
	 * @return
	 * @throws MdSysException
	 */
	public static String deyption(String str) throws MdSysException {
		return decrypt(str,key);
	}

	/**
	 * 字符串加密
	 * @param str 需要加密的字符串
	 * @param memberId 会员编号
	 * @return
	 * @throws MdSysException
	 */
	public static String encryption(String str, String memberId) throws MdSysException {
		return encrypt(str, MD5Util.encrypeString(memberId));
	}

	/**
	 * 字符串解密
	 * @param str 需要解密的字符串
	 * @param memberId 会员编号
	 * @return
	 * @throws MdSysException
	 */
	public static String deyption(String str, String memberId) throws MdSysException {
		return decrypt(str, MD5Util.encrypeString(memberId));
	}

	private static String encrypt(String data, String key) throws MdSysException {
		String result=null;
		try {
			Key deskey = keyGenerator(key);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			SecureRandom random = new SecureRandom();
			cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
			result=Base64.encodeBase64String(cipher.doFinal(data.getBytes(ConstSysParamsDefination.GBK)));
		} catch (Exception e) {
			logger.error("加密程序异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.ENCRYPTION_EXCEPTION);
		}
		return result;
	}


	private static String decrypt(String data, String key) throws MdSysException {
		if(StringUtils.isEmpty(data)){
			return "";
		}
		if(String.valueOf(Constants.CONSTANT_INT_ZERO).equals(data)){
			return String.valueOf(Constants.CONSTANT_INT_ZERO);
		}

		String result=null;
		try {
			Key deskey = keyGenerator(key);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			result=new String(cipher.doFinal(Base64.decodeBase64(data)),ConstSysParamsDefination.GBK);
		} catch (Exception e) {
			logger.error("解密程序异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.DECRYPTION_EXCEPTION);
		}
		return result;
	}
	
	private static SecretKey keyGenerator(String keyStr) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException{
		byte input[] = HexString2Bytes(keyStr);
		DESKeySpec desKey;
		desKey = new DESKeySpec(input);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ConstSysParamsDefination.DES);
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