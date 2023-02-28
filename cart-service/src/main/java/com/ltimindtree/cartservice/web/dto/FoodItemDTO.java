package com.ltimindtree.cartservice.web.dto;

import javax.validation.constraints.NotBlank;

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
public class FoodItemDTO {

	@NotBlank
	private int itemId;

	private String itemName;

	private String category;


	private String cuisine;

	private int price;

	@NotBlank
	private int quentity;

}
