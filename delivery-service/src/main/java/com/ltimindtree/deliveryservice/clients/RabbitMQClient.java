package com.ltimindtree.deliveryservice.clients;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltimindtree.deliveryservice.constants.Constants;
import com.ltimindtree.deliveryservice.web.dto.DeliveryDTO;
import com.ltimindtree.deliveryservice.web.service.DeliveryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQClient {

	@Autowired
	private DeliveryService deliveryService;
	
	@RabbitListener(queues = Constants.ORDER_DELIVERY_QUEUE)
	public void consumeMessageFromOrderService(DeliveryDTO deliveryDto) {
		
		log.info("deliveryDto "+deliveryDto);
		deliveryService.assignAdeliveryPerson(deliveryDto);
	}
}
