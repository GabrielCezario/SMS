package com.pitang.Sms.exception;

public class UserEmptyFieldsException extends Exception{
	
	private boolean userNameIsEmpty;
	private boolean firstNameIsEmpty;
	private boolean emailIsEmpty;
	private boolean passwordIsEmpty;
	
	public boolean isUserNameIsEmpty() {
		return userNameIsEmpty;
	}
	
	public void setUserNameIsEmpty(boolean userNameIsEmpty) {
		this.userNameIsEmpty = userNameIsEmpty;
	}
	
	public boolean isFirstNameIsEmpty() {
		return firstNameIsEmpty;
	}
	
	public void setFirstNameIsEmpty(boolean firstNameIsEmpty) {
		this.firstNameIsEmpty = firstNameIsEmpty;
	}
	
	public boolean isEmailIsEmpty() {
		return emailIsEmpty;
	}
	
	public void setEmailIsEmpty(boolean emailIsEmpty) {
		this.emailIsEmpty = emailIsEmpty;
	}
	
	public boolean isPasswordIsEmpty() {
		return passwordIsEmpty;
	}
	
	public void setPasswordIsEmpty(boolean passwordIsEmpty) {
		this.passwordIsEmpty = passwordIsEmpty;
	}

}
