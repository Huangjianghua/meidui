package com.meiduimall.application.usercenter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.core.ResBodyData;

/**
 * MD5工具类
 * @author chencong
 *
 */
public class MD5Utils {
	
	private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);
	
	/**
	 * 生成签名
	 * @param reqJson HTTP请求数据
	 * @param secretkey 密钥
	 * @return 统一返回数据格式
	 */
	public static ResBodyData getSign(JSONObject reqJson,String secretkey){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		String serverSign=null;
		String[] arr = new String[reqJson.containsKey(SysParamsConst.SIGN)?(reqJson.size()- 1):reqJson.size()];
		int i=0;
		try {
			for (String key: reqJson.keySet()) {
				if (SysParamsConst.SIGN.equals(key))
					continue;
				arr[i] = key+ SysParamsConst.EQUALS_SYMBOL +reqJson.getString(key);
				i++;
			}
			Arrays.sort(arr);
			StringBuffer buffer = new StringBuffer();
			for (int k = 0; k < arr.length; k++) {
				buffer.append(arr[k]);
				buffer.append(SysParamsConst.CONNECTION_SYMBOL);
			}
			buffer.append(SysParamsConst.KEY_LAST);
			buffer.append(secretkey);
			serverSign=MD5Utils.MD5EncryptBy32(buffer.toString()).toUpperCase();
			resBodyData.setData(serverSign);
		} catch (Exception e) {
			logger.error("exec getSign() error:{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.GET_SIGN_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.GET_SIGN_EX));
		}
		return resBodyData;
	}
	/**
	 * MD5 32位加密
	 * @param values 需要加密的字符串
	 * @return 加密后的32位字符串
	 * @throws Exception
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
			logger.error("exec MD5EncryptBy32() error:{}",e.getMessage());
			throw e;
		}
		return buf.toString();
	}
	
	public static ResBodyData updateSign(JSONObject reqJson,String clientID,String key){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,null);
		reqJson.put(SysParamsConst.CLIENTID,clientID);
		reqJson.put(SysParamsConst.TIMESATAMP,System.currentTimeMillis());
		resBodyData=MD5Utils.getSign(reqJson,key);
		if(resBodyData.getStatus()!=0){
			logger.error("接入层请求网关更新签名失败");
			return resBodyData;
		}
		String sign=resBodyData.getData().toString();
		reqJson.put(SysParamsConst.SIGN,sign);
		logger.info("接入层请求网关更新签名成功");
		return resBodyData;
	}
	
}