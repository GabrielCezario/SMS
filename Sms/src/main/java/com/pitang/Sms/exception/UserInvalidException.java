package com.pitang.Sms.exception;

public class UserInvalidException extends Exception{

	private boolean userInvalid;

	public boolean isUserInvalid() {
		return userInvalid;
	}

	public void setUserInvalid(boolean userInvalid) {
		this.userInvalid = userInvalid;
	}
	
	
	
}
