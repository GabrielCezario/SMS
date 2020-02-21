package com.pitang.Sms.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.pitang.Sms.exception.UserEmailInvalidException;
import com.pitang.Sms.exception.UserEmptyFieldsException;
import com.pitang.Sms.exception.UserInvalidException;
import com.pitang.Sms.model.UserModel;
import com.pitang.Sms.repository.UserModelRepository;
import com.pitang.Sms.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserModelRepository userModelRepository;
	
	//Public methods
	
	@Override
	public UserModel addUser(UserModel userModel) {
		
		UserEmailInvalidException userEmailInvalidException = verifyEmail(userModel.getEmail());
		UserInvalidException userInvalidException = verifyUser(userModel);
		UserEmptyFieldsException userEmptyFieldsException = verifyEmptyFields(userModel);
		
		if (userEmailInvalidException == null &&
			userInvalidException == null && 
			userEmptyFieldsException == null) {
			
			return userModelRepository.save(userModel);
			
		} else {
			return null;
		}
		
	}
	
	@Override
	public UserModel findUserByName(String userName) {
		return null;
	}
	
	@Override
	public List<UserModel> lisUsers() {
		
		return null;
	}
	
	@Override
	public UserModel updateUser(UserModel userModel) {
		
		return null;
	}
	
	@Override
	public void deleteUser(long id) {
		userModelRepository.deleteById(id);
	}
	
	//Private methods
	
	private UserEmailInvalidException verifyEmail(String email) {
		
		UserEmailInvalidException userEmailInvalidException = null;
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		if (StringUtils.isEmpty(email)) {
			if (matcher.matches() == false) {
				if (userEmailInvalidException == null) {	
					userEmailInvalidException = new UserEmailInvalidException();
				}
				
				userEmailInvalidException.setEmailInvalid(true);
			}
		}
		
		return userEmailInvalidException;
	}
	
	private UserInvalidException verifyUser(UserModel userModel) {
		
		UserInvalidException userInvalidException = null;
		
		if (userModel == null) {
			if (userInvalidException == null) {
				userInvalidException = new UserInvalidException();
			}
			
			userInvalidException.setUserInvalid(true);
			
		}
		
		return userInvalidException;
		
	}
	
	private void verifyPassword() {
		
	}
	
	private UserEmptyFieldsException verifyEmptyFields(UserModel userModel){
		
		UserEmptyFieldsException userEmptyFieldsException = null;
		
		if (StringUtils.isEmpty(userModel.getUserName())) {
			if (userEmptyFieldsException == null) {
				userEmptyFieldsException = new UserEmptyFieldsException();
			}
			
			userEmptyFieldsException.setUserNameIsEmpty(true);
		}
		
		if (StringUtils.isEmpty(userModel.getFirstName())) {
			if (userEmptyFieldsException == null) {
				userEmptyFieldsException = new UserEmptyFieldsException();
			}
			
			userEmptyFieldsException.setFirstNameIsEmpty(true);
		}
		
		if (StringUtils.isEmpty(userModel.getEmail())) {
			if (userEmptyFieldsException == null) {
				userEmptyFieldsException = new UserEmptyFieldsException();
			}
			
			userEmptyFieldsException.setEmailIsEmpty(true);
		}
		
		if (StringUtils.isEmpty(userModel.getPassword())) {
			if (userEmptyFieldsException == null) {
				userEmptyFieldsException = new UserEmptyFieldsException();
			}
			
			userEmptyFieldsException.setPasswordIsEmpty(true);
		}
		
		return userEmptyFieldsException;
		
	}

}
