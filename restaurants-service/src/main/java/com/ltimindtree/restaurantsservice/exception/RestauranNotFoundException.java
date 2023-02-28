package com.ltimindtree.restaurantsservice.exception;

public class RestauranNotFoundException extends RuntimeException {

	private String msg;
	
	public RestauranNotFoundException(String msg) {
		super(msg);
		this.msg  = msg;
	}
}
