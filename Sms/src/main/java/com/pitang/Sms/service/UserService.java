package com.pitang.Sms.service;

import java.util.List;

import com.pitang.Sms.model.UserModel;

public interface UserService {
	
	public UserModel findUserByName(String userName);
	public UserModel addUser(UserModel userModel);
	public UserModel updateUser(UserModel userModel);
	public void deleteUser(long id);
	
	public List<UserModel> lisUsers();

}
