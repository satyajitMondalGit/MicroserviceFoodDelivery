package com.ltimindtree.cartservice.exception;

public class ItemAlreadyExistException extends RuntimeException {

	private String msg;

	public ItemAlreadyExistException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	
}
