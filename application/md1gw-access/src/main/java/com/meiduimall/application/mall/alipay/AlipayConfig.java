package com.meiduimall.application.mall.alipay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 */
public class AlipayConfig {

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static final String partner = "2088421291650350";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static final String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMq5VTcNTQU8eRyX"
									  +"IZgwc0et8WoHOsZbz9Nveud8oFlmyktM2akh4tzcDEbzyswdqcv14w16t8ofkgS1"
									  +"69uarQs+emb21k1vr/WbzGStuHiditEiMmK+MQW+g+6HhwT3WXnPtq55v9102TN2"
									  +"OEvz2cycJvouxistd0JBxnOwJBZDAgMBAAECgYAURfiJquDa9zM9cre/WokM0A5m"
									  +"hhaQo7ni8TEfWU1H44mzghn6WhMv9ymbJuJPC2Bhr1Nnk7AxnHqj0fWmXo+TdLgx"
									  +"LYC5vUXux0meyiPMo9YzrBj6KmlE5Y2lctWDBSjCzJW8xyW//V3n8kDkMQclu2Ud"
									  +"lL1JdUGsC8Otc9gHYQJBAOdA81WOGG4jak+sqXcNcYqE+vBSDiNv87S5ZrmoUOjV"
									  +"f+XeJNJYDTklcejKYwDFtq7sSR8QC0F1IyV4fAQ9bzsCQQDgatPi1CZlykHREaqp"
									  +"U1ilYyGltAaqFnuf6sFIKzBR3Rzv1xQMZPTn9htibjR+tIPSFqPdXjtSJTmJhwac"
									  +"dBSZAkAD5Fz0EKsql1//rUlA3rINgcVza/4e7JBudzxaXZHXCVjYto9qFt9KGe47"
									  +"28QkzC8R7JoicrOty1nnFIsJyzrnAkANnI0jeeimTeE2FBM81/BNf7i/uy6YTGrN"
									  +"JUj53py8qmOh7HKr2ce89gFtM+/9K2e/a14fBCMm9VkLDWSKQsQpAkEAiBxdnnuz"
									  +"sFtnBN66Maw9LZYqu1f+kDoasHK01W4WRA8JKJIJhwDnmD6edznxRZZTvI3qpC1A"
									  +"rnVCID0vf1ZiHg==";

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static final String seller_id = "gcmepay@qq.com" ;

	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static final String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRA"
										 	 +"FljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQE"
											 +"B/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5Ksi"
											 +"NG9zpgmLCUYuLkxpLQIDAQAB";

	// 调试用，创建TXT日志文件夹路径
	public static final String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static final String input_charset = "utf-8";

	// 签名方式 不需修改
	public static final String sign_type = "RSA";

	// 支付类型 ，无需修改
	public static final String payment_type = "1";

	// 调用的接口名，无需修改
	public static final String service = "create_direct_pay_by_user";

	public static final String notify_url = "http://xxxxxxxx/xxxxxxx/notify/payNotify" ;
}
