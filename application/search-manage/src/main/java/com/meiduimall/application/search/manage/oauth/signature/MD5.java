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

package com.meiduimall.application.search.manage.oauth.signature;

import com.meiduimall.application.search.manage.oauth.OAuthException;
import com.meiduimall.application.search.manage.utility.MD5Tool;

/**
 * The MD5(32bit) signature method.
 * 
 */
public class MD5 extends OAuthSignatureMethod {

    @Override
    public String getSignature(String baseString) {
        return getSignature();
    }

    @Override
    protected boolean isValid(String signature, String baseString)
            throws OAuthException {
    	
    	String strBaseString = MD5Tool.MD5Encrypt(baseString);

        return equals(strBaseString, signature);
    }

    private synchronized String getSignature() {
      
        return super.signature;
    }


    @Override
    public void setConsumerSecret(String consumerSecret) {
        synchronized (this) {
            signature = null;
        }
        super.setConsumerSecret(consumerSecret);
    }

    @Override
    public void setTokenSecret(String tokenSecret) {
        synchronized (this) {
            signature = null;
        }
        super.setTokenSecret(tokenSecret);
    }

}
