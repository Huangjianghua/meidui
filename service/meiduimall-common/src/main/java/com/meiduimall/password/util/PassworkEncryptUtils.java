package com.meiduimall.password.util;

/**
 * 密码加解密工具类。
 * 
 * @author 
 * @version 
 * @since 
 */
public final class PassworkEncryptUtils
{
	/**
	 * 将指定的值进行加密。
	 * 
	 * @param value
	 *            待加密的值
	 * @param encKey
	 *            密钥
	 * @return 加密后的值
	 * @throws Exception 
	 */
	public static byte[] encrypt(byte[] value, byte[] encKey) throws Exception
	{
		if (value.length == 0)
		{
			return value;
		}
		
		return toByteArray(encrypt(toIntArray(value, true), toIntArray(encKey, false)), false);
	}

	/**
	 * 将指定的值进行解密。
	 * 
	 * @param value
	 *            待解密的值
	 * @param encKey
	 *            密钥
	 * @return 解密后的值
	 * @throws Exception 
	 */
	public static byte[] decrypt(byte[] value, byte[] encKey) throws Exception
	{
		if (value.length == 0)
		{
			return value;
		}
		
		return toByteArray(decrypt(toIntArray(value, false), toIntArray(encKey, false)), true);
	}

	/**
	 * 将指定的值进行加密。
	 * 
	 * @param value
	 *            待加密的值
	 * @param encKey
	 *            密钥
	 * @return 加密后的值
	 */
	public static int[] encrypt(int[] value, int[] encKey)
	{
		int n = value.length - 1;
		
		if (n < 1)
		{
			return value;
		}
		
		if (encKey.length < 4)
		{
			int[] key = new int[4];
			System.arraycopy(encKey, 0, key, 0, encKey.length);
			encKey = key;
		}
		
		int z = value[n], y = value[0], delta = 0x9E3779B9, sum = 0, e;
		int p, q = 6 + 52 / (n + 1);
		
		while (q-- > 0)
		{
			sum = sum + delta;
			e = sum >>> 2 & 3;
			
			for (p = 0; p < n; p++)
			{
				y = value[p + 1];
				z = value[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (encKey[p & 3 ^ e] ^ z);
			}
			
			y = value[0];
			z = value[n] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (encKey[p & 3 ^ e] ^ z);
		}
		
		return value;
	}

	/**
	 * 将指定的值进行解密。
	 * 
	 * @param value
	 *            待解密的值
	 * @param encKey
	 *            密钥
	 * @return 解密后的值
	 */
	public static int[] decrypt(int[] value, int[] encKey)
	{
		int n = value.length - 1;
		
		if (n < 1)
		{
			return value;
		}
		
		if (encKey.length < 4)
		{
			int[] key = new int[4];
			System.arraycopy(encKey, 0, key, 0, encKey.length);
			encKey = key;
		}
		
		int z = value[n], y = value[0], delta = 0x9E3779B9, sum, e;
		int p, q = 6 + 52 / (n + 1);
		
		sum = q * delta;
		
		while (sum != 0)
		{
			e = sum >>> 2 & 3;
		
			for (p = n; p > 0; p--)
			{
				z = value[p - 1];
				y = value[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (encKey[p & 3 ^ e] ^ z);
			}
			
			z = value[n];
			y = value[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (encKey[p & 3 ^ e] ^ z);
			
			sum = sum - delta;
		}
		
		return value;
	}

	// 将字节数组转换为整数数组
	private static int[] toIntArray(byte[] value, boolean includeLength)
	{
		int n = (((value.length & 3) == 0) ? (value.length >>> 2) : ((value.length >>> 2) + 1));
		int[] result;
		
		if (includeLength)
		{
			result = new int[n + 1];
			result[n] = value.length;
		}
		else
		{
			result = new int[n];
		}
		
		n = value.length;
		
		for (int i = 0; i < n; i++)
		{
			result[i >>> 2] |= (0x000000ff & value[i]) << ((i & 3) << 3);
		}
		
		return result;
	}

	// 将整数数组转换为字节数组
	private static byte[] toByteArray(int[] data, boolean includeLength) throws Exception
	{
		int n;
		
		if (includeLength)
		{
			n = data[data.length - 1];
		}
		else
		{
			n = data.length << 2;
		}

		if(n < 0)
			throw new Exception("data length is error !");
		
		byte[] result = new byte[n];
		
		for (int i = 0; i < n; i++)
		{
			result[i] = (byte) (data[i >>> 2] >>> ((i & 3) << 3));
		}
		
		return result;
	}
}
