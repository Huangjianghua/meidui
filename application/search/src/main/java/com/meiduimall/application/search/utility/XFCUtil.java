package com.meiduimall.application.search.utility;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.meiduimall.application.search.cache.XFCSessionManager;
import com.meiduimall.application.search.constant.Constant;
import com.meiduimall.application.search.oauth.OAuth;
import com.meiduimall.application.search.oauth.OAuthMessage;
import com.meiduimall.application.search.oauth.OAuth.Parameter;
import com.meiduimall.application.search.oauth.signature.OAuthSignatureMethod;

public class XFCUtil {

	//初始化配置文件
	private static Map<String,String> propertiesList = null;
	
	private static Logger log = Logger.getLogger(XFCUtil.class);
	
	static {
		try {
			propertiesList = LoadPropertyUtil.getPropertyValues("config.properties");
		} catch (Exception e) {
			log.error("读取配置文件异常", e);
			e.printStackTrace();
		}
	}

	public static double getxfc() {
		try {
			JSONObject json = new JSONObject();
			// 传参
    		json.put("oauth_accessor_secret", propertiesList.get("first_xfc_secret"));
    		json.put("oauth_consumer_key", propertiesList.get("first_xfc_key"));
    		json.put("oauth_signature_method", Constant.OAUTH_SIGNATURE_METHOD);
    		json.put("oauth_version", Constant.OAUTH_VERSION);
    		json.put("oauth_timestamp", getCurrentTimestamp());
    		json.put("oauth_nonce", randOauthNonce());
    		
    		String url = propertiesList.get("first_xfc_price_url");
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
	
	public static double getXfcPrice() {
		try {
			double xfc = XFCUtil.getxfc();
			if (xfc > 0) {
				XFCSessionManager.put("xfc", xfc);
				return xfc;
			} else {
				int time = 30*1000;
				for (int i = 1;; i++) {
					// 5次以内30秒重新请求
					if (i <= 5) {
						
					} else if (i > 5 && i <= 10) {
						// 大于5次小于10次间隔1分钟
						time = 60*1000;
					} else if(i > 10 && i <= 20) {
						time = 2*60*1000;
					} else {
						time = 5*60*1000;
					}
					Thread.sleep(time);
					log.info("第" + i + "次重新获取xfc价格");
					xfc = XFCUtil.getxfc();
					if (xfc > 0) {
						XFCSessionManager.put("xfc", xfc);
						return xfc;
					}
				}
			}
		} catch (Exception e) {
			log.error("获取XFC价格异常", e);
		}
		return -1;
	}
    
	/**
	 * Description : 
	 * Created By : pjl 
	 * Creation Time : 2016年8月11日 下午12:12:25 
	 * 
	 * @param price		销售价
	 * @param costPrice 成本价
	 * @param rate		返利比率
	 * @return 
	 */
	public static double getItemXFC(double price, double costPrice, int rate) {
		return (price - costPrice) * (rate / 100.0) / new Double(XFCSessionManager.get("xfc").toString());
	}
	
    private static String getParams(JSONObject json) {
    	StringBuffer buff = new StringBuffer();
    	buff.append("oauth_signature_method=" + Constant.OAUTH_SIGNATURE_METHOD)
    	.append("&oauth_accessor_secret=" + propertiesList.get("first_xfc_secret"))
    	.append("&oauth_consumer_key=" + propertiesList.get("first_xfc_key"))
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
		list.add(new Parameter("oauth_accessor_secret", propertiesList.get("first_xfc_secret")));
		list.add(new Parameter("oauth_consumer_key", propertiesList.get("first_xfc_key")));
		list.add(new Parameter("oauth_timestamp", json.getString("oauth_timestamp")));
		list.add(new Parameter("oauth_nonce", json.getString("oauth_nonce")));
		list.add(new Parameter("oauth_version", Constant.OAUTH_VERSION));
		return list;
	}
}
