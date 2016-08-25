package com.cn.uuu.service;

import com.cn.uuu.pojo.User;

public interface UserService {  	

	public User user(User user);
	
	public User selectLogin(String username);

	public User getUserById(int i);

	public User selectByPrimaryKey(int i);
	
	public User getUserByusername(String username);
	
	public int inster(User user);
	
	public Boolean getLoginUser(User user);

}  
