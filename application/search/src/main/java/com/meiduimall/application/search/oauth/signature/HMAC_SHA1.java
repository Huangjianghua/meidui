/*
 * Copyright 2007 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.meiduimall.application.search.oauth.signature;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.meiduimall.application.search.oauth.OAuth;
import com.meiduimall.application.search.oauth.OAuthException;
import com.meiduimall.application.search.utility.SHAUtil;

/**
 * The HMAC-SHA1 signature method.
 * 
 */
public class HMAC_SHA1 extends OAuthSignatureMethod {

    @Override
    protected String getSignature(String baseString) throws OAuthException {
        try {
        	 String keyString = getConsumerSecret();
             byte[] expected = SHAUtil.computeSignature(keyString, baseString);
        	String signature = base64Encode(expected);
            return signature;
        } catch (GeneralSecurityException e) {
            throw new OAuthException(e);
        } catch (UnsupportedEncodingException e) {
            throw new OAuthException(e);
        }
    }

    @Override
    protected boolean isValid(String signature, String baseString)
    throws OAuthException {
        try {
        	 String keyString = getConsumerSecret();
            byte[] expected = SHAUtil.computeSignature(keyString, baseString);
            byte[] first = base64Encode(expected).getBytes();
            byte[] actual = decodeBase64(signature);
            //byte[] actual = signature.getBytes();
            
            return equals(first, actual);
        } catch (GeneralSecurityException e) {
            throw new OAuthException(e);
        } catch (UnsupportedEncodingException e) {
            throw new OAuthException(e);
        }
    }

    public byte[] computeSignature(String baseString)
            throws GeneralSecurityException, UnsupportedEncodingException {
    	SecretKeySpec key = null;
       /* synchronized (this) {
            if (key == null) {
                String keyString = OAuth.decodePercent(getConsumerSecret());
                String tokenSecret = getTokenSecret();
                if ( tokenSecret!=null)
                {
                	keyString+='&' + OAuth.percentEncode(tokenSecret);
                }
               
                byte[] keyBytes = keyString.getBytes(ENCODING);
                key = new SecretKeySpec(keyBytes, MAC_NAME);
            }
        }*/
    	
    	//String data = "user_id%3dxxxxxxxxxxx%26oauth_signature_method%3dhmac-sha1%26oauth_nonce%3d779795";
    	
    	 String keyString = getConsumerSecret();
    	 byte[] keyBytes = keyString.getBytes(ENCODING);
         key = new SecretKeySpec(keyBytes, MAC_NAME);
    	
        Mac mac = Mac.getInstance(MAC_NAME);
        mac.init(key);
        byte[] text = baseString.getBytes(ENCODING);
        return mac.doFinal(text);
    }

    /** ISO-8859-1 or US-ASCII would work, too. */
    private static final String ENCODING = OAuth.ENCODING;

    private static final String MAC_NAME = "HmacSHA1";


    @Override
    public void setConsumerSecret(String consumerSecret) {
        
        super.setConsumerSecret(consumerSecret);
    }

    @Override
    public void setTokenSecret(String tokenSecret) {
       
        super.setTokenSecret(tokenSecret);
    }

}
