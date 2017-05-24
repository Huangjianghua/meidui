package com.meiduimall.application.mall.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.meiduimall.application.mall.constant.MallApiCode;
import com.meiduimall.exception.ServiceException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Des {



	private static final String DES = "DES";

	

	public static String appencrypt(String data, String key) {
		byte[] bt;
		try {
			bt = encrypt(data.getBytes(), key.getBytes());
		} catch (Exception e) {
			Logger.error("加密错误: %s", e);
			throw new ServiceException(MallApiCode.ENCRYPT_ERROR,MallApiCode.getZhMsg(MallApiCode.ENCRYPT_ERROR));
		}
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}
	
	public static String appdecrypt(String data, String key) {
		try {
			if (data == null)
				return null;
			String decode = URLDecoder.decode(URLDecoder.decode(data, "utf-8"),"utf-8");
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] buf = decoder.decodeBuffer(decode);
			byte[] bt = decrypt(buf, key.getBytes());
			return new String(bt);
		} catch (IOException e) {
			Logger.error("解密错误: %s", e);
			throw new ServiceException(MallApiCode.DECRYPT_ERROR,MallApiCode.getZhMsg(MallApiCode.DECRYPT_ERROR));
		} catch (Exception e) {
			Logger.error("解密错误: %s", e);
			throw new ServiceException(MallApiCode.DECRYPT_ERROR,MallApiCode.getZhMsg(MallApiCode.DECRYPT_ERROR));
		}
	}


	 

	 

 

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key)  {
		try {
			// 生成一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);

			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES);

			// 用密钥初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

			return cipher.doFinal(data);
		} catch (Exception e) {
			Logger.error("加密错误: %s", e);
			throw new ServiceException(MallApiCode.ENCRYPT_ERROR,MallApiCode.getZhMsg(MallApiCode.ENCRYPT_ERROR));
		}
	}

	
	private static byte[] decrypt(byte[] data, byte[] key) {
		try {
			// 生成一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);

			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES);

			// 用密钥初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

			return cipher.doFinal(data);
		} catch (Exception e) {
			Logger.error("解密错误: %s", e);
			throw new ServiceException(MallApiCode.DECRYPT_ERROR,MallApiCode.getZhMsg(MallApiCode.DECRYPT_ERROR));
		}
	}
	
}
