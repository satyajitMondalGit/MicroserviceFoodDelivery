package com.ltimindtree.paymentservice.web.service;

import com.ltimindtree.paymentservice.web.dto.PaymentProcessingDTO;

public interface PaymentService {

	String paymentProcessing(PaymentProcessingDTO paymentDto);

}
