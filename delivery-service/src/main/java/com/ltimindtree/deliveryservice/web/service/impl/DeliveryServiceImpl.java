package com.ltimindtree.deliveryservice.web.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.ltimindtree.deliveryservice.constants.Constants;
import com.ltimindtree.deliveryservice.entity.DeliveryPerson;
import com.ltimindtree.deliveryservice.entity.Status;
import com.ltimindtree.deliveryservice.exceptions.PersonNotAvailableException;
import com.ltimindtree.deliveryservice.repository.DeliveryPersonRepository;
import com.ltimindtree.deliveryservice.web.dto.ConfirmedDeliveryPersonDTO;
import com.ltimindtree.deliveryservice.web.dto.DeliveryDTO;
import com.ltimindtree.deliveryservice.web.dto.DeliveryPersonDto;
import com.ltimindtree.deliveryservice.web.dto.DeliveryStatusChangeDTO;
import com.ltimindtree.deliveryservice.web.service.DeliveryService;
//import com.ltimindtree.orderservice.constants.Constants;
//import com.ltimindtree.orderservice.web.dto.RestaureantMsgDTO;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	private DeliveryPersonRepository dPersonRepo;
	private RabbitTemplate rabbitTemplate;

	public DeliveryServiceImpl(DeliveryPersonRepository dPersonRepo, RabbitTemplate rabbitTemplate) {
		super();
		this.dPersonRepo = dPersonRepo;
		this.rabbitTemplate = rabbitTemplate;
	}

//    public RestaurantServiceImpl(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//	@Override
//	public void updateToResturent(RestaureantMsgDTO restaurantDto) {
//		
//		log.info("restaurantDto "+restaurantDto);
//		
//		
//		rabbitTemplate.convertAndSend(Constants.ORDER_SERVICE_EXCHANGE, Constants.ORDER_RESTAURANTS_ROUTING_KEY, restaurantDto);
//	}
//	
//	public DeliveryServiceImpl(DeliveryPersonRepository dPersonRepo) {
//		super();
//		this.dPersonRepo = dPersonRepo;
//	}

	@Override
	public DeliveryPersonDto addDeliveryPerson(DeliveryPersonDto dPersonDto) {

		DeliveryPerson dPerosn = DeliveryPerson.builder().id(0).Name(dPersonDto.getName()).email(dPersonDto.getEmail())
				.mobile(dPersonDto.getMobile()).rating(dPersonDto.getRating()).status(Status.Available).build();
		DeliveryPerson dPer = dPersonRepo.save(dPerosn);
		return DeliveryPersonDto.builder().id(dPer.getId()).Name(dPer.getName()).email(dPer.getEmail())
				.mobile(dPer.getMobile()).rating(dPer.getRating()).build();
	}

	@Override
	public void assignAdeliveryPerson(DeliveryDTO deliveryDto) {
		List<DeliveryPerson> personList = dPersonRepo.findAll();
		DeliveryPerson person = personList.stream().filter(p -> p.getStatus().equals(Status.Available)).findAny()
				.orElseThrow(() -> new PersonNotAvailableException("no person is available"));
		person.setStatus(Status.Occupied);
		DeliveryPerson dPer = dPersonRepo.save(person);
		sendMessageToResturant(
				ConfirmedDeliveryPersonDTO.builder().orderId(deliveryDto.getOrderId())
						.deliveryPersonDto(DeliveryPersonDto.builder().id(dPer.getId()).Name(dPer.getName())
								.email(dPer.getEmail()).mobile(dPer.getMobile()).rating(dPer.getRating()).build())
						.build());
	}

	private void sendMessageToResturant(ConfirmedDeliveryPersonDTO confirmedDeliveryPersonDTO) {
		
		rabbitTemplate.convertAndSend(Constants.DELIVERY_SERVICE_EXCHANGE, Constants.DELIVERY_RESTAURANTS_KEY, confirmedDeliveryPersonDTO);

	}

	@Override
	public DeliveryPersonDto updateOrderDeliveryStatus(@Valid DeliveryStatusChangeDTO deliveryStatusChangeDTO) {
		DeliveryPerson person = dPersonRepo.findById(deliveryStatusChangeDTO.getDeiveryPerosnId())
				.orElseThrow(() -> new PersonNotAvailableException("no person is available"));
		
		
		sendStatusMessageToOrder(deliveryStatusChangeDTO);

		person.setStatus(Status.Available);
		DeliveryPerson dPer = dPersonRepo.save(person);

		return DeliveryPersonDto.builder().id(dPer.getId()).Name(dPer.getName()).email(dPer.getEmail())
				.mobile(dPer.getMobile()).rating(dPer.getRating()).build();
	}

	private void sendStatusMessageToOrder(@Valid DeliveryStatusChangeDTO deliveryStatusChangeDTO) {
		rabbitTemplate.convertAndSend(Constants.DELIVERY_SERVICE_EXCHANGE, Constants.DELIVERY_ORDER_KEY, deliveryStatusChangeDTO);

	}

}
