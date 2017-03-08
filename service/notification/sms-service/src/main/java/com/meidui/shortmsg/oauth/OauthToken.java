package com.meidui.shortmsg.oauth;

import com.alibaba.fastjson.JSONObject;
import com.meidui.shortmsg.util.Logger;
import com.meidui.shortmsg.util.MD5Util;

/**
 * Title : Token 
 * Description : TokenTools
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月15日 下午3:38:29 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public class OauthToken {

	/**
	 * Description : 通过用户名、密码、时间戳换取Token
	 * Token = MD5(username&password&tomesatamp);
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午3:35:02 
	 * 
	 * @param username
	 * @param password
	 * @param timesatamp
	 * @return
	 */
	public static String createToken(String username, String password, Long timesatamp) throws Exception{
		StringBuffer buffer = new StringBuffer();
		buffer.append(username);
		buffer.append("&");
		buffer.append(password);
		buffer.append("&");
		buffer.append(timesatamp);
		return MD5Util.MD5EncryptBy32(buffer.toString());
	}
	
	/**
	 * Description : 验证token
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午4:59:47 
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validateToken(String token) throws Exception{
		JSONObject json = new JSONObject();
		json.put("errcode", 0);
		Logger.info("access valToken");
		return json;
	}
}
