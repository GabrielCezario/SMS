package com.pitang.Sms.exception;

public class UserEmailInvalidException extends Exception{
	
	private boolean emailInvalid;

	public boolean isEmailInvalid() {
		return emailInvalid;
	}

	public void setEmailInvalid(boolean emailInvalid) {
		this.emailInvalid = emailInvalid;
	}

}
