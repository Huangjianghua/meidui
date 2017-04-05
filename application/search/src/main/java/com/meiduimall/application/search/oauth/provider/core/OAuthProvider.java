/*
 * Copyright 2007 AOL, LLC.
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

package com.meiduimall.application.search.oauth.provider.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.meiduimall.application.search.oauth.OAuth;
import com.meiduimall.application.search.oauth.OAuthAccessor;
import com.meiduimall.application.search.oauth.OAuthConsumer;
import com.meiduimall.application.search.oauth.OAuthException;
import com.meiduimall.application.search.oauth.OAuthMessage;
import com.meiduimall.application.search.oauth.OAuthProblemException;
import com.meiduimall.application.search.oauth.OAuthValidator;
import com.meiduimall.application.search.oauth.SimpleOAuthValidator;
import com.meiduimall.application.search.oauth.server.OAuthServlet;
import com.meiduimall.application.search.utility.DBUtil;

/**
 * Utility methods for providers that store consumers, tokens and secrets in
 * local cache (HashSet). Consumer key is used as the name, and its credentials
 * are stored in HashSet.
 *
 */
public class OAuthProvider {

	public static final OAuthValidator VALIDATOR = new SimpleOAuthValidator();

	public static Map<String, OAuthConsumer> ALL_CONSUMERS = Collections
			.synchronizedMap(new HashMap<String, OAuthConsumer>(10));

	public static final Collection<OAuthAccessor> ALL_TOKENS = new HashSet<OAuthAccessor>();

	private static Properties consumerProperties = null;

	public static synchronized void loadConsumers(ServletConfig config)
			throws IOException {

		String sql = " select oauth_accessor_name,oauth_accessor_secret,oauth_consumer_key,oauth_callback,oauth_description from sysaccess_authorization where saa_is_valid =1 ";
		List<Map<String, Object>> list = DBUtil.queryBySql(sql);
		for (Map<String, Object> map : list) {
			
			String consumer_key = (String) map.get("oauth_consumer_key");
			// make sure it's key not additional properties
			if (!consumer_key.contains(".")) {
				String consumer_secret = map.get("oauth_accessor_secret").toString();
				if (consumer_secret != null) {
					Object desc = map.get("oauth_description");
					Object callBack = map.get("oauth_callback");
					String consumer_description =  desc==null?consumer_key+".Description":desc.toString();

					String consumer_callback_url = callBack==null?consumer_key+".CallBack":callBack.toString();
					// Create OAuthConsumer w/ key and secret
					OAuthConsumer consumer = new OAuthConsumer(
							consumer_callback_url, consumer_key,
							consumer_secret, null);
					consumer.setProperty("name", consumer_key);
					consumer.setProperty("description", consumer_description);
					ALL_CONSUMERS.put(consumer_key, consumer);
				}
			}

		}
		


	}

	public static synchronized OAuthConsumer getConsumer(
			OAuthMessage requestMessage) throws IOException,
			OAuthProblemException {

		OAuthConsumer consumer = null;
		// try to load from local cache if not throw exception
		String consumer_key = requestMessage.getConsumerKey();

		if (ALL_CONSUMERS != null |ALL_CONSUMERS.size()>0) {
			consumer = ALL_CONSUMERS.get(consumer_key);
		} 
		
		if (consumer == null) {
			OAuthProblemException problem = new OAuthProblemException(
					"token_rejected");
			throw problem;
		}

		return consumer;
	}

	/**
	 * Get the access token and token secret for the given oauth_token.
	 */
	public static synchronized OAuthAccessor getAccessor(
			OAuthMessage requestMessage) throws IOException,
			OAuthProblemException {

		// try to load from local cache if not throw exception
		String consumer_token = requestMessage.getToken();
		OAuthAccessor accessor = null;
		for (OAuthAccessor a : OAuthProvider.ALL_TOKENS) {
			if (a.requestToken != null) {
				if (a.requestToken.equals(consumer_token)) {
					accessor = a;
					break;
				}
			} else if (a.accessToken != null) {
				if (a.accessToken.equals(consumer_token)) {
					accessor = a;
					break;
				}
			}
		}

		if (accessor == null) {
			OAuthProblemException problem = new OAuthProblemException(
					"token_expired");
			throw problem;
		}

		return accessor;
	}

	/**
	 * Set the access token
	 */
	public static synchronized void markAsAuthorized(OAuthAccessor accessor,
			String userId) throws OAuthException {

		// first remove the accessor from cache
		ALL_TOKENS.remove(accessor);

		accessor.setProperty(OAuth.USER_ID, userId);
		accessor.setProperty(OAuth.OAUTH_AUTHORIZED, Boolean.TRUE);

		// update token in local cache
		ALL_TOKENS.add(accessor);
	}

	/**
	 * Set the access token is null
	 */
	public static synchronized void markAsUnAuthorized(OAuthAccessor accessor)
			throws OAuthException {

		// first remove the accessor from cache
		ALL_TOKENS.remove(accessor);

	}

	/**
	 * Generate a fresh request token and secret for a consumer.
	 * 
	 * @throws OAuthException
	 */
	public static synchronized void generateRequestToken(OAuthAccessor accessor)
			throws OAuthException {

		// generate oauth_token and oauth_secret
		String consumer_key = (String) accessor.consumer.getProperty("name");
		// generate token and secret based on consumer_key

		// for now use md5 of name + current time as token
		String token_data = consumer_key + System.nanoTime();
		String token = DigestUtils.md5Hex(token_data);
		// for now use md5 of name + current time + token as secret
		String secret_data = consumer_key + System.nanoTime() + token;
		String secret = DigestUtils.md5Hex(secret_data);

		accessor.requestToken = token;
		accessor.tokenSecret = secret;
		accessor.accessToken = null;

		// add to the local cache
		ALL_TOKENS.add(accessor);

	}

	/**
	 * Generate a fresh request token and secret for a consumer.
	 * 
	 * @throws OAuthException
	 */
	public static synchronized void generateAccessToken(OAuthAccessor accessor)
			throws OAuthException {

		// generate oauth_token and oauth_secret
		String consumer_key = (String) accessor.consumer.getProperty("name");
		// generate token and secret based on consumer_key

		// for now use md5 of name + current time as token
		String token_data = consumer_key + System.nanoTime();
		String token = DigestUtils.md5Hex(token_data);
		// first remove the accessor from cache
		ALL_TOKENS.remove(accessor);

		accessor.requestToken = null;
		accessor.accessToken = token;

		// update token in local cache
		ALL_TOKENS.add(accessor);
	}

	public static void handleException(Exception e, HttpServletRequest request,
			HttpServletResponse response, boolean sendBody) throws IOException,
			ServletException {
		String realm = (request.isSecure()) ? "https://" : "http://";
		realm += request.getLocalName();
		OAuthServlet.handleException(response, e, realm, sendBody);
	}

}
