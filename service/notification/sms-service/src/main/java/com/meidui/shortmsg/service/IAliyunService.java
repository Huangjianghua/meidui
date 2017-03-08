package com.meidui.shortmsg.service;

import com.meidui.shortmsg.exception.ApiException;

public interface IAliyunService {
	public boolean Send(String mobile, String tid, String context) throws ApiException;

}
