package com.ltimindtree.orderservice.web.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.ltimindtree.orderservice.constants.Constants;
import com.ltimindtree.orderservice.web.dto.RestaureantMsgDTO;
import com.ltimindtree.orderservice.web.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

	
	private RabbitTemplate rabbitTemplate;

    public RestaurantServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	@Override
	public void updateToResturent(RestaureantMsgDTO restaurantDto) {
		
		log.info("restaurantDto "+restaurantDto);
		
		
		rabbitTemplate.convertAndSend(Constants.ORDER_SERVICE_EXCHANGE, Constants.ORDER_RESTAURANTS_ROUTING_KEY, restaurantDto);
		log.info("restaurantDto "+restaurantDto);
	}

}
