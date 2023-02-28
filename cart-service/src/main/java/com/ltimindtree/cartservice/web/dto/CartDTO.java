package com.ltimindtree.cartservice.web.dto;

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
public class CartDTO {

	
	private long userId;
	private long resturantId;
	private List<FoodItemDTO> foodItem;
}
