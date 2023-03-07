package com.ltimindtree.userservice.exception;

public class UserNotFoundException extends RuntimeException {

	private String msg;
	
	public UserNotFoundException(String msg) {
		super(msg);
		this.msg  = msg;
	}
}
