package com.meidui.shortmsg.service;

import com.meidui.shortmsg.entity.MessageChannel;

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
