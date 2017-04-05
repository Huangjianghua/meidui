/**
 * 
 */
package com.first.utility;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author acer
 *
 */
public class SHAUtil {
	
	 private static final String MAC_NAME = "HmacSHA1";
	    private static final String ENCODING = "UTF-8";
	
	public static byte[] computeSignature(String secretKey,String baseString)
            throws GeneralSecurityException, UnsupportedEncodingException {
    	SecretKeySpec key = null;
    	 byte[] keyBytes = secretKey.getBytes(ENCODING);
         key = new SecretKeySpec(keyBytes, "");
    	
        Mac mac = Mac.getInstance(MAC_NAME);
        mac.init(key);
        byte[] text = baseString.getBytes(ENCODING);
        return mac.doFinal(text);
    }

}
