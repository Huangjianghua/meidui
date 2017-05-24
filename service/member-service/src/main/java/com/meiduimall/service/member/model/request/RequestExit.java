package com.meiduimall.service.member.model.request;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登出请求映射实体
 * @author chencong
 *
 */
public class RequestExit  implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="令牌不能为空")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
