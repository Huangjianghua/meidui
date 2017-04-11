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

import com.meiduimall.service.sms.entity.MessageChannel;

/**
 * 基础数据提供
 * @author pc
 *
 */
public interface IMessageChannelService {
	
	//public List<MessageChannel> getChannelList();
      
    public void put(MessageChannel channel);
  
    public void delete(MessageChannel channel);
  
    public String getChannelList(String key);

}
