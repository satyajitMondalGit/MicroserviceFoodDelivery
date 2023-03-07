package com.ltimindtree.userservice.exception;

public class AddressNotFoundException extends RuntimeException {

	private String msg;
	
	public AddressNotFoundException(String msg) {
		super(msg);
		this.msg  = msg;
	}
}
