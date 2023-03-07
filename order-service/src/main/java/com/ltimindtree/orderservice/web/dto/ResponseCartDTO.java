package com.ltimindtree.orderservice.web.dto;

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
public class ResponseCartDTO {

	private long userId;

	private long restaurantId;

	
	private List<FoodItemDTO> fooditems;

	private int totalPrice;
}
