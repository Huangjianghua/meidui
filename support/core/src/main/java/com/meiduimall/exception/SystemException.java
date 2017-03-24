/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.exception;

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
