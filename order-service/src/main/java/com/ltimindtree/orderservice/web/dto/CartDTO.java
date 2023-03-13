package com.ltimindtree.orderservice.web.dto;

import java.util.List;

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
public class CartDTO {

	@NotBlank
	private long userId;
	@NotBlank
	private long resturantId;
	private List<FoodItemDTO> foodItem;
}
