package com.meidui.shortmsg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import com.meidui.shortmsg.model.rtx.RtxRequestModel;
import com.meidui.shortmsg.util.HttpClientUtil;
import com.meidui.shortmsg.util.MD5Util;
import com.meidui.shortmsg.util.Time;

/**
 * 容聯
 * 
 * @author Nico.Jiang
 * @since 2016.12.16
 *
 */
@Service
public class RtxService {

	String RTX_SID = "8a48b5514e04a574014e09a83d4e03fd";
	String RTX_TOKEN = "c1a8406b7c454f97b39bb820e9e0a2e1";
	String RTX_APPID = "aaf98f89539b228f0153a7acfbd01739";

	String url_base = "https://app.cloopen.com:8883";
	String url_profile = "/2013-12-26/Accounts/" + RTX_SID + "/SMS/TemplateSMS?sig=";

	protected String getSign() throws Exception {
		String tmp = RTX_SID + RTX_TOKEN + Time.getTimestamp();
		return MD5Util.md5Digest(tmp);
	}

	public String Send(String mobile, String tid, String appid, List<String> data) throws Exception {

		String url = url_base + url_profile + getSign();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json;charset=utf-8");

		String authStr = MD5Util.base64Encoder(RTX_SID + ":" + Time.getTimestamp());
		headers.put("Authorization", authStr);

		RtxRequestModel model = new RtxRequestModel();
		model.setTo(mobile);
		model.setAppId(appid);
		model.setTemplateId(tid);
		model.setDatas(data);

		StringEntity entity = new StringEntity(model.toString(), "UTF-8");
		String result = HttpClientUtil.doPost(url, entity, headers);

		return result;
	}

}
