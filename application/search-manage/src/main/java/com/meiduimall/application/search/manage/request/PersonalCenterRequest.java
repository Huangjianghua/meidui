package com.meiduimall.application.search.manage.request;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class PersonalCenterRequest {
	
	@NotBlank(message="当前用户不存在")
	private String userId;
	
	
	@NotBlank(message="原始密码不能为空")
	private String oldPwd;
	
	
	@NotBlank(message="新密码不能为空")
	@Size(min=7, max=100,message="密码输入必须大于六位数")
	private String onePwd;
	
	@Size(min=7, max=100,message="密码输入必须大于六位数")
	private String twoPwd;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getOnePwd() {
		return onePwd;
	}
	public void setOnePwd(String onePwd) {
		this.onePwd = onePwd;
	}
	public String getTwoPwd() {
		return twoPwd;
	}
	public void setTwoPwd(String twoPwd) {
		this.twoPwd = twoPwd;
	}
	
	
	

}
