package com.meiduimall.application.search.utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DES {
	private static final Log log = LogFactory.getLog(DES.class);

	private static final String DES_ALGORITHM = "DES";

	public static String encryption(String plainData) throws Exception {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(DES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, createKey(DES_ALGORITHM));
		} catch (NoSuchAlgorithmException e) {
			log.error("encryption异常:", e);
		} catch (NoSuchPaddingException e) {
			log.error("encryption异常:", e);
		} catch (InvalidKeyException e) {
			log.error("encryption异常:", e);
		}

		try {
			byte[] buf = cipher.doFinal(plainData.getBytes());
			return Base64Utils.encode(buf);
		} catch (IllegalBlockSizeException e) {
			log.error("encryption异常:", e);
			throw new Exception("IllegalBlockSizeException", e);
		} catch (BadPaddingException e) {
			log.error("encryption异常:", e);
			throw new Exception("BadPaddingException", e);
		}

	}

	public static String decryption(String secretData) throws Exception {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(DES_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, createKey(DES_ALGORITHM));
		} catch (NoSuchAlgorithmException e) {
			log.error("decryption异常:", e);
			throw new Exception("NoSuchAlgorithmException", e);
		} catch (NoSuchPaddingException e) {
			log.error("decryption异常:", e);
			throw new Exception("NoSuchPaddingException", e);
		} catch (InvalidKeyException e) {
			log.error("decryption异常:", e);
			throw new Exception("InvalidKeyException", e);
		}

		try {
			byte[] buf = cipher.doFinal(Base64Utils.decode(secretData
					.toCharArray()));
			return new String(buf);
		} catch (IllegalBlockSizeException e) {
			log.error("decryption异常:", e);
			throw new Exception("IllegalBlockSizeException", e);
		} catch (BadPaddingException e) {
			log.error("decryption异常:", e);
			throw new Exception("BadPaddingException", e);
		}
	}

	private static SecretKey createKey(String secretKey)
			throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom(secretKey.getBytes());
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(DES_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			log.error("createKey异常:", e);
		}
		kg.init(secureRandom);
		return kg.generateKey();
	}

	static class Base64Utils {
		static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
				.toCharArray();
		static private byte[] codes = new byte[256];
		static {
			for (int i = 0; i < 256; i++)
				codes[i] = -1;
			for (int i = 'A'; i <= 'Z'; i++)
				codes[i] = (byte) (i - 'A');
			for (int i = 'a'; i <= 'z'; i++)
				codes[i] = (byte) (26 + i - 'a');
			for (int i = '0'; i <= '9'; i++)
				codes[i] = (byte) (52 + i - '0');
			codes['+'] = 62;
			codes['/'] = 63;
		}

		static public String encode(byte[] data) {
			char[] out = new char[((data.length + 2) / 3) * 4];
			for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
				boolean quad = false;
				boolean trip = false;
				int val = (0xFF & (int) data[i]);
				val <<= 8;
				if ((i + 1) < data.length) {
					val |= (0xFF & (int) data[i + 1]);
					trip = true;
				}
				val <<= 8;
				if ((i + 2) < data.length) {
					val |= (0xFF & (int) data[i + 2]);
					quad = true;
				}
				out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 1] = alphabet[val & 0x3F];
				val >>= 6;
				out[index + 0] = alphabet[val & 0x3F];
			}

			return new String(out);
		}

		static public byte[] decode(char[] data) {
			int len = ((data.length + 3) / 4) * 3;
			if (data.length > 0 && data[data.length - 1] == '=')
				--len;
			if (data.length > 1 && data[data.length - 2] == '=')
				--len;
			byte[] out = new byte[len];
			int shift = 0;
			int accum = 0;
			int index = 0;
			for (int ix = 0; ix < data.length; ix++) {
				int value = codes[data[ix] & 0xFF];
				if (value >= 0) {
					accum <<= 6;
					shift += 6;
					accum |= value;
					if (shift >= 8) {
						shift -= 8;
						out[index++] = (byte) ((accum >> shift) & 0xff);
					}
				}
			}
			if (index != out.length) {
				throw new Error("miscalculated data length!");
			}
			return out;
		}
	}
}
