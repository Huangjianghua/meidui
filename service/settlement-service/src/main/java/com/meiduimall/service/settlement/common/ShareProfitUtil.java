package com.meiduimall.service.settlement.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.github.pagehelper.StringUtil;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.meiduimall.core.Constants;
import com.meiduimall.service.settlement.context.MemberSystemDataContext;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.util.DateUtil;
import com.meiduimall.service.settlement.vo.EcmMzfBillWaterVO;
import com.meiduimall.service.settlement.vo.ShareProfitVO;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ShareProfitUtil.java
 * Author:   许彦雄
 * Date:     2017年3月15日 下午6:15:47
 * Description: 分润工具类
 */
public class ShareProfitUtil {
	
	private static final Logger log=LoggerFactory.getLogger(ShareProfitUtil.class);
	
	//大区个代提成比例
	public final static String PERSONAL_SCALE_FOR_BIG_REGION = "person_shareprofit_rate_for_big_region";
	
	//个代提成比例
	public final static String PERSONAL_SCALE = "person_shareprofit_rate";
	
	//区代提成比例
	public final static String AREA_SCALE = "area_shareprofit_rate";
	
	//跨区个代提成比例
	public final static String CROSS_PERSONAL_SCALE = "outperson_shareprofit_rate";
	
	//跨区区代提成比例
	public final static String CROSS_AREA_SCALE = "outarea_shareprofit_rate";
	
	//前200区代提成
	public final static String TWO_AREA_SCALE = "two_hundred_area_shareprofit_rate";
	
	//推荐人积分提成
	public final static String BELONG_SCALE = "belong_rate";
	
	//一级推荐人所获现金比例
	public final static String FIRST_REFERRER_CASH_RATE = "first_referrer_cash_rate";
	
	//商家所获积分提成
	public final static String SELLER_POINT_RATE = "seller_point_rate";
	
	//接收短信的手机号码
	public final static String SMS_PHONES = "sms.phones";
	public final static String CLIENT_ID = "clientID";

	public final static String TEMPLATE_ID_O2O_1009="O2O_1009";
	public final static String TEMPLATE_ID_O2O_1008 = "O2O_1008";
	
	//请求方式_get
	public final static String REQUEST_METHOD_GET = "GET";
	
	//请求方式_post
	public final static String REQUEST_METHOD_POST = "POST";
	
	//加载鉴权配置文件
	//public final static Map<String, String> AUTHORIZED_MAP = ToolUtils.loadProperty("config/authorized.properties");
	public static Map<String, String> AUTHORIZED_MAP =null;

	
	//直营前缀编码
	public static final String CODE_DIRECT_SALE="888888";
	
	//大区前缀编码
	public static final String CODE_BIG_REGION="999999";
	
	//直营个代(888888开头)
	public static final String PERSONAL_AGENT_TYPE_DIRECT_SALE="directSale";
	
	//大区个代(999999开头)
	public static final String PERSONAL_AGENT_TYPE_BIG_REGION="bigRegion";
	
	//(普通个代类型)
	public static final String PERSONAL_AGENT_TYPE_NORMAL="normal";
	
	public static final Map<Integer,String> O2O_SETTLEMENT_STATUS_CODE_MAP=ImmutableMap.of(ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_SCORE,"积分已送出",
			ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_BILL,"订单已结算",ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_CASH,"一级推荐人现金奖励已送出");

	//接口参数url拼接
	public static String getBelongInfoUrl(String phone) {
		String timeStamp = String.valueOf(System.currentTimeMillis() / 1000L);
		String nonce = getRandomNum();
		String belongInfoUrl = AUTHORIZED_MAP.get("authorized.url") 
				+ AUTHORIZED_MAP.get("authorized.belong")
				+ "?oauth_signature_method=" + AUTHORIZED_MAP.get("authorized.oauth_signature_method")
				+ "&oauth_accessor_secret=" + AUTHORIZED_MAP.get("authorized.oauth_accessor_secret") 
				+ "&oauth_consumer_key=" + AUTHORIZED_MAP.get("authorized.oauth_consumer_key")
				+ "&oauth_timestamp=" + timeStamp 
				+ "&oauth_nonce=" + nonce 
				+ "&oauth_version=" + AUTHORIZED_MAP.get("authorized.oauth_version")
				+ "&oauth_signature=" + oauthSignature(phone, timeStamp, nonce) 
				+ "&share_man=" + phone;
		return belongInfoUrl;
	}
	
