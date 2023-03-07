package com.ltimindtree.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{

	private String msg;

	public OrderNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
}
