package com.ltimindtree.restaurantsservice.web.dto;

import java.io.Serializable;
import java.util.List;

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
public class RestaureantMsgDTO implements Serializable{

	
	private long OrderId;
	private long ResturentId;
	private long UserId;
	private AddressDTO addressDTO;
	private List<FoodItemDTO> footItemDTO;
}
