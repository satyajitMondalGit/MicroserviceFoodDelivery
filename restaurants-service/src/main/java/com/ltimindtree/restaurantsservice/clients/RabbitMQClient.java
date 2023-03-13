package com.ltimindtree.restaurantsservice.clients;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltimindtree.restaurantsservice.constants.Constants;
import com.ltimindtree.restaurantsservice.web.dto.ConfirmedDeliveryPersonDTO;
import com.ltimindtree.restaurantsservice.web.dto.RestaureantMsgDTO;
import com.ltimindtree.restaurantsservice.web.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQClient {

	@Autowired
	private RestaurantService restaurantService;
	
	@RabbitListener(queues = Constants.ORDER_RESTAURANTS_QUEUE)
	public void consumeMessageFromOrderService(RestaureantMsgDTO restaurantDto) {
		
		restaurantService.processTheOrderedFood(restaurantDto);
	}
	
	@RabbitListener(queues = Constants.DELIVERY_RESTAURANTS_QUEUE)
	public void consumeMessageFromDeliveryService(ConfirmedDeliveryPersonDTO ConfirmedDeliveryPersonDTO) {
		//assign the order to the delivery person
		log.info("ConfirmedDeliveryPersonDTO "+ConfirmedDeliveryPersonDTO);
	}
}
