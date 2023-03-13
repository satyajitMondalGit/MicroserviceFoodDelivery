package com.ltimindtree.deliveryservice.web.dto;

import java.io.Serializable;

import com.ltimindtree.deliveryservice.web.dto.AddressDTO.AddressDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfirmedDeliveryPersonDTO implements Serializable{

	private DeliveryPersonDto deliveryPersonDto;
	private long orderId;
}
