package com.ltimindtree.orderservice.web.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.ltimindtree.orderservice.constants.Constants;
import com.ltimindtree.orderservice.web.dto.DeliveryDTO;
import com.ltimindtree.orderservice.web.service.DeliveryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

	private RabbitTemplate rabbitTemplate;

    public DeliveryServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	@Override
	public void pickDeliveryPerson(DeliveryDTO deliveryDto) {
		log.info("deliveryDto"+deliveryDto);
		rabbitTemplate.convertAndSend(Constants.ORDER_SERVICE_EXCHANGE,Constants.ORDER_DELIVERY_ROUTING_KEY , deliveryDto);
		log.info("deliveryDto"+deliveryDto);
	}

}
