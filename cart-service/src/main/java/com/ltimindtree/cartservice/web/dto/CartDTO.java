package com.ltimindtree.cartservice.web.dto;

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

//	@NotBlank(message = "User Id Cannot be blank")
	private long userId;
//	@NotBlank(message = "Restaurant Id Cannot be blank")
	private long resturantId;
	private List<FoodItemDTO> foodItem;
}
