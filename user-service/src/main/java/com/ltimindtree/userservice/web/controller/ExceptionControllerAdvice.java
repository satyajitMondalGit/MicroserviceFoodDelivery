package com.ltimindtree.userservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ltimindtree.userservice.exception.AddressNotFoundException;
import com.ltimindtree.userservice.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
	public String invalidUserExceptionHandler(UserNotFoundException ex) {
       
        return ex.getMessage();
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AddressNotFoundException.class)
	public String invalidAddressExceptionHandler(AddressNotFoundException ex) {
       
        return ex.getMessage();
    }
}
