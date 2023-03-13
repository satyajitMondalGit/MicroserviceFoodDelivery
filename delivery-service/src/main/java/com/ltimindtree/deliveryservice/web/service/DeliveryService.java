package com.ltimindtree.deliveryservice.web.service;

import javax.validation.Valid;

import com.ltimindtree.deliveryservice.web.dto.DeliveryDTO;
import com.ltimindtree.deliveryservice.web.dto.DeliveryPersonDto;
import com.ltimindtree.deliveryservice.web.dto.DeliveryStatusChangeDTO;

public interface DeliveryService {

	DeliveryPersonDto addDeliveryPerson(DeliveryPersonDto dPersonDto);

	void assignAdeliveryPerson(DeliveryDTO deliveryDto);

	DeliveryPersonDto updateOrderDeliveryStatus(@Valid DeliveryStatusChangeDTO deliveryStatusChangeDTO);

}
