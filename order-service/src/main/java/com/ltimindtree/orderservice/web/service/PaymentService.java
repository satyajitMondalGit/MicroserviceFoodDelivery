package com.ltimindtree.orderservice.web.service;

import com.ltimindtree.orderservice.web.dto.PaymentProcessingDTO;
import com.ltimindtree.orderservice.web.dto.cardInformationDTO;

public interface PaymentService {

	String getpaymentStatus(PaymentProcessingDTO build);


}
