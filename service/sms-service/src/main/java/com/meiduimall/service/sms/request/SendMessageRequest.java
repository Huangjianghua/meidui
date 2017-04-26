/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 发送普通短信参数模板
 * 
 * @author pc
 *
 */
public class SendMessageRequest implements Serializable {

	private static final long serialVersionUID = 7996920400702100275L;

	// 手机号
	@NotNull
	private String phones;

	// 模板id
	private String templateKey;

	// 渠道编号
	private String channelId;

	// 替换短信中的参数
	private String params;

	// 短信过期时间，即timeout缓存保存时长：格式:3h, 2mn, 7s or combination 2d4h10s, 1w2d3h10s
	private String timeout;

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
}
