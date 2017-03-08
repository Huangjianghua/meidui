package com.meidui.shortmsg.exception;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -1972925542520532318L;
	
	public ApiException(String e) {
		super(e);
	}

	public ApiException(Exception e) {
		super(e);
	}

}
