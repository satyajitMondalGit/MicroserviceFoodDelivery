package com.ltimindtree.orderservice.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ltimindtree.orderservice.exception.RestTemplateResponseErrorHandler;
import com.ltimindtree.orderservice.web.dto.CartDTO;
import com.ltimindtree.orderservice.web.dto.ResponseCartDTO;
import com.ltimindtree.orderservice.web.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${cart.service.host}")
	private String host;
	
//	@Value("${cart-service.host}")
//	String cartHost;
//	

	@Override
	public ResponseCartDTO getCartDetails(long userId) {
		log.info("User ID "+userId);
		
		ResponseCartDTO responseCartDto = restTemplate.getForObject("http://localhost:8081/api/v1/cart/"+userId, ResponseCartDTO.class);
		log.info("responseCartDto "+responseCartDto);
		return responseCartDto;
	}



	@Override
	public void removeItemsFromCartAfterOrderPlaced(CartDTO cartDto) {
		restTemplate.delete("http://localhost:8081/api/v1/cart/"+"delete", cartDto);
		
	}

}
