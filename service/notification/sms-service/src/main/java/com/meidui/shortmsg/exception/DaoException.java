package com.meidui.shortmsg.exception;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 7182136514266922537L;

	public DaoException(String e) {
		super(e);
	}
	
	public DaoException(Throwable cause) {
        super(cause);
    }
}
