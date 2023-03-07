package com.ltimindtree.deliveryservice.web.service.impl;

import org.springframework.stereotype.Service;

import com.ltimindtree.deliveryservice.entity.DeliveryPerson;
import com.ltimindtree.deliveryservice.entity.Status;
import com.ltimindtree.deliveryservice.repository.DeliveryPersonRepository;
import com.ltimindtree.deliveryservice.web.dto.DeliveryPersonDto;
import com.ltimindtree.deliveryservice.web.service.DeliveryService;


@Service
public class DeliveryServiceImpl implements DeliveryService {

	
	

	private DeliveryPersonRepository dPersonRepo;
	
	
	public DeliveryServiceImpl(DeliveryPersonRepository dPersonRepo) {
		super();
		this.dPersonRepo = dPersonRepo;
	}

	
	@Override
	public DeliveryPersonDto addDeliveryPerson(DeliveryPersonDto dPersonDto) {
		
		DeliveryPerson dPerosn = DeliveryPerson.builder()
				.id(0)
				.Name(dPersonDto.getName())
				.email(dPersonDto.getEmail())
				.mobile(dPersonDto.getMobile())
				.rating(dPersonDto.getRating())
				.status(Status.Available)
				.build();
		DeliveryPerson dPer = dPersonRepo.save(dPerosn); 
		return DeliveryPersonDto.builder()
				.id(dPer.getId())
				.Name(dPer.getName())
				.email(dPer.getEmail())
				.mobile(dPer.getMobile())
				.rating(dPer.getRating())
				.build();
	}

}
