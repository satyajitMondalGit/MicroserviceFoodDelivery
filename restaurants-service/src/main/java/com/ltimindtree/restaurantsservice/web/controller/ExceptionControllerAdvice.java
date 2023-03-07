package com.ltimindtree.restaurantsservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ltimindtree.restaurantsservice.exception.RestauranNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestauranNotFoundException.class)
	public String invalidResturentExceptionHandler(RestauranNotFoundException ex) {
       
        return ex.getMessage();
    }
	
}
