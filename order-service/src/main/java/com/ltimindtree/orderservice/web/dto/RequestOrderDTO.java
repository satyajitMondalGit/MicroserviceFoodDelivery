package com.ltimindtree.orderservice.web.dto;

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
public class RequestOrderDTO {

	
	private long userId;
	private long addressId;
	private cardInformationDTO cardDatils;
}
