package com.meiduimall.application.mall.constant;

public class PayConfig {

	/**wxpayjsapi微信，appAlipay 支付宝***/
	public static final String WXPAYTYPE = "wxpayjsapi";
	public static final String ALIPAYTYPE = "appAlipay";
	
	
	//华容支付
	public static final String HUARONG_MERNO = "00000000000167"; //商户ID
	public static final String HUARONG_MD5KEY = "8fDfdty8zle6";  //MD5key值
	public static final Boolean HUARONG_IS_TEST = false;  //是否为华容测试环境
      

	 //支付宝
	public static final String ALIPAY_MER_ID = "2088421291650350"; //合作者身份(parterID)
	public static final String ALIPAY_MER_KEY = "tsv3nifvv449u3x910qryofh1rs09bxg"; //交易安全校验码(key)
	public static final String ALIPAY_REAL_METHOD = "1"; //选择接口类型  0:使用标准双接口 1:使用即时到帐交易接口 2:使用担保交易接口
 
	//H5版支付宝
	public static final String H5ALIPAY_MER_ID = "2088421291650350"; //合作者身份(parterID)
	public static final String H5ALIPAY_MER_KEY = "tsv3nifvv449u3x910qryofh1rs09bxg"; //交易安全校验码(key)
	public static final String H5ALIPAY_SELLER_ACCOUNT_NAME = "gcmepay@qq.com"; //支付宝账号
	public static final String USERPAYKEY = "{liangping}";
  
 
	//app调用支付宝
	public static final String APPALIPAY_MER_ID = "2088421291650350"; //合作者身份(parterID)
	public static final String APPALIPAY_MER_KEY = "tsv3nifvv449u3x910qryofh1rs09bxg"; //交易安全校验码(key)
	public static final String APPALIPAY_SELLER_ID = "gcmepay@qq.com"; //支付宝账号
	public static final String APPALIPAY_PRIVATE_KEY_PATH = "";  
	public static final String APPALIPAY_ALI_PUBLIC_KEY_PATH = "";  
	public static final String APPALIPAY_RSA_PUBLIC_KEY_PATH = "";  
	public static final String APPALIPAY_SIGN_TYPE = "";  
       
//        ''    => CONFIG_DIR.'/appAlipay/rsa_private_key.pem',
//        'ali_public_key_path'    => CONFIG_DIR.'/appAlipay/alipay_public_key.pem',
//        'rsa_public_key_path'    => CONFIG_DIR.'/appAlipay/rsa_public_key.pem',
//        'sign_type'    => strtoupper('RSA'),
//        'input_charset'    => strtolower('utf-8'),
//        'cacert'    => CONFIG_DIR.'/appAlipay/cacert.pem',
//        'transport'    => 'http',
//        'notify_url'    => in_array($_SERVER['HTTP_HOST'],array('md.1gw.com')) ? 'http://md.1gw.com/wap/appPay.html' : 'http://md.meiduimall.com/wap/appPay.html',//回调地址



	//壹购物配置
	/**  壹购物微信支付App调用配置 **/
	public static final String YGWAPP_APPID = "wxcb7abeba68f629cf"; //绑定支付的APPID
	public static final String YGWAPP_MCHID = "1373207902"; //商户号
	public static final String YGWAPP_KEY = "ifi2454kisuJKHFS33Rjh1jk2NASj30c"; //商户支付密钥
	public static final String YGWAPP_NOTIFY_URL = "http://md.1gw.com/wap/appjsapi.html"; //回调地址
	
	/** 壹购物微信支付 PC和H5版配置 **/
	public static final String YGWPCH5_APPID = "wx7d4edc8f56ba0c9c";  //绑定支付的APPID
	public static final String YGWPCH5_MCHID = "1249907601";   //商户号
	public static final String YGWPCH5_KEY = "PEI0K375TXDlAgZVR1NMUqOBS49JW2H6";   //商户支付密钥
	public static final String YGWPCH5_APPSECRET = "92807d5013337d122c0e67e34027791d";  //公众帐号secert    仅JSAPI支付的时候需要配置
	
	
	//美兑配置
	/**  美兑微信支付App调用配置  **/
	public static final String MDAPP_APPID = "wxe7819d4cbd2901cc"; //绑定支付的APPID
	public static final String MDAPP_KEY = "505CC1DF73DBA1E7145881BD3D30903B"; //商户支付密钥
	public static final String MDAPP_MCHID = "1386826202"; //商户号
	public static final String MDAPP_NOTIFY_URL = "http://md.meiduimall.com/wap/appjsapi.html"; //回调地址


	//美兑配置
	/** 美兑微信支付 PC和H5版配置  **/
	public static final String MDPCH5_APPID = "wxa2a3877c5fefc819"; //绑定支付的APPID
	public static final String MDPCH5_KEY = "505CC1DF73DBA1E7145881BD3D30903B"; //商户支付密钥
	public static final String MDPCH5_MCHID = "1383531102"; //商户号
	public static final String MDPCH5_APPSECRET = "310808443b7734a8979f950dad7c05b8"; //公众帐号secert 仅JSAPI支付的时候需要配置
	
	
	
	
	
}
