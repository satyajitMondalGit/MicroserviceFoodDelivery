package com.ltimindtree.orderservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.orderservice.web.dto.RequestOrderDTO;
import com.ltimindtree.orderservice.web.dto.ResponseOrderDTO;
import com.ltimindtree.orderservice.web.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/place")
	ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody RequestOrderDTO requestOrderDTO) {

		return new ResponseEntity<>(orderService.placeOrder(requestOrderDTO), HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	ResponseEntity<ResponseOrderDTO> getOrderDetails(@PathVariable("orderId") long orderId ) {

		return new ResponseEntity<>(orderService.getOrderDetails(orderId), HttpStatus.OK);
	}

}
