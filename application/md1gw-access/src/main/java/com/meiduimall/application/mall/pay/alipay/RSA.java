package com.meiduimall.application.mall.pay.alipay;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.meiduimall.application.mall.util.Logger;


public class RSA {


	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	* RSA签名
	* @param content 待签名数据
	* @param privateKey 商户私钥
	* @param inputCharset 编码格式
	* @return 签名值
	*/
	public static String sign(String content, String privateKey, String inputCharset)
	{
	        try 
	        {
	        PKCS8EncodedKeySpec priPKCS8 
	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
	        KeyFactory keyf 
	= KeyFactory.getInstance("RSA");
	        PrivateKey priKey 
	= keyf.generatePrivate(priPKCS8);


	            java.security.Signature signature = java.security.Signature
	                .getInstance(SIGN_ALGORITHMS);


	            signature.initSign(priKey);
	            signature.update( content.getBytes(inputCharset) );


	            byte[] signed = signature.sign();
	            
	            return Base64.encode(signed);
	        }
	        catch (Exception e) 
	        {
	        	Logger.error("system error: %s",e);
	        }
	        
	        return null;
	    }

	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param alipayPublicKey 支付宝公钥
	* @param inputCharset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String alipayPublicKey, String inputCharset)
	{
	try 
	{
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	       byte[] encodedKey = Base64.decode(alipayPublicKey);
	       PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));



	java.security.Signature signature = java.security.Signature
	.getInstance(SIGN_ALGORITHMS);

	signature.initVerify(pubKey);
	signature.update( content.getBytes(inputCharset) );

	boolean bverify = signature.verify( Base64.decode(sign) );
	return bverify;

	} 
	catch (Exception e) 
	{
		Logger.error("system error: %s",e);
	}

	return false;
	}
}
