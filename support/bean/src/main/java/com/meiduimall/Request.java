package com.meiduimall;

public class Request {
	
	public String check(){
		return ApiParamCheckUtil.checkParam(this);
	}
}
