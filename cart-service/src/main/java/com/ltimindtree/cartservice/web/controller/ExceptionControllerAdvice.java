package com.ltimindtree.cartservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ltimindtree.cartservice.exception.FoodItemNotFound;
import com.ltimindtree.cartservice.exception.ItemAlreadyExistException;
import com.ltimindtree.cartservice.exception.MultipleRestaurantException;
import com.ltimindtree.cartservice.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FoodItemNotFound.class)
	public String invalidFoodItemExceptionHandler(FoodItemNotFound ex) {
       
        return ex.getMessage();
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ItemAlreadyExistException.class)
	public String itemAlreadyExistException(ItemAlreadyExistException ex) {
       
        return ex.getMessage();
    }
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MultipleRestaurantException.class)
	public String multipleRestaurantException(MultipleRestaurantException ex) {
       
        return ex.getMessage();
    }
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundException(UserNotFoundException ex) {
       
        return ex.getMessage();
    }
}
