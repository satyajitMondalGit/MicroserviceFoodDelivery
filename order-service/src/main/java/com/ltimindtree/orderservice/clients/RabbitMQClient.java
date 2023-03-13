package com.ltimindtree.orderservice.clients;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltimindtree.orderservice.constants.Constants;
import com.ltimindtree.orderservice.web.dto.DeliveryStatusChangeDTO;
import com.ltimindtree.orderservice.web.dto.OrderProcessingStatusDTO;
import com.ltimindtree.orderservice.web.service.OrderService;


@Component
public class RabbitMQClient {

	@Autowired
	private OrderService orderService;
	
	@RabbitListener(queues = Constants.DELIVERY_ORDER_QUEUE)
	public void consumeMessageFromDeliveryService(DeliveryStatusChangeDTO DeliveryStatusChangeDTO) {
		orderService.uppateStatus(DeliveryStatusChangeDTO.getOrderId(), DeliveryStatusChangeDTO.getStatus());
	}
	
	@RabbitListener(queues = Constants.RESTAURANTS_ORDER_QUEUE)
	public void consumeMessageFromResturantService(OrderProcessingStatusDTO orderProcessingStatusDTO) {	
	
		orderService.uppateStatus(orderProcessingStatusDTO.getOrderId(), orderProcessingStatusDTO.getStatus());
	
	}
}
