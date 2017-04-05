package com.first.services;

import com.first.domain.Members;

public interface UserService {  	

	public Members user(Members members);
	
	public Members selectLogin(String username);

	public Members getUserById(int i);

	public Members selectByPrimaryKey(int i);
	
	public Members getUserByusername(String username);
	
	public int inster(Members members);
	
	public Boolean getLoginUser(Members members);

}  
