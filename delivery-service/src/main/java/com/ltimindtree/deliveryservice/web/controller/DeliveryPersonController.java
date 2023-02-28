package com.ltimindtree.deliveryservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.deliveryservice.web.dto.DeliveryPersonDto;
import com.ltimindtree.deliveryservice.web.service.DeliveryService;

@RequestMapping("/delivery")
@RestController
public class DeliveryPersonController {

	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("/person")
	ResponseEntity<DeliveryPersonDto> addDeliveryPerson(@RequestBody DeliveryPersonDto dPersonDto){
		return new ResponseEntity<>(deliveryService.addDeliveryPerson(dPersonDto), HttpStatus.CREATED);
	}
	
}