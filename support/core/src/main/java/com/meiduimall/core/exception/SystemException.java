package com.meiduimall.core.exception;

public class SystemException extends RuntimeException {

	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = -6132660873946805356L;
	
	/**
     * 构造异常对象
     * @param msg
     */
    public SystemException(String msg) {
        super(msg);
    }

    /**
     * TradeSystemException
     * @param exception
     */
    public SystemException(Throwable exception) {
        super(exception);
    }

    /**
     * TradeSystemException
     * @param mag
     * @param exception
     */
    public SystemException(String mag, Exception exception) {
        super(mag, exception);
    }

}
