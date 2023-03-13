package com.ltimindtree.paymentservice.web.service.impl;

import org.springframework.stereotype.Service;

import com.ltimindtree.paymentservice.web.dto.PaymentProcessingDTO;
import com.ltimindtree.paymentservice.web.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public String paymentProcessing(PaymentProcessingDTO paymentDto) {
		
		return "Success";
	}

}
