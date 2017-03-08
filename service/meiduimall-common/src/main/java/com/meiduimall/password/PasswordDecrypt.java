package com.meiduimall.password;

import org.springframework.beans.factory.FactoryBean;

import com.meiduimall.password.util.PassworkEncryptUtils;
import com.meiduimall.password.util.StringUtils;

/**
 * 加密工厂Bean。
 * 
 * @author 
 * @version 
 * @since 
 */
public class PasswordDecrypt implements FactoryBean<String>
{
	private String password;
	private String encKey = "6e63627a2437346c";

	public String getEncKey()
	{
		return encKey;
	}

	public void setEncKey(String encKey) throws Exception
	{
		if(encKey == null || encKey.length() % 2 != 0)
			throw new Exception("encrypt key length error !");

		this.encKey = encKey;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password) throws Exception
	{
		if (password == null || password.isEmpty())
		{
			this.password = password;
		}
		else
		{
			if(password.length() < 16 || password.length() % 8 != 0)
				throw new Exception("encrypt password length error !");
			
			this.password = new String(PassworkEncryptUtils.decrypt(StringUtils.hexToBytes(password), StringUtils.hexToBytes(encKey)));
		}
	}


	public String getObject() throws Exception
	{
		return password;
	}


	public Class<?> getObjectType()
	{
		return String.class;
	}


	public boolean isSingleton()
	{
		return false;
	}
}
