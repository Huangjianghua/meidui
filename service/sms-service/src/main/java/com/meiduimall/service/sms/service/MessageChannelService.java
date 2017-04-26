/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.service;

public interface MessageChannelService {

	/**
	 * 获取短信渠道并转成json字符串
	 * 
	 * @param key
	 *            短信渠道key
	 * @return 所有短信渠道组成的json串
	 */
	String getChannelList(String key);

}
