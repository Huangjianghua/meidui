package com.meidui.shortmsg.model.rtx;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;

public class RtxRequestModel implements Serializable {

	private static final long serialVersionUID = -95409425646086780L;

	private String to;
	private String appId;
	private String templateId;
	private List<String> datas;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public List<String> getDatas() {
		return datas;
	}

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
