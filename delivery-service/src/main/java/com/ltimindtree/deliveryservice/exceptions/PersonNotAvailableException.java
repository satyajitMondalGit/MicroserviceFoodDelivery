package com.ltimindtree.deliveryservice.exceptions;

public class PersonNotAvailableException extends RuntimeException{

	private String msg;

	public PersonNotAvailableException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
}
