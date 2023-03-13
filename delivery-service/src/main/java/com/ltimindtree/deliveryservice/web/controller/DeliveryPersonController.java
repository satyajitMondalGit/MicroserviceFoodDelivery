package com.ltimindtree.deliveryservice.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.deliveryservice.web.dto.DeliveryPersonDto;
import com.ltimindtree.deliveryservice.web.dto.DeliveryStatusChangeDTO;
import com.ltimindtree.deliveryservice.web.service.DeliveryService;

@RequestMapping("api/v1/delivery/")
@RestController
public class DeliveryPersonController {

	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("person")
	ResponseEntity<DeliveryPersonDto> addDeliveryPerson( @RequestBody @Valid DeliveryPersonDto dPersonDto){
		return new ResponseEntity<>(deliveryService.addDeliveryPerson(dPersonDto), HttpStatus.CREATED);
	}
	
	
	@PostMapping("status/")
	ResponseEntity<DeliveryPersonDto> updateOrderDeliveryStatus( @RequestBody @Valid DeliveryStatusChangeDTO deliveryStatusChangeDTO){
		return new ResponseEntity<>(deliveryService.updateOrderDeliveryStatus(deliveryStatusChangeDTO), HttpStatus.OK);
	}
	
	
}
