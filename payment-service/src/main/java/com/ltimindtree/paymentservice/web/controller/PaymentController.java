package com.ltimindtree.paymentservice.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.paymentservice.web.dto.PaymentProcessingDTO;
import com.ltimindtree.paymentservice.web.service.PaymentService;

@RequestMapping("/PaymentApi/")
@RestController
public class PaymentController {

	

	private PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping
	public String paymentStatus(@RequestBody PaymentProcessingDTO paymentDto) {
		
		return paymentService.paymentProcessing(paymentDto);
	} 
}
