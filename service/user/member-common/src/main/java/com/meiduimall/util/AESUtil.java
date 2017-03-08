package com.meiduimall.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Title : AESUtil 
 * Description : AESTools
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:48:16 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public class AESUtil {

	/**
	 * Description : AES 加密
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午3:40:06 
	 * 
	 * @param content
	 * @param aesKey
	 * @return
	 */
	public static byte[] encrypt(String content, String aesKey) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(aesKey.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Description : AES解密
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月14日 下午2:59:59 
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(byte[] content, String aesKey) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(aesKey.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return new String(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}