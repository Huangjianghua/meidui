package com.meiduimall.application.mall.constant;

public class MallConstant {

	public static final String KEY_SIGN_CLIENT_ID = "sign.clientID";

	public static final String KEY_SIGN_KEY = "sign.key";

	/** 商品模块微服务主机名 */
	public static final String KEY_CATALOG_SERVICE_HOST = "catalog-service.host";

	/** 会员系统接入层务根地址 */
	public static final String ACCESS_MEMBER_BASE_URL = "/member/front_user_center/v1";

	/** 商品模块微服务根地址 */
	public static final String SERVICE_CATALOG_BASE_URL = "/mall/catalog-service/v1";
	
	public static final String MEMBER_SERVCIE = "/member/member_service/v1";

	public static final String CONTENT_TYPE = "Content-Type";

	/** form表单提交的Content-Type */
	public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";

	private MallConstant() {
	}
}
