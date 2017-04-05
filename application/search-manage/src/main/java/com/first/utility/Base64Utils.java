package com.first.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {

	private static final Log LOGGER = LogFactory.getLog(Base64Utils.class);

	public static String getEnBASE64(byte[] b) throws Exception {
		if (b == null) {
			return null;
		}
		String result = new BASE64Encoder().encode(b);
		return result;
	}

	// 将 BASE64 编码的图片字符串进行解码
	public static boolean getDeBASE64(String s, String fileName) {
		if (s == null) {
			return false;
		}
		OutputStream out = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					// 调整异常数据
					b[i] += 256;
				}
			}

			out = new FileOutputStream(fileName);
			out.write(b);
			out.flush();
			return true;
		} catch (Exception e) {
			LOGGER.error("BASE64图片解码失败!", e);
			return false;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				LOGGER.error("关闭文件输出流失败！", e);
			}
		}
	}

	public static byte[] readFile(String filename) throws IOException {
		File file = new File(filename);
		if (filename == null || filename.equals("")) {
			throw new NullPointerException("无效的文件路径");
		}
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len) {
			bufferedInputStream.close();
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();

		return bytes;
	}

}
