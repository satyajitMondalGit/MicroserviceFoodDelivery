package com.ltimindtree.deliveryservice.web.dto;

import java.io.Serializable;


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
