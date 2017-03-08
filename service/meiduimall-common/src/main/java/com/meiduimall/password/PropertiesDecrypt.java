package com.meiduimall.password;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import static com.meiduimall.password.util.StringUtils.hexToBytes;
import com.meiduimall.password.util.PassworkEncryptUtils;

/**
 * 属性值加密工厂Bean。
 * 
 * @author 
 * @version 
 * @since 
 */
public class PropertiesDecrypt implements FactoryBean<Properties>
{

	private static final String USER_KEY = "user";
	private static final String PASSWORD_KEY = "password";

	private String encKey = "6e63627a2437346c";

	private Properties properties;

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

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties) throws Exception
	{
		if (properties == null)
			throw new NullPointerException("The properties is null.");

		this.properties = properties;

		String user = properties.getProperty(USER_KEY);
		if (user != null)
			this.properties.setProperty(USER_KEY, user);

		String srcPwd = properties.getProperty(PASSWORD_KEY);
		if (srcPwd != null && srcPwd.length() != 0)
		{
			if(srcPwd.length() < 16 || srcPwd.length() % 8 != 0)
				throw new Exception("encrypt password length error !");
			
			this.properties.setProperty(PASSWORD_KEY, new String(PassworkEncryptUtils.decrypt(hexToBytes(srcPwd), hexToBytes(encKey))));
		}
	}


	public Properties getObject() throws Exception
	{
		return properties;
	}


	public Class<?> getObjectType()
	{
		return Properties.class;
	}


	public boolean isSingleton()
	{
		return true;
	}
}
