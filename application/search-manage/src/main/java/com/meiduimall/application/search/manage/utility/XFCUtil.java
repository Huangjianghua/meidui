package com.meiduimall.application.search.manage.utility;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.meiduimall.application.search.manage.constant.Constant;
import com.meiduimall.application.search.manage.oauth.OAuth;
import com.meiduimall.application.search.manage.oauth.OAuthMessage;
import com.meiduimall.application.search.manage.oauth.OAuth.Parameter;
import com.meiduimall.application.search.manage.oauth.signature.OAuthSignatureMethod;

public class XFCUtil {

	private static Logger log = Logger.getLogger(XFCUtil.class);

	public static double getxfc() {
		try {
			JSONObject json = new JSONObject();
			// 传参
    		json.put("oauth_accessor_secret", Constant.FIRST_XFC_SECRET );
    		json.put("oauth_consumer_key",  Constant.FIRST_XFC_KEY );
    		json.put("oauth_signature_method", Constant.OAUTH_SIGNATURE_METHOD);
    		json.put("oauth_version", Constant.OAUTH_VERSION);
    		json.put("oauth_timestamp", getCurrentTimestamp());
    		json.put("oauth_nonce", randOauthNonce());
    		
    		String url = Constant.FIRST_XFC_PRICE_URL;
    		List<Parameter> list = buildingBasicParameters(json);
    		OAuthMessage requestMessage = new OAuthMessage("GET",url, list);
	    	String oauthSingnature = OAuthSignatureMethod.getBaseString(requestMessage);
	    	
	    	// 加密
	    	json.put("oauth_signature", MD5Tool.MD5Encrypt(oauthSingnature));
	    	
	    	String result = HttpTooUtils.sendPost(url, getParams(json));
	    	result = new String(result.getBytes(), "utf-8");
	    	JSONObject obj = JSONObject.fromObject(result);
	    	if (obj.getInt("status_code") == 0 && obj.get("price") != null) {
	    		log.info("获取XFC价格成功！");
				log.info("当前XFC价格： " + obj.getDouble("price"));
				return obj.getDouble("price");
			}
		} catch (Exception e) {
			log.error("获取XFC价格异常", e);
		}
		return -1;
	}
	


	
    private static String getParams(JSONObject json) {
    	StringBuffer buff = new StringBuffer();
    	buff.append("oauth_signature_method=" + Constant.OAUTH_SIGNATURE_METHOD)
    	.append("&oauth_accessor_secret=" + Constant.FIRST_XFC_SECRET)
    	.append("&oauth_consumer_key=" +Constant.FIRST_XFC_KEY)
    	.append("&oauth_signature=" + json.get("oauth_signature"))
    	.append("&oauth_timestamp=" + json.get("oauth_timestamp"))
    	.append("&oauth_nonce=" + json.get("oauth_nonce"))
    	.append("&oauth_version=" + Constant.OAUTH_VERSION);
    	return buff.toString();
    }
    
    /***
     * 自动生成nonce
     * **/
    private static String randOauthNonce() {
    	return String.valueOf(Math.random()).substring(2,8);
    }
    
    private static String getCurrentTimestamp() {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	return String.valueOf(timestamp.getTime()).substring(0, 10);
    }
    
    //此方法应和文档外部接口退单接口参数保持一致
	private static List<OAuth.Parameter> buildingBasicParameters(JSONObject json) {
		List<OAuth.Parameter> list = new ArrayList<OAuth.Parameter>();
		list.add(new Parameter("oauth_signature_method", Constant.OAUTH_SIGNATURE_METHOD));
		list.add(new Parameter("oauth_accessor_secret",Constant.FIRST_XFC_SECRET));
		list.add(new Parameter("oauth_consumer_key",Constant.FIRST_XFC_KEY));
		list.add(new Parameter("oauth_timestamp", json.getString("oauth_timestamp")));
		list.add(new Parameter("oauth_nonce", json.getString("oauth_nonce")));
		list.add(new Parameter("oauth_version", Constant.OAUTH_VERSION));
		return list;
	}
}
