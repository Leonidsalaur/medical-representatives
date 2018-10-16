package com.leosal.medrepear.exceptions;




public class UserNotAuthorisedException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotAuthorisedException(){
		//super("User is not authorised for current session");
		super("User is not authorised for current session");
	}
}
