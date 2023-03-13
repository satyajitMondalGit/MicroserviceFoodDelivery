package com.ltimindtree.orderservice.exception;

public class CustomRestExceptionHandler extends RuntimeException{

	private String msg;

	public CustomRestExceptionHandler(String msg) {
		super(msg);
		this.msg = msg;
	}
	
}
