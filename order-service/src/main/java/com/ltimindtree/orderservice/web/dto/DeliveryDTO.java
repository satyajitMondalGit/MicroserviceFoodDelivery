package com.ltimindtree.orderservice.web.dto;

import java.io.Serializable;

import com.ltimindtree.orderservice.web.dto.cardInformationDTO.cardInformationDTOBuilder;

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
public class DeliveryDTO implements Serializable {

	private long OrderId;
	private long ResturentId;
	private long UserId;
	private AddressDTO addressDTO;
	
}