	//MD5加密串拼接
	public static String oauthSignature(String phone, String timeStamp, String nonce) {
		String md5Str = "get&" + AUTHORIZED_MAP.get("authorized.url") 
				+ AUTHORIZED_MAP.get("authorized.belong")
				+ "&oauth_signature_method=" + AUTHORIZED_MAP.get("authorized.oauth_signature_method") 
				+ "&oauth_accessor_secret=" + AUTHORIZED_MAP.get("authorized.oauth_accessor_secret")
				+ "&oauth_consumer_key=" + AUTHORIZED_MAP.get("authorized.oauth_consumer_key") 
				+ "&oauth_timestamp=" + timeStamp 
				+ "&oauth_nonce=" + nonce 
				+ "&oauth_version=" + AUTHORIZED_MAP.get("authorized.oauth_version") 
				+ "&share_man=" + phone;
		return MD5Encrypt(encodeStr(md5Str, "UTF-8"));
	}
	
	
	
	/** 支付方式 */
	public final static String PAY_WECHAT = "pay_wechat";
	public final static String PAY_ALIPAY = "pay_alipay";
	public final static String PAY_CMB = "pay_cmb";
	public final static String PAY_POS = "pay_pos";
	
	/**
	 * Description : 查询分润数据配置
	 * Created By : Fkx 
	 * Creation Time : 2016-10-19 上午10:58:37 
	 * 
	 * @return
	 */
	public static Map<String, String> queryShareProfit(List<EcmSystemSetting> systemSettings) {
		Map<String, String> ShareProfit = new HashMap<String, String>();
		try {
			for (EcmSystemSetting systemSetting : systemSettings) {
				ShareProfit.put(systemSetting.getScode(), systemSetting.getValue());
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return ShareProfit;
	}
	
	
	//接口参数url拼接
	public static String belongInfoUrl(Map<String, String> map) {
			String timeStamp = String.valueOf(System.currentTimeMillis() / 1000L);
			String nonce = getRandomNum();
			map.put("timeStamp", timeStamp);
			map.put("nonce", nonce);
			
			String url =  map.get("url")!=null?map.get("url"):"";
			String user_id =  map.get("user_id")!=null?map.get("user_id"):"";
			String consume_points_count =  map.get("consume_points_count")!=null?map.get("consume_points_count"):"";
			String order_source =  map.get("order_source")!=null?map.get("order_source"):"";
			String order_id =  map.get("order_id")!=null?map.get("order_id"):"";
			
			String belongInfoUrl = AUTHORIZED_MAP.get("authorized.url")+url;
			 
			String belongInfo = "oauth_signature_method=" + AUTHORIZED_MAP.get("authorized.oauth_signature_method")
					+ "&oauth_accessor_secret=" + AUTHORIZED_MAP.get("authorized.oauth_accessor_secret") 
					+ "&oauth_consumer_key=" + AUTHORIZED_MAP.get("authorized.oauth_consumer_key")
					+ "&oauth_timestamp=" + timeStamp 
					+ "&oauth_nonce=" + nonce 
					+ "&oauth_version=" + AUTHORIZED_MAP.get("authorized.oauth_version");
					
			
			String belongInfoend = "&user_id=" + user_id
					+ "&consume_points_count=" + consume_points_count
					+ "&order_source=" + order_source
			        + "&order_id=" + order_id;
					
			String oauth_signature  = "&oauth_signature=" + MD5Encrypt(encodeStr("get&"+belongInfoUrl+"&"+belongInfo+belongInfoend, "UTF-8")); 
			
			return belongInfoUrl+"?"+belongInfo+oauth_signature+belongInfoend;
	}
	
	public static String getPersonalAgentType(String personalAgentNo){
		if(Strings.isNullOrEmpty(personalAgentNo)){
			return PERSONAL_AGENT_TYPE_NORMAL;
		}else{
			String prefix6=personalAgentNo.trim().substring(0, 6);
			if(CODE_DIRECT_SALE.equals(prefix6)){
				return PERSONAL_AGENT_TYPE_DIRECT_SALE;
			}else if(CODE_BIG_REGION.equals(prefix6)){
				return PERSONAL_AGENT_TYPE_BIG_REGION;
			}else{
				return PERSONAL_AGENT_TYPE_NORMAL;
			}
		}
		
	}

		//构建更新一级推荐人所获现金余额到会员系统接口的URL以及请求参数
		public static String buildMemberSystemAmoutUrl(MemberSystemDataContext ctx) {
			
			String userId = ctx.getUserId();

			String signatureMethod=AUTHORIZED_MAP.get(MemberSystemDataContext.KEY_AUTHORIZED_OAUTH_SIGNATURE_METHOD);
			String accessorSecret=AUTHORIZED_MAP.get(MemberSystemDataContext.KEY_AUTHORIZED_OAUTH_ACCESS_SECRET);
			String consumerKey=AUTHORIZED_MAP.get(MemberSystemDataContext.KEY_AUTHORIZED_OAUTH_CONSUMER_KEY);
			String apiDomain=AUTHORIZED_MAP.get(MemberSystemDataContext.KEY_AUTHORIZED_DOMAIN);
			String apiPath=AUTHORIZED_MAP.get(MemberSystemDataContext.KEY_AUTHORIZED_API_UPD_FIRST_REFERRER_CASH);
			String version=AUTHORIZED_MAP.get(MemberSystemDataContext.KEY_AUTHORIZED_OAUTH_VERSION);
			
			String timeStamp = String.valueOf(System.currentTimeMillis() / 1000L);
			String nonce = getRandomNum();
			String source=ctx.getSource();
			String orderId=ctx.getOrderSn();
			String tradeType=ctx.getTradeType();
			String tradeAmount=ctx.getAmount().toString();
			String direction=ctx.getDirection();
			String tradeTime=String.valueOf(ctx.getTradeTime());
			String remark=ctx.getRemark();
			String remarkEncoded=encodeStr(remark,Constants.ENCODE_UTF8);
				
			String api = apiDomain+apiPath;
			 
			String oauthParams = "oauth_signature_method=" + signatureMethod
					+ "&oauth_accessor_secret=" + accessorSecret 
					+ "&oauth_consumer_key=" + consumerKey
					+ "&oauth_timestamp=" + timeStamp 
					+ "&oauth_nonce=" + nonce 
					+ "&oauth_version=" + version;
			
			String dataParams = "&user_id=" + userId
					+ "&source=" + source
					+ "&trade_type=" + tradeType
			        + "&order_id=" + orderId 
			        + "&direction=" + direction 
			        + "&trade_amount=" + tradeAmount 
			        + "&trade_time=" + tradeTime
			        + "&remark=" + remarkEncoded;
			
			String dataParams4Sign = "&user_id=" + userId
					+ "&source=" + source
					+ "&trade_type=" + tradeType
			        + "&order_id=" + orderId 
			        + "&direction=" + direction 
			        + "&trade_amount=" + tradeAmount 
			        + "&trade_time=" + tradeTime
			        + "&remark=" + remark;
					
			String signature = "&oauth_signature=" + MD5Encrypt(encodeStr("get&"+api+"&"+oauthParams+dataParams4Sign, Constants.ENCODE_UTF8)); 
			
			return api+"?"+oauthParams+signature+dataParams;  //不嫩直接将中文字符的remark字段传到请求参数，不然API服务器那边接收到时会出现乱码。
		}

		public static EcmMzfOrderStatus buildOrderStatusObj(EcmMzfShareProfit ecmMzfShareProfit) {
			EcmMzfOrderStatus orderStatus=new EcmMzfOrderStatus();
			orderStatus.setOrderSn(ecmMzfShareProfit.getOrderSn());
			orderStatus.setStatus(ecmMzfShareProfit.getStatus());
			orderStatus.setAddTime(ecmMzfShareProfit.getOrderDate());
			orderStatus.setPayTime(ecmMzfShareProfit.getPayTime());
			orderStatus.setCreatedDate(DateUtil.getCurrentTimeSec());
			orderStatus.setShareStatus(1);  //1:已分润
			return orderStatus; 
		}

		public static BigDecimal getShareProfitByType(String roleType, List<ShareProfitVO> shareProfitVOs,String profitType) {
			BigDecimal value=null;
			if(shareProfitVOs!=null && !shareProfitVOs.isEmpty() && !StringUtil.isEmpty(roleType)){
				Collection<ShareProfitVO> col=Collections2.filter(shareProfitVOs, new Predicate<ShareProfitVO>(){
					
					@Override
					public boolean apply(ShareProfitVO soVo) {
						return roleType.equals(soVo.getType());
					}});
				
				if(col!=null){
					final List<ShareProfitVO> list=new ArrayList(col);
					ShareProfitVO spVO=list.get(0);
					if(spVO!=null){
						if("Today".equals(profitType)){
							return spVO.getProfitToday();
						}
						
						if("Settlement".equals(profitType)){
							return spVO.getProfit4Settlement();
						}
					}
				}

			}
			return value;
			
			
		}


		public static void updateBillInfo(List<EcmMzfShareProfit> shareProfits, List<EcmMzfBillWaterVO> billWaterVOs) {
			if(shareProfits!=null && !shareProfits.isEmpty() && billWaterVOs!=null && !billWaterVOs.isEmpty()){
				Map<String, EcmMzfShareProfit> shareProfitMap=SettlementUtil.convert2Map(shareProfits, "orderSn");
				Map<String, EcmMzfBillWaterVO> billWaterMap=SettlementUtil.convert2Map(billWaterVOs, "orderSn");
				for(Map.Entry<String, EcmMzfShareProfit> entry:shareProfitMap.entrySet()){
					String orderSn=entry.getKey();
					if(billWaterMap.get(orderSn)!=null){
						EcmMzfBillWaterVO bwVO=billWaterMap.get(orderSn);
						entry.getValue().setBillDate(bwVO.getBillDate());
						entry.getValue().setBillAddDate(bwVO.getBillAddDate());
						entry.getValue().setBillDateStr(DateUtil.format(bwVO.getBillDate(), DateUtil.YYYY_MM_DD));
						entry.getValue().setBillAddDateStr(DateUtil.format(bwVO.getBillAddDate(), DateUtil.YYYY_MM_DD));
						
					}
				}
				
			}
		}

		/**
		 * Description : 获取请求接口后的数据提取推荐人手机号
		 * Created By : Fkx 
		 * Creation Time : 2016-10-27 下午5:31:00 
		 * 
		 * @param arrStr
		 * @return
		 */
		public static Map<String, String> getlvlAndPhone(List<Map<String, String>> list) {
			Map<String, String> retMap = new HashMap<String, String>();
			if(list!=null && !list.isEmpty()){
				for(Map<String,String> referrerMap:list){
					retMap.put(referrerMap.get("level"), referrerMap.get("phone"));
				}
			}
			return retMap;
		}
		
		
		public static String MD5Encrypt(String values) {
			StringBuilder buf = new StringBuilder("");
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(values.toLowerCase().getBytes());
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
				log.error(e.getMessage(),e);
			}
			return buf.toString();
		}
		
		/**
		 * 描述:  生成不重复随机数，生成方式：毫秒+5位随机数<br>
		 * @return
		 */
		public final static String getRandomNum() {
			
			//当前秒数
			String timeMillis = String.valueOf(System.currentTimeMillis()/1000L);
			
			String newString = null;
			// 得到0.0到1.0之间的数字,并扩大100000倍
			double doubleP = Math.random() * 100000;
			// 如果数据等于100000,则减少1
			if (doubleP >= 100000) {
				doubleP = 99999;
			}
			// 然后把这个数字转化为不包含小数点的整数
			int tempString = (int) Math.ceil(doubleP);
			// 转化为字符串
			newString = "" + tempString;
			// 把得到的数增加为固定长度,为5位
			while (newString.length() < 5) {
				newString = "0" + newString;
			}

			return (timeMillis + newString);
		}
		
		/**
		 * Description : 加载配置文件
		 * Created By : Fkx 
		 * Creation Time : 2016-10-28 上午11:34:07 
		 * 
		 * @param config
		 * @return
		 */
		public static Map<String, String> loadProperty(String config) {
			InputStream is = null;
			Map<String, String> map = new HashMap<String, String>();
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
				Properties pro = new Properties();
				pro.load(is);
				Iterator<Object> localIterator = pro.keySet().iterator();
				while (localIterator.hasNext()) {
					Object key = localIterator.next();
					map.put(key.toString(), pro.get(key).toString());
				}
			} catch (Exception ex) {
				log.error("配置文件:{},加载出错!error:{}",config,ex.getMessage());
			} finally {
				try {
					if (is != null)
					is.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
			return map;
		}
		
		/**
		 * @param str
		 * @param charset
		 * @return
		 * @author alex.xu
		 */
		public static String encodeStr(String str,String charset){
			
			String encodestr = "";
			String defaultCharSet=Constants.ENCODE_UTF8;
			if(!Strings.isNullOrEmpty(charset)){
				defaultCharSet=charset;
			}
			
			try {
				if(!Strings.isNullOrEmpty(str)){
					encodestr = URLEncoder.encode(str, defaultCharSet);
				}
			} catch (UnsupportedEncodingException e) {
				log.error("{}",e.getMessage());
			}
			return encodestr;
		}

	 
}