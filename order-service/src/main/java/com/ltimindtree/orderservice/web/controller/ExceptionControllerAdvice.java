package com.ltimindtree.orderservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ltimindtree.orderservice.exception.CustomRestExceptionHandler;
import com.ltimindtree.orderservice.exception.OrderNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNotFoundException.class)
	public String orderNotFoundException(OrderNotFoundException ex) {
       
        return ex.getMessage();
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomRestExceptionHandler.class)
	public String customRestExceptionHandler(CustomRestExceptionHandler ex) {
       
        return ex.getMessage();
    }
}
