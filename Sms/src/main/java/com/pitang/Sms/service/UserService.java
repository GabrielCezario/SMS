package com.pitang.Sms.service;

import java.util.List;

import com.pitang.Sms.model.UserModel;

public interface UserService {
	
	public UserModel addUser(UserModel userModel);
	public UserModel findUserByName(String userName);
	public UserModel findUserByEmail(String email);
	public UserModel updateUser(Long id, UserModel userModel);
	public void deleteUser(long id);
	
	public List<UserModel> listUsers();

}
