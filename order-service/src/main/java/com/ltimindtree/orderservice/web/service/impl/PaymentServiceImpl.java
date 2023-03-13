package com.ltimindtree.orderservice.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ltimindtree.orderservice.exception.RestTemplateResponseErrorHandler;
import com.ltimindtree.orderservice.web.dto.PaymentProcessingDTO;
import com.ltimindtree.orderservice.web.dto.cardInformationDTO;
import com.ltimindtree.orderservice.web.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private RestTemplate restTemplate;

	
	
	@Override
	public String getpaymentStatus(PaymentProcessingDTO paymentDto) {
		
		return restTemplate.postForObject("http://localhost:8084/PaymentApi/", paymentDto, String.class);
	}

	
}
