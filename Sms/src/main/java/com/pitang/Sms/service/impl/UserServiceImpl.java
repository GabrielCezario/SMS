package com.pitang.Sms.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pitang.Sms.exception.UserEmailInvalidException;
import com.pitang.Sms.exception.UserEmptyFieldsException;
import com.pitang.Sms.exception.UserInvalidException;
import com.pitang.Sms.model.UserModel;
import com.pitang.Sms.repository.UserModelRepository;
import com.pitang.Sms.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserModelRepository userModelRepository;
	
	//Public methods
	
	@Override
	public UserModel addUser(UserModel userModel) {
		
		verifyEmail(userModel.getEmail());
		verifyUser(userModel);
		verifyEmptyFields(userModel);
		
		return userModelRepository.save(userModel);
	}
	
	@Override
	public UserModel findUserByName(String userName) {
		return userModelRepository.findByUserName(userName);
	}
	
	public UserModel findUserByEmail(String email) {
		return userModelRepository.findByEmail(email);
	}
	
	@Override
	public List<UserModel> listUsers() {
		return userModelRepository.findAll();
	}
	
	@Override
	public UserModel updateUser(Long id, UserModel userModel) {
		
		verifyEmail(userModel.getEmail());
		verifyUser(userModel);
		verifyEmptyFields(userModel);
		
		UserModel userdb = userModelRepository.findById(id).get();
		UserModel userModified = changeUser(userModel, userdb);
		
		return userModelRepository.save(userModified);
	}
	
	@Override
	public void deleteUser(long id) {
		userModelRepository.deleteById(id);
	}
	
	//Private methods
	
	private void verifyEmail(String email) {
		
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
		
		if (userEmailInvalidException != null) {
			throw new UserEmailInvalidException();
		} 
		
	}
	
	private void verifyUser(UserModel userModel) {
		
		UserInvalidException userInvalidException = null;
		
		if (userModel == null) {
			if (userInvalidException == null) {
				userInvalidException = new UserInvalidException();
			}
			
			userInvalidException.setUserInvalid(true);
			
		}
		
		if (userInvalidException != null) {
			throw new UserInvalidException();
		} 
		
	}
	
	private void verifyPassword() {
		
	}
	
	private void verifyEmptyFields(UserModel userModel){
		
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
		
		if (userEmptyFieldsException != null) {
			throw new UserEmptyFieldsException();
		} 
		
	}
	
	private UserModel changeUser(UserModel model1, UserModel model2) {
		
		model2.setUserName(model1.getUserName());
		model2.setFirstName(model1.getFirstName());
		model2.setLastName(model1.getLastName());
		model2.setEmail(model1.getEmail());
		model2.setPassword(model2.getPassword());
		
		return model2;
	}

}
