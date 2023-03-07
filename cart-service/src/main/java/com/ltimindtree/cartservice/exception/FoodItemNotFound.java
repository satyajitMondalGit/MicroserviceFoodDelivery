package com.ltimindtree.cartservice.exception;

public class FoodItemNotFound extends RuntimeException {

	private String msg;

	public FoodItemNotFound(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	
}
