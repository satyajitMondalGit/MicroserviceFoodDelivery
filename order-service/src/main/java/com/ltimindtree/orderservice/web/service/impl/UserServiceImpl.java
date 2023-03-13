package com.ltimindtree.orderservice.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ltimindtree.orderservice.exception.RestTemplateResponseErrorHandler;
import com.ltimindtree.orderservice.web.dto.AddressDTO;
import com.ltimindtree.orderservice.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${user.service.host}")
	private String host;


	@Override
	public AddressDTO getAddressOfTheUser(long userId, long addressId) {
		
		return restTemplate.getForObject("http://localhost:8086/api/v1/user/"+userId+"/address/"+addressId, AddressDTO.class);
	}

}
