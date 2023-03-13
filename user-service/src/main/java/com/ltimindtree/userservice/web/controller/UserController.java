package com.ltimindtree.userservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.userservice.web.dto.AddressDTO;
import com.ltimindtree.userservice.web.dto.UserDTO;
import com.ltimindtree.userservice.web.service.UserService;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {

	private UserService userService;
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}


	@PostMapping("add")
	ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto){
		
		return new ResponseEntity<>(userService.addUser(userDto),HttpStatus.OK);
	}
	
	
	@GetMapping("{userId}")
	ResponseEntity<UserDTO> getUser(@PathVariable("userId") long userId){
		
		return new ResponseEntity<>(userService.getUser(userId),HttpStatus.OK);
	}
	
	@GetMapping("{userId}/address/{addId}")
	ResponseEntity<AddressDTO> getAddress(@PathVariable("userId") long userId, @PathVariable("addId")long addId){
		
		return new ResponseEntity<>(userService.getAddress(userId,addId),HttpStatus.OK);
	}
	
}
