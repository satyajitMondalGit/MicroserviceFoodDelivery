package com.ltimindtree.cartservice.exception;

public class MultipleRestaurantException extends RuntimeException {

	private String msg;

	public MultipleRestaurantException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	
}
