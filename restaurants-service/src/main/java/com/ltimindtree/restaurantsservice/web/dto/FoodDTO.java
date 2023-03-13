package com.ltimindtree.restaurantsservice.web.dto;

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
public class FoodDTO {
	
	private int itemId;

	private String itemName;

	private String category;

	private String cuisine;

	private int price;

}
