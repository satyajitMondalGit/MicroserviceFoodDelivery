package com.ltimindtree.orderservice.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltimindtree.orderservice.entity.Order;
import com.ltimindtree.orderservice.exception.OrderNotFoundException;
import com.ltimindtree.orderservice.repository.OrderRepository;
import com.ltimindtree.orderservice.web.dto.RequestOrderDTO;
import com.ltimindtree.orderservice.web.dto.ResponseOrderDTO;
import com.ltimindtree.orderservice.web.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;

	@Override
	public ResponseOrderDTO placeOrder(RequestOrderDTO requestOrderDTO) {
		
		return null;
	}

	@Override
	public ResponseOrderDTO getOrderDetails(long orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Orderis not found with Id"+orderId));
		return ResponseOrderDTO.builder()
				.message("Your Order's currently in "+order.getStatus())
				.order(order)
				.build();
	}

}
